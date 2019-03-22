package com.authstr.sd.basic.controller;

import java.util.Map;

import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.http.RequestPara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authstr.ff.utils.login.LoginInfo;
import com.authstr.ff.utils.login.LoginThreadLocal;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.controller.AbstractController;
import com.authstr.sd.basic.dao.impl.UserDaoImpl;
import com.authstr.sd.basic.service.inter.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController extends AbstractController {
		@Autowired
		UserService userService;
		
		@RequestMapping("a")
		public String  ae(){
			System.out.println("进入了(01)............................");
			LoginInfo ee=LoginThreadLocal.get();
			System.out.println(ee);
			String aaa=ee.getUsername();
			System.out.println(aaa);
			return "测试测试";
		}
		
		@RequestMapping("query")
		 public Map query(QueryCommonPage query, HttpServletRequest request) {
			Map m = super.success();
			RequestPara para=new RequestPara(request);
			m.put("page", userService.query(query,para));
			return m;
		}

		@RequestMapping("save")
		public Map save(BaseUser user) {
			Map m = super.success();
			m.put("data", userService.save(user));
			return m;
		}

		
		@RequestMapping("../a")
		public String  ac(){
			return "返回了";
		}
		
}
