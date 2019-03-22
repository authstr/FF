package com.authstr.sd.basic.dao.inter;

import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.Page;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.dao.InterfaceDao;

public interface UserDao extends InterfaceDao{

	/**
	 * 
	 * @param query
	 * @return
	 * @time 2018年10月27日 下午5:59:24
	 * @author authstr
	 */
	Page query(QueryCommonPage query, RequestPara para);

	

}
