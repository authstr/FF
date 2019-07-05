package com.authstr.ff.basic.dao.inter;

import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.web.dao.InterfaceDao;

import java.util.List;
import java.util.Map;

public interface BaseCodeDao extends InterfaceDao{

	ReturnPage query(QueryCommonPage query, RequestPara para);

    List<Map> get(RequestPara para);
}
