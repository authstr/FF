package com.authstr.ff.basic.dao.impl;

import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.web.dao.AbstractDao;
import com.authstr.ff.basic.dao.inter.BaseCodeDao;
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
		if(para.hasKeyText("name")){
			sql.append(" and name = :name ");
			kv.put("name", para.get("name"));
		}
		sql.append(" order by a.gmt_create desc ");
		return super.queryByParamAndValue(sql.toString(), kv, query);
	}

	/**
	 * 查询出编码的名称,该编码名称的编码数量
	 * @param para
	 * @return
	 */
	@Override
	public List<Map> getCodeName(RequestPara para){
		StringBuffer sql = new StringBuffer();
		Map kv = new HashMap();
		sql.append("select  a.`name`,COUNT(a.id) AS count ");
		sql.append(" FROM base_code a ");
		sql.append(" where 1=1 ");
		if(para.hasKeyText("name")){
			sql.append(" and name like :name ");
			kv.put("name", "%"+para.get("name")+"%");
		}
		sql.append(" GROUP BY a.`name` ");
		sql.append(" order by a.id desc ");
		return super.getByMapSQL(sql.toString(), kv,Map.class);
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
