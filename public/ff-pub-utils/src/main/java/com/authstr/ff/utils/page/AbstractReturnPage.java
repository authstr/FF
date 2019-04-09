package com.authstr.ff.utils.page;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 记录查询出的一页数据
 * @time 2018年10月15日 上午9:57:50
 * @author authstr
 */
public class AbstractReturnPage implements ReturnPage {
    //数据总数
	private Integer total;
    //总页数
	private Integer pageTotal;
    //每页条数
    private Integer rows;
    //查询的页
    private Integer page;
    //该页数据
    private List record;

    public AbstractReturnPage(){}

    /**
     * 通过查询参数对象创建返回页
     * @param queryPage
     */
    public AbstractReturnPage(QueryPage queryPage){
        if (queryPage.getRows()!=null){
            rows=queryPage.getRows();
        }else{
            rows=DEFAULT_PAGE_ROWS;
        }
        if(queryPage.getPage()!=null){
            page=queryPage.getPage();
        }else{
            page=DEFAULT_PAGE_NUM;
        }
    }

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
