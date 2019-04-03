package com.authstr.ff.utils.page;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @time 2018年10月15日 上午9:57:50
 * @author authstr
 * @version V1.0
 */

public class AbstractPage implements Page{
	private Integer total;//总数
	private Integer pageTotal;//总页数
    private Integer rows;//每页条数
    private Integer page;//要查询的页
    private List record;//该页数据

    @Override
    public Integer getTotal() {
        return total;
    }
    @Override
    public Integer getFirst() {
        return (getPage() - 1) * getRows();
    }
    @Override
    public Integer getRows() {
        if (rows == null) {
            rows = DEFAULT_PAGE_ROWS;
        }
        return rows;
    }
    @Override
    public void setTotal(Integer total) {
        this.total = total;
    }
    @Override
    public String toJson() {
        return JSON.toJSONString((Object)this);
    }
    
    public void setPageTotal(Integer pageTotal) {
    	 this.pageTotal = pageTotal;
    }
    @Override
    public Integer getPageTotal() {
        pageTotal = getTotal() % rows == 0 ? Integer.valueOf(getTotal() / rows) : Integer.valueOf(getTotal() / rows + 1);
        return pageTotal;
    }
    @Override
    public void setPage(Integer pageNo) {
        page = pageNo;
    }
    @Override
    public Integer getPage() {
        if (page == null || page < 1) {
            page = 1;
        }
        return page;
    }
    @Override
    public void setRows(Integer length) {
        rows = length;
    }
    
    @Override
    public List getRecord() {
        return this.record;
    }
    @Override
    public void setRecord(List record) {
        this.record = record;
    }
    
}
