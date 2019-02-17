package com.authstr.sd.basic.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.encryption.Md5Salt;
import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.page.Page;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.sevice.AbstractService;
import com.authstr.sd.basic.dao.inter.UserDao;
import com.authstr.sd.basic.service.inter.UserService;
import com.authstr.sd.basic.utils.LoginInfoEnum;
@Service
public class UserServiceImpl extends AbstractService implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public Page query(QueryCommonPage query){
		return userDao.query(query);
	}

	
}
