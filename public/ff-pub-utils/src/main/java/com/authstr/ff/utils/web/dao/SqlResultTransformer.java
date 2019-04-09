package com.authstr.ff.utils.web.dao;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import java.util.*;

/**
 * hibernate的转换类,将数据库的返回值封装成SqlResult对象
 * @time 2019年4月8日15:21:27
 * @author authstr
 *
 */
public class SqlResultTransformer extends AliasedTupleSubsetResultTransformer {

    public static final SqlResultTransformer SQL_INSTANCE = new SqlResultTransformer();

    private Object readResolve() {
        return SQL_INSTANCE;
    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        SqlResult res=new SqlResult();
        res.setAliases(aliases);
        res.setTuple(tuple);
        return res;
    }

    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }
}

