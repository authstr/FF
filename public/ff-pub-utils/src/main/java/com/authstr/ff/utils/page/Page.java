package com.authstr.ff.utils.page;

import java.util.List;

public interface Page{
    public static final Integer DEFAULT_PAGE_ROWS = 20;

    public Integer getTotal();

    public List getRecord();

    public void setTotal(Integer var1);

    public void setRecord(List var1);

    public Integer getPageTotal();

    public void setRows(Integer var1);

    public Integer getRows();

    public void setPage(Integer var1);

    public Integer getPage();

    public Integer getFirst();

    public String toJson();
}

