package com.authstr.sd.basic.service.impl;

import javax.transaction.Transactional;

import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.encryption.Md5Salt;
import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.exception.ErrorException;
import com.authstr.ff.utils.http.RequestPara;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.sevice.AbstractService;
import com.authstr.sd.basic.dao.inter.UserDao;
import com.authstr.sd.basic.service.inter.UserService;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

@Service
public class UserServiceImpl extends AbstractService implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public ReturnPage query(QueryCommonPage query, RequestPara para){
		return userDao.query(query,para);
	}

	@Transactional
	@Override
	public String save(BaseUser user){
		Assert.isTrue(StringUtils.hasText(user.getUsername()),"用户名不能为空");
		Assert.isTrue(StringUtils.hasText(user.getPassword()),"密码不能为空");
		Integer id=(Integer) super.save(user);
		//密码md5加密后,用数据自身id作为盐,再次加密
		String md5Password= null;
		try {
			md5Password = Md5Salt.sec(String.valueOf(id), DigestUtils.md5Hex(user.getPassword()));
		} catch (Exception e) {
			throw new ErrorException("-3","加密出错!");//异常提示实际应写在枚举里
		}
		BaseUser afterUser=super.get(BaseUser.class,id);
		Assert.notNull(afterUser);
		afterUser.setPassword(md5Password);
		super.updata(afterUser);
		return "保存成功";
	}
}
