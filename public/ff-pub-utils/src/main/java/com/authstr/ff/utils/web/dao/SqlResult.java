package com.authstr.ff.utils.web.dao;

/**
 * 实体类,用于储存sql语句的结果集
 * 2019年4月8日14:19:23
 * authstr
 */
public class SqlResult  {
    private String[] aliases;
    private Object[] tuple;

    public String[] getAliases() {
        return aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public Object[] getTuple() {
        return tuple;
    }

    public void setTuple(Object[] tuple) {
        this.tuple = tuple;
    }
}

