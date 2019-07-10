package com.authstr.ff.basic.dao.inter;

import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.dao.InterfaceDao;

import java.util.List;
import java.util.Map;

public interface UserDao extends InterfaceDao{

	/**
	 * 
	 * @param query
	 * @return
	 * @time 2018年10月27日 下午5:59:24
	 * @author authstr
	 */
	ReturnPage query(QueryCommonPage query, RequestPara para);


    List<Map> get(RequestPara para);
}
