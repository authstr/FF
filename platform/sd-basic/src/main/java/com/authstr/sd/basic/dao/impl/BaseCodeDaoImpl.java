package com.authstr.sd.basic.dao.impl;

import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.web.dao.AbstractDao;
import com.authstr.sd.basic.dao.inter.BaseCodeDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseCodeDaoImpl extends AbstractDao implements BaseCodeDao {

	@Override
	public ReturnPage query(QueryCommonPage query, RequestPara para){
		StringBuffer sql = new StringBuffer();
		Map kv = new HashMap();
		sql.append("select  a.*");
		sql.append(" FROM base_code a ");
		sql.append(" where 1=1 ");
		if(StringUtils.hasText(query.getSearch())){
			sql.append(" and (a.name like :k or a.code_value like :k or a.type like :k) ");
			kv.put("k", "%"+query.getSearch()+"%");
		}
		sql.append(" order by a.gmt_create desc ");
		return super.queryByParamAndValue(sql.toString(), kv, query);
	}

	@Override
	public List<Map> get( RequestPara para){
		StringBuffer sql = new StringBuffer();
		Map kv = new HashMap();
		sql.append("select  a.*");
		sql.append(" FROM base_code a ");
		sql.append(" where 1=1 ");
		if(para.hasKeyText("id")){
			sql.append(" and id=:id ");
			kv.put("id", para.get("id"));
		}
		return super.getByMapSQL(sql.toString(),kv,Map.class);
	}
	
	
	
}
