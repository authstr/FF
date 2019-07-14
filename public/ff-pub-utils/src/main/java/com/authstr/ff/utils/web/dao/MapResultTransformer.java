package com.authstr.ff.utils.web.dao;

import java.sql.Timestamp;
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

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        HashMap<String, Object> result = new HashMap<String, Object>(tuple.length);
        for(int i=0;i<aliases.length;i++){
            String alias = aliases[i];
            if (alias != null) {
                Object value= tuple[i];
                if(value instanceof Timestamp){
                    //如果类型是sql日期,将其转换为Long格式的时间值
                    value=((Timestamp) value).getTime();
                }else{

                }
                result.put(alias, value);
            }
        }
        return result;
    }
    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }
}

