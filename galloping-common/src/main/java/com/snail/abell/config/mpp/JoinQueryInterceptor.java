package com.snail.abell.config.mpp;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Abell
 * @className JoinQueryInterceptor
 * @description TODO
 * @date 2020/6/5
 * */
public class JoinQueryInterceptor extends JsqlParserSupport implements InnerInterceptor {
    /**
     * 保存我们的关联查询的上下文信息
     */
    static ThreadLocal<List<JoinBuilder>> joinBuilderThreadLocal = new ThreadLocal<>();

    /**
     * 操作前置处理
     * <p>
     * 改改sql啥的
     *
     * @param executor      Executor(可能是代理对象)
     * @param ms            MappedStatement
     * @param parameter     parameter
     * @param rowBounds     rowBounds
     * @param resultHandler resultHandler
     * @param boundSql      boundSql
     */
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
                            ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (parameter instanceof MapperMethod.ParamMap) {
            for (Object value : ((MapperMethod.ParamMap) parameter).values()) {
                if (value instanceof MyQueryWrapper) {
                    List<JoinBuilder> joinBuilders = ((MyQueryWrapper) value).getJoinBuilder();
                    if(!CollectionUtils.isEmpty(joinBuilders)){
                        joinBuilderThreadLocal.set(joinBuilders);
                        try {
                            logger.debug("开始添加关联查询SQL");
                            String s = this.parserSingle(boundSql.getSql(), parameter);
                            logger.debug("加了关联查询的SQL ： "+ s);
                            PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
                            mpBs.sql(s);
                        }finally {
                            joinBuilderThreadLocal.remove();
                        }
                        return;
                    }
                }
            }
        }
    }

    /**
     * 查询
     */
    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        List<JoinBuilder> joinBuilders = joinBuilderThreadLocal.get();
        PlainSelect selectBody = (PlainSelect) select.getSelectBody();
        if (selectBody.getFromItem().getAlias()==null) {
            selectBody.getFromItem().setAlias(new Alias("mainjointable"));
        }
        setJoins(selectBody,joinBuilders);


    }


    private void setJoins(PlainSelect selectBody,List<JoinBuilder> joinBuilders) {
        List<SelectItem> selectItems = selectBody.getSelectItems();
        Expression where = selectBody.getWhere();
        List<Join> joins = new ArrayList<>();
        for (int i = 0; i < joinBuilders.size(); i++) {
            JoinBuilder joinBuilder = joinBuilders.get(i);
            Join builderJoin = joinBuilder.getJoin();
            Set<String> selectFields = new HashSet<>(joinBuilder.getSelectFields());
            Join join = new Join();
            join.setLeft(builderJoin.isLeft());
            join.setRight(builderJoin.isRight());
            join.setInner(builderJoin.isInner());
            Table table = new Table(joinBuilder.getSubTable());
            table.setAlias( new Alias("subjointable"+i));
            setSelectItems(table,selectFields,selectItems,selectBody);
            join.setRightItem(table);
//            Expression expression = getOnExpressionWithTable(joinBuilder);
            Expression onExpression = joinBuilder.getJoin().getOnExpression();
            selectFields = new HashSet<>(joinBuilder.getSelectFields());
            setOnCase(onExpression,table,selectFields,(Table) selectBody.getFromItem(),false);
            join.setOnExpression(onExpression);
            joins.add(join);
            selectFields = new HashSet<>(joinBuilder.getSelectFields());
            setWhere(where,table,selectFields,(Table) selectBody.getFromItem());
        }
        selectBody.setJoins(joins);
    }


//    private Expression getOnExpressionWithTable(JoinBuilder joinBuilder) {
////        setWhere(joinBuilder.getJoin().getOnExpression(),);
//        return joinBuilder.getJoin().getOnExpression();
//    }


    private void setSelectItems(Table table,  Set<String> selectFields, List<SelectItem> selectItems, PlainSelect selectBody) {


        for (SelectItem selectItem : selectItems) {
            if (selectItem instanceof SelectExpressionItem) {
                if (((SelectExpressionItem) selectItem).getExpression() instanceof Column && selectBody.getFromItem() instanceof Table) {
                    Column expression = (Column) ((SelectExpressionItem) selectItem).getExpression();
                    if (expression.getTable()==null&&selectFields.contains(expression.getColumnName().toUpperCase())){
                        expression.setTable(table);
                        selectFields.remove(expression.getColumnName().toUpperCase());
                    }else if(expression.getTable()==null){
                        expression.setTable((Table) selectBody.getFromItem());
                    }
                }
            }
        }
        if (!selectFields.isEmpty()){
            for (String selectField : selectFields) {
                SelectExpressionItem selectExpressionItem = new SelectExpressionItem();
                Column column = new Column();
                column.setTable(table);
                column.setColumnName(selectField);
                selectExpressionItem.setExpression(column);
                selectItems.add(selectExpressionItem);
            }
        }
    }

    /**
     *
     * @param on
     * @param subTable
     * @param joinSelectFields
     * @param sourceTable
     * @param isLeft 是否是左侧列，如果是那么就是主表，如果false，那么就是关联表，如果为null，那么就需要根据join字段判断
     */
    private void setOnCase(Object on, Table subTable, Set<String> joinSelectFields , Table sourceTable,Boolean isLeft) {
        if (on==null) {
            return;
        }
        if (on instanceof Column) {
            Column column = (Column) on;
            if((column).getTable()==null &&isLeft!=null){
                (column).setTable(isLeft?sourceTable:subTable);
            }
            if (isLeft==null&&column.getTable()==null){
                (column).setTable(joinSelectFields.contains(column.getColumnName().toUpperCase())?subTable:sourceTable);
            }
        }else if (on instanceof BinaryExpression){
            setOnCase(((BinaryExpression) on).getLeftExpression(),subTable,joinSelectFields,sourceTable,true);
            setOnCase(((BinaryExpression) on).getRightExpression(),subTable,joinSelectFields,sourceTable,false);
        }else if (on instanceof Parenthesis){
            setOnCase(((Parenthesis) on).getExpression(),subTable,joinSelectFields,sourceTable,false);
        }else if(on instanceof IsNullExpression){
            setOnCase(((IsNullExpression) on).getLeftExpression(),subTable,joinSelectFields,sourceTable,null);
        }else if (on instanceof Between){
            setOnCase(((Between) on).getLeftExpression(),subTable,joinSelectFields,sourceTable,null);
        }
        //有其他条件再补充
    }

    private void setWhere(Object where, Table subTable, Set<String> joinSelectFields , Table sourceTable) {
        if (where==null) {
            return;
        }
        if (where instanceof Column) {
            Column column = (Column) where;
            if((column).getTable()==null&&joinSelectFields.contains((column).getColumnName().toUpperCase())){
                (column).setTable(subTable);
            }else if((column).getTable()==null){
                (column).setTable(sourceTable);
            }
        }else if (where instanceof BinaryExpression){
            setWhere(((BinaryExpression) where).getLeftExpression(),subTable,joinSelectFields,sourceTable);
            setWhere(((BinaryExpression) where).getRightExpression(),subTable,joinSelectFields,sourceTable);
        }else if (where instanceof Parenthesis){
            setWhere(((Parenthesis) where).getExpression(),subTable,joinSelectFields,sourceTable);
        }else if(where instanceof IsNullExpression){
            setWhere(((IsNullExpression) where).getLeftExpression(),subTable,joinSelectFields,sourceTable);
        }else if(where instanceof Between){
            setWhere(((Between) where).getLeftExpression(),subTable,joinSelectFields,sourceTable);
        }
        //有其他条件再补充
    }
}

