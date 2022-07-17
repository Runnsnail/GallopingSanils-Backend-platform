package com.snail.abell.config.mpp;


import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Abell
 * @date 2020/6/5
 * @Version 1.0
 * @Description : 关联查询join构造器
 */
@Slf4j
public class JoinBuilder {
    private StringBuilder sb = new StringBuilder();
    /**
     * 关联表里面的查询字段，比如要查询关联的用户表里面的用户名称
     */
    private String[] selectFields;
    /**
     * 关联表
     */
    private String joinTable;

    /**
     * 查询字段去重
     */
    Set<String> set = new HashSet<>();
    /**
     * 主表
     */
    private String mainTable;
    /**
     * 关联类型
     */
    private String joinType;


    private static final String LEFT_BRACKET = " ( ";
    private static final String RIGHT_BRACKET = " ) ";
    private static final String AND = " AND ";
    private static final String OR = " OR ";
    /**
     * 左连接
     */
    public static final String LEFT = " left ";
    /**
     * 右连接
     */
    public static final String RIGHT = " right ";
    /**
     * 内连接
     */
    public static final String INNER = " inner ";


    public JoinBuilder selectField(String... fields) {
        this.selectFields = fields;
        if (!ArrayUtil.isEmpty(this.selectFields)) {
            for (int i = 0; i < this.selectFields.length; i++) {
                this.selectFields[i] = StringUtils.camelToUnderline(this.selectFields[i]);
                set.add(this.selectFields[i].toUpperCase());
            }
        }
        return this;
    }


    public Set<String> getSelectFields() {
        return set;


    }


    public String getMainTable() {
        return mainTable;
    }

    public String getSubTable() {
        return this.joinTable;
    }


    /**
     * @param joinType  关联类型  JoinBuilder.LEFT,JoinBuilder.RIGHT,JoinBuilder.INNER
     * @param mainTable 主表
     * @param joinTable 关联表
     * @return
     */
    public JoinBuilder join(String joinType, String mainTable, String joinTable) {
        mainTable = StringUtils.camelToUnderline(mainTable);
        ;
        joinTable = StringUtils.camelToUnderline(joinTable);
        ;
        this.joinTable = joinTable;
        this.mainTable = mainTable;
        this.joinType = joinType;
        return this;
    }


    public static JoinBuilder build() {
        return new JoinBuilder();
    }

    public JoinBuilder and() {
        sb.append(AND);
        return this;
    }

    public JoinBuilder or() {
        sb.append(OR);
        return this;
    }

    public StringBuilder getSql() {
        return sb;
    }

    public JoinBuilder on(CaseBuilder builder) {
        sb.append(LEFT_BRACKET).append(builder.getSql()).append(RIGHT_BRACKET);
        return this;
    }

    public Join getJoin() {
        CCJSqlParserManager pm = new CCJSqlParserManager();
        String sql = "select * from " + mainTable + " " + joinType + " join " + joinTable + " on " + sb;
        try {
            net.sf.jsqlparser.statement.Statement parse = pm.parse(new StringReader(sql));
            if (parse instanceof Select) {
                return ((PlainSelect) ((Select) parse).getSelectBody()).getJoins().get(0);
            }
            return null;
        } catch (JSQLParserException e) {
            log.warn(sql);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author jenkin
     * @Since 2021年4月12日14:45:58
     * @Version 1.0
     * @Description : 条件构造器，局限于关联查询
     */
    public static class CaseBuilder {


        /**
         * SQL语句
         */
        private StringBuilder sb = new StringBuilder();
        private static final String LEFT_BRACKET = " ( ";
        private static final String RIGHT_BRACKET = " ) ";


        private static final String EQ = "=";
        private static final String NE = "<>";
        private static final String GT = ">";
        private static final String LT = "<";
        private static final String GT_EQ = ">=";
        private static final String LT_EQ = "<=";
        private static final String AND = " AND ";
        private static final String OR = " OR ";


        public static CaseBuilder build() {
            return new CaseBuilder();
        }


        public StringBuilder getSql() {
            return sb;
        }


        /**
         * 把条件表达式用括号包裹起来
         *
         * @param builder
         * @return
         */
        public CaseBuilder brackets(CaseBuilder builder) {
            sb.append(LEFT_BRACKET).append(builder.sb).append(RIGHT_BRACKET);
            return this;
        }


        public CaseBuilder and() {
            sb.append(AND);
            return this;
        }

        public CaseBuilder or() {
            sb.append(OR);
            return this;
        }


        /**
         * 规定左侧为主表的列
         * ，右侧为从表的列，不可以写反
         * 注意，在使用定值查询的时候 例如 on a.name = b.name and age = 1
         * 这个时候一样要遵循左边为主表，右边为关联表的规则.
         * 例如
         * <p>
         * 1、 and里面的条件 age字段是存在在主表里面的 那么就写成 eq("age",1)
         * 2、如果age字段是在关联表里面的，那么应该写成 eq(1,"age")
         * <p>
         * 其他的条件语句例如，ne，gt，lt等等也适用这个逻辑
         *
         * @param left  左侧列名称
         * @param right 右侧列名称
         * @return
         */
        public CaseBuilder eq(Object left, Object right) {
            if (left instanceof String) {
                left = StringUtils.camelToUnderline((String) left);
            }
            if (right instanceof String) {
                right = StringUtils.camelToUnderline(String.valueOf(right));
            }
            sb.append(left).append(EQ).append(right);
            return this;
        }

        /**
         * 规定左侧为主表的列
         * ，右侧为从表的列，不可以写反
         *
         * @param left  左侧列名称
         * @param right 右侧列名称
         * @return
         */
        public CaseBuilder ne(String left, Object right) {
            left = StringUtils.camelToUnderline(left);
            if (right instanceof String) {
                right = StringUtils.camelToUnderline(String.valueOf(right));
            }
            sb.append(left).append(NE).append(right);
            return this;
        }

        /**
         * 关联查询一般是列关联，如果条件里面有值等式，要做特殊处理，目前还不支持
         *
         * @param left
         * @param right
         * @return
         */
        @Deprecated
        public CaseBuilder gt(String left, Object right) {
            sb.append(left).append(GT).append(right);
            return this;
        }

        /**
         * 关联查询一般是列关联，如果条件里面有值等式，要做特殊处理，目前还不支持
         *
         * @param left
         * @param right
         * @return
         */
        @Deprecated
        public CaseBuilder gtEq(String left, Object right) {
            sb.append(left).append(GT_EQ).append(right);
            return this;
        }

        /**
         * 关联查询一般是列关联，如果条件里面有值等式，要做特殊处理，目前还不支持
         *
         * @param left
         * @param right
         * @return
         */
        @Deprecated
        public CaseBuilder lt(String left, Object right) {
            sb.append(left).append(LT).append(right);
            return this;
        }

        /**
         * 关联查询一般是列关联，如果条件里面有值等式，要做特殊处理，目前还不支持
         *
         * @param left
         * @param right
         * @return
         */
        @Deprecated
        public CaseBuilder ltEq(String left, Object right) {
            sb.append(left).append(LT_EQ).append(right);
            return this;
        }
    }
}
