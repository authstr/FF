package com.authstr.sd.basic.controller;

import java.util.Map;

import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.http.RequestPara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

		@Value("${logging.config}")
		String a;

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
			System.out.println("ddd");
			this.log.info("11111111111111111");
			this.log.error("2222");
			this.log.debug("333");
			this.log.trace("444");
			System.out.println(a);
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

}
