package com.authstr.sd.login.dao.impl;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.base.ObjectUtils;
import com.authstr.ff.utils.web.dao.AbstractDao;
import com.authstr.sd.login.dao.inter.LoginAndRegisterDao;





@Repository
public class LoginAndRegisterDaoImpl extends AbstractDao implements LoginAndRegisterDao {
	
	@Override
	public BaseUser getUser(String loginname) {
		String sql = " SELECT b.id,b.username,b.`password`,CASE WHEN b.`status_id` IS NULL THEN 0 ELSE	b.`status_id` END status_id FROM base_user b WHERE b.username =? ";
		List<BaseUser> u = super.getByValuesSql(sql, new Object[]{loginname}, BaseUser.class);
		if(!ObjectUtils.isExist(u))return null;
		return u.get(0);
	}
	
	
	@Transactional
	public void a(){
		String sql="INSERT INTO ff_user (username, password) VALUES ('value1', 'value2')";
		BaseUser u=new BaseUser();
		u.setUsername("cccc111");
//		String s=(String) this.save(u);
		System.out.println("到"+this.save(u));
	}
}
