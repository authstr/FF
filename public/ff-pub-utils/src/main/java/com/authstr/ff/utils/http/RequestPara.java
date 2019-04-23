package com.authstr.ff.utils.http;


import com.authstr.ff.utils.exception.MsgException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 将reques的请求参数进行封装,方便传递与调用
 * @author 杨梦豪
 * @time 2019年2月19日17:44:37
 */
public class RequestPara {
    private HttpServletRequest request =null;

    //储存request里的参数,数组转String
    private Map<String,String> parameter=null;

    //储存其他参数
    private Map<String,Object> parameterObject=new HashMap<String,Object>();

    //储存Integer类型的参数
    private Map<String,Integer> parameterInteger=new HashMap<>();

    public RequestPara(HttpServletRequest request){
        setRequest(request);
    }
    public  RequestPara (){
        parameter=new HashMap<String,String>();
    };

    public HttpServletRequest getRequest() {
        return request;
    }


    public void setRequest(HttpServletRequest request) {
        this.request = request;
        parameter=mapTypeTrans(request.getParameterMap());
    }

    /**
     * 将Map<String,String[]>转换为Map<String,String> 类型
     * @param map
     * @return
     */
    public Map<String,String> mapTypeTrans(Map<String,String[]> map ){
        Map<String,String> res=new HashMap<String,String>();
        for (Map.Entry<String,String[]> temp: map.entrySet()){
            res.put(temp.getKey(),arrayToString(temp.getValue()));
        }
        return res;
    }

    /**
     * 将String数组中转换为 String
     * @param array
     * @return
     */
    public String  arrayToString(String[] array){
        StringBuffer res =new StringBuffer();
        if(array==null){
            return null;
        }
        for(int i=0;i<array.length;i++){
            res.append(array[i]);
            if((array.length-1)!=i){
                res.append(",");
            }
        }
        return res.toString();
    }

    /**
     * 获取一个指定key的String类型参数
     * @param key
     * @return
     */
    public String get(String key){
        return parameter.get(key);
    }

    /**
     * 添加一个String类型参数
     * @param key
     * @param value
     */
    public void add(String key,String value){
        parameter.put(key,value);
    }

    /**
     * 获取一个指定key的String[]类型参数
     * @param key
     * @return
     */
    public String[] getArray(String key){
        return getRequest().getParameterMap().get(key);
    }

    /**
     * 获取一个Object类型参数
     * @param key
     * @return
     */
    public Object getObject(String key){
        return parameterObject.get(key);
    }

    /**
     * 添加一个Object类型参数
     * @param key
     * @param value
     */
    public void addObject(String key,Object value){
        parameterObject.put(key,value);
    }

    /**
     * 获取一个Integer类型参数
     * @param key
     * @return
     */
    public Integer getInteger(String key){
        Integer res=parameterInteger.get(key);
        if(res==null){
            //尝试从parameter获取数据
            res=stringToInteger(parameter.get(key));
        }
        if(res==null){
            //尝试从parameterObject获取数据
            res=objectToInteger(parameterObject.get(key));

        }
        return res;
    }

    /**
     * 添加一个Integer类型参数
     * @param key
     * @param value
     */
    public void addInteger(String key,Integer value){
        parameterInteger.put(key,value);
    }

    /**
     * 从参数map中获取指定key的值,验证字符串是否存在
     * @param key
     * @return
     */
    public  boolean hasKeyText(String key) {
       return  hasText(get(key));
    }


    /**
     * 验证指定key的参数值是否存在,不存在抛出异常
     * @param key
     * @return
     */
    public boolean verifyExistByKey(String key){
        //有值,返回true
        if(hasText(get(key))){
            return true;
        }
        //没值,尝试去parameterInteger里获取
        if(parameterInteger.get(key)!=null){
            return true;
        }
        //尝试从object获取
        if(getObject(key)!=null){
            return true;
        }
        throw  new MsgException("参数缺失:"+key);
    }


    /**
     * 判断一个字符串是否存在
     * @param str
     * @return
     */
    public boolean hasText(String str){
        if (str != null && !"".equals(str)) {
            return str.trim().length() > 0;
        } else {
            return false;
        }
    }



    /**
     * String类型转为Integer类型
     * @param value
     * @return
     */
    public Integer stringToInteger(String value){
        if(value==null){
            return null;
        }
        try {
            Integer res=Integer.valueOf(value);
            return res;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Object类型转为Integer类型
     * @param value
     * @return
     */
    public Integer objectToInteger(Object value){
        if(value==null){
            return null;
        }
        return stringToInteger(value.toString());
    }





}
