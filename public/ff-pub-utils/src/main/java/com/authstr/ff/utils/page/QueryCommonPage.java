/*
 * Decompiled with CFR 0_132.
 */
package com.authstr.ff.utils.page;


/**
 * 发起查询请求时,进行分页需要的基本参数
 * 2019年4月5日11:36:41
 * authstr
 */
public class QueryCommonPage implements QueryPage {
    //要查那页数据
    private Integer page;
    //每页有几条数据
    private Integer rows;
    //搜索值
    private String search;

    public Integer getRows() {
        return rows;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public String getSearch() {
        return search;
    }
    public void setSearch(String search) {
        this.search = search;
    }
}

