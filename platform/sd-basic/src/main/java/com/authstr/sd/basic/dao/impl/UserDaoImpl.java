package com.authstr.sd.basic.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.authstr.ff.utils.http.RequestPara;
import org.springframework.stereotype.Repository;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.web.dao.AbstractDao;
import com.authstr.ff.utils.page.Page;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.sd.basic.dao.inter.UserDao;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {
	
	
	@Override
	public Page query(QueryCommonPage query, RequestPara para){
		StringBuffer sql = new StringBuffer();
		Map kv = new HashMap();
		sql.append("select  a.*");
		sql.append(" FROM base_user a ");
		if(StringUtils.hasText(query.getKeyword())){
			sql.append(" and (a.name like :k) ");
			kv.put("k", "%"+query.getKeyword()+"%");
		}
		return super.queryByParamAndValueSqlMap(sql.toString(), kv, query);
	}
	
	
	
}
