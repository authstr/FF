package com.authstr.ff.utils.page;

import java.util.List;

/**
 * 定义查询出的一页数据应包含的内容
 * 2019年4月5日11:41:09
 * authstr
 */
public interface ReturnPage {
    //默认的每页数据行数
    Integer DEFAULT_PAGE_ROWS = 20;
    //默认的页码数
    Integer DEFAULT_PAGE_NUM = 1;

    Integer getTotal();

    List getRecord();

    void setTotal(Integer var1);

    void setRecord(List var1);

    Integer getPageTotal();

    void setRows(Integer var1);

    Integer getRows();

    void setPage(Integer var1);

    Integer getPage();

    Integer getFirst();

    String toJson();
}

