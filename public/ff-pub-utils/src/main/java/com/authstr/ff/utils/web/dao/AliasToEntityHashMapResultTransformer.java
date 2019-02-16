package com.authstr.ff.utils.web.dao;

import java.util.HashMap;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

/**
 * hibernate的转换类,将数据库的返回值封装成map
 * @author authstr
 *
 */
public class AliasToEntityHashMapResultTransformer
extends AliasedTupleSubsetResultTransformer {
    public static final AliasToEntityHashMapResultTransformer INSTANCE = new AliasToEntityHashMapResultTransformer();

    private AliasToEntityHashMapResultTransformer() {
    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        HashMap<String, Object> result = new HashMap<String, Object>(tuple.length);
        int i = 0;
        while (i < tuple.length) {
            String alias = aliases[i];
            if (alias != null) {
                result.put(alias, tuple[i]);
            }
            ++i;
        }
        return result;
    }

    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

