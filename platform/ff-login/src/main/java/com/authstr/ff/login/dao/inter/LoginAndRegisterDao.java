package com.authstr.ff.login.dao.inter;

import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.web.dao.InterfaceDao;

public interface LoginAndRegisterDao extends InterfaceDao{

	BaseUser getUser(String loginname);

}
