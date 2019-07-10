package com.authstr.ff.basic.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.authstr.ff.basic.dao.inter.UserDao;
import com.authstr.ff.utils.http.RequestPara;
import org.springframework.stereotype.Repository;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.web.dao.AbstractDao;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.page.QueryCommonPage;

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

	@Override
	public List<Map> get( RequestPara para){
		StringBuffer sql = new StringBuffer();
		Map kv = new HashMap();
		sql.append("select  a.*");
		sql.append(" FROM base_user a ");
		sql.append(" where 1=1 ");
		if(para.hasKeyText("id")){
			sql.append(" and id=:id ");
			kv.put("id", para.get("id"));
		}
		return super.getByMapSQL(sql.toString(),kv,Map.class);
	}
	
	
	
}
