package com.authstr.sd.login.service.impl;

import javax.transaction.Transactional;

import com.authstr.ff.utils.http.CookieUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.encryption.Md5Salt;
import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.web.sevice.AbstractService;
import com.authstr.sd.login.dao.inter.LoginAndRegisterDao;
import com.authstr.sd.login.service.inter.LoginAndRegisterService;
import com.authstr.sd.login.utils.LoginInfoEnum;
import com.authstr.sd.login.utils.RegexVerify;
@Service
public class LoginAndRegisterServiceImpl extends AbstractService implements LoginAndRegisterService{
	@Autowired
	private LoginAndRegisterDao loginAndRegisterDao;

	
	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public BaseUser validate(String username,String password){
		
		Assert.isTrue(StringUtils.hasText(username,password),
				LoginInfoEnum.Name_Or_Pwd_Is_Null.getCode(),LoginInfoEnum.Name_Or_Pwd_Is_Null.getExplain());//不为空检查
		BaseUser u=loginAndRegisterDao.getUser(username);
		Assert.isTrue(u!=null,
				LoginInfoEnum.Name_Or_Pwd_Is_Wrong.getCode(),LoginInfoEnum.Name_Or_Pwd_Is_Wrong.getExplain() );//检查有没有该账户
//		Assert.isTrue(u.getStatus()!=0,
//				LoginInfoEnum.User_Is_Enable.getCode(),LoginInfoEnum.User_Is_Enable.getExplain());//检查账户是否可用
		/*
		 * 密码加密流程
		 * 前端对密码进行md5加密,发到后台 A
		 * 获取该账号用户的id(int),进行md5加密 B
		 * 将B作为盐,根据B的 hashcode,在四个加盐方式中选择一个
		 * 最后返回加盐后的md5码
		 * */
		String secpwd =Md5Salt.sec(u.getId(), password);
		Assert.isTrue(u.getPassword().equalsIgnoreCase(secpwd),
				LoginInfoEnum.Name_Or_Pwd_Is_Wrong.getCode(),LoginInfoEnum.Name_Or_Pwd_Is_Wrong.getExplain());//验证密码是否正确
		return u;
	}
	
	@Transactional
	@Override
	public String register( String username, String password, String email){
		//验证昵称
		Assert.isTrue(username.length()>1&&username.length()<10,"昵称应为2~9长度的字符");
		//验证密码
		Assert.isTrue(RegexVerify.regexPassword(password),"密码长度应在6-18之间，只包含字母、数字和下划线");
		//验证邮箱
		Assert.isTrue(RegexVerify.regexEmail(email),"邮箱格式不正确");
		//创建对象
		BaseUser u=new BaseUser();
		u.setUsername(username);
		//u.setPassword(password);
		u.setEmail(email);
		//查重
		Assert.isTrue(loginAndRegisterDao.isUnique(u, "username"),"该用户名已被占用!");
		//保存用户
		Integer userId=(Integer) loginAndRegisterDao.save(u);
		//获取用户对象
		BaseUser user= loginAndRegisterDao.get(BaseUser.class,userId);
		//密码加密
		String secpwd =Md5Salt.sec(user.getId(), DigestUtils.md5Hex(password));
		//保存密码
		user.setPassword(secpwd);
		loginAndRegisterDao.save(user);
		//发送激活邮件


//		Integer id= (Integer) loginDao.save(u);
//		String password=Md5Salt.sec(id, u.getPassword());
//		u.setPassword(password);
//		loginDao.update(u);
		return "成功";
	}

	
}
