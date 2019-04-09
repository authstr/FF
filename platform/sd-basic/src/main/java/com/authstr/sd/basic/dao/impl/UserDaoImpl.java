package com.authstr.sd.basic.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.authstr.ff.utils.http.RequestPara;
import org.springframework.stereotype.Repository;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.web.dao.AbstractDao;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.sd.basic.dao.inter.UserDao;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {
	
	
	@Override
	public ReturnPage query(QueryCommonPage query, RequestPara para){
		StringBuffer sql = new StringBuffer();
		Map kv = new HashMap();
		sql.append("select  a.*");
		sql.append(" FROM base_user a ");
		sql.append(" where 1=1 ");
		if(StringUtils.hasText(query.getSearch())){
			sql.append(" and (a.username like :k) ");
			kv.put("k", "%"+query.getSearch()+"%");
		}
		return super.queryByParamAndValue(sql.toString(), kv, query);
	}
	
	
	
}
