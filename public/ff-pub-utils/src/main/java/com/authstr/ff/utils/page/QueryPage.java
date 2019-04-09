package com.authstr.ff.utils.page;


/**
 * 发起查询请求时,定义进行分页需要的相关参数
 * 2019年4月5日11:36:41
 * authstr
 */
public interface QueryPage {
     Integer getRows();
     void setRows(Integer rows);
     Integer getPage();
     void setPage(Integer page);
}
