package com.authstr.ff.utils.web.dao;

import java.util.HashMap;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

/**
 * hibernate的转换类,将数据库的返回值封装成map
 * @author authstr
 *
 */
public class MapResultTransformer extends AliasedTupleSubsetResultTransformer {
    public static final MapResultTransformer MAP_INSTANCE = new MapResultTransformer();

    private Object readResolve() {
        return MAP_INSTANCE;
    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        HashMap<String, Object> result = new HashMap<String, Object>(tuple.length);
        for(int i=0;i<aliases.length;i++){
            String alias = aliases[i];
            if (alias != null) {
                result.put(alias, tuple[i]);
            }
        }
        return result;
    }
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }
}

