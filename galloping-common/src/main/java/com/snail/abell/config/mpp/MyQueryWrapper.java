package com.snail.abell.config.mpp;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Abell
 * @date ：Created in 2020/6/5 11:51
 * @description：条件构造器，重写字符串转换方法
 * @modified By：
 * @version: 1.0
 */
public class MyQueryWrapper<T> extends QueryWrapper<T> {
    /**
     * 关联查询构造器
     */
    private final List<JoinBuilder> joinBuilder = new ArrayList<>();


    /**
     * 获取 columnName
     *
     * @param column
     */
    @Override
    protected String columnToString(String column) {
        return StringUtils.camelToUnderline(column);
    }


    public static<T> MyQueryWrapper<T> query(){
        return new MyQueryWrapper<T>();
    }


    /**
     * 关联查询构造
     * @param builder
     * @return
     */
    public  MyQueryWrapper<T> addJoin(JoinBuilder builder){
        this.joinBuilder.add(builder);
        return this;
    }

    public List<JoinBuilder> getJoinBuilder() {
        return joinBuilder;
    }
    /**
     * 排序
     * @param sorts
     * @return
     */
//    public QueryWrapper<T> sort(List<Sort> sorts){
//        if(!CollectionUtils.isEmpty(sorts)){
//            sorts.forEach(item->{
//                orderBy(item.getSortField()!=null,"asc".equals(item.getSortValue()),item.getSortField());
//            });
//        }
//        return this;
//    }

}

