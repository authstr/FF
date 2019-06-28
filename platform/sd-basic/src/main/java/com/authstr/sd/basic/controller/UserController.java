package com.authstr.sd.basic.controller;

import java.util.Map;

import com.authstr.ff.model.platform.base.BaseCode;
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
import javax.transaction.Transactional;

import static com.authstr.ff.utils.base.CollectionUtils.listGetOneData;

@RestController
@RequestMapping("user")
public class UserController extends AbstractController {
		@Autowired
		UserService userService;

		@RequestMapping("a")
		@Transactional
		public String  ae(){
			System.out.println("进入了(01)............................");
//			User a=new User();
//			a.setUsername("aaaa3");
//			a.setPassword("dasd");
//			UserExtend ue=new UserExtend();
//			ue.setInterest("eeeeeeee");
			BaseCode bc=new BaseCode();
			bc.setName("测试6");
			bc.setCode_index(1);
			bc.setCode_value("222");

			BaseUser bu=new BaseUser();
			bu.setUsername("NNNNN");
			bu.setPassword("111111");
			bu=userService.get(BaseUser.class,7);




//			bu.setSex(bc);
//			sex.setName("男111");
//            userService.save(bc);
//            userService.save(bu);
//            userService.remove(bu);


//			bu.setRemark("aaa2222");

			//bc.setCreator_user(bu);
//			userService.save(bc);
//			bu.setUsername("7777");
//			bu.setPassword("46645");
//			bu.setSex(bc);
			//bc.setCreator_user(bu);
			//userService.save(bu);
//			ue.setUser(a);
//			userService.save(ue);

			//a.setUserExtend(ue);
			//userService.save(a);


			//UserExtend eee=userService.get(UserExtend.class,1);
			//System.out.println(eee);
			return "测试测试";
		}
		
		@RequestMapping("query")
		 public Map query(QueryCommonPage query, HttpServletRequest request) {
			Map m = super.success();
//			TRACE < DEBUG < INFO < WARN < ERROR < FATAL
			this.log.info("info");
			this.log.warn("warn");
			this.log.error("error");
			this.log.debug("debug");
			this.log.trace("trace");
			RequestPara para=new RequestPara(request);
			m.put("page", userService.query(query,para));
			return m;
		}

		@RequestMapping("save")
		public Map save(BaseUser user) {
			Map m = super.success();
			m.put("msg", userService.save(user));
			return m;
		}

		@RequestMapping("remove")
		public Map remove(HttpServletRequest request) {
			Map m = super.success();
			RequestPara para=new RequestPara(request);
			m.put("data", userService.removeIds(BaseUser.class,para.getArray("ids")));
			return m;
		}

		@RequestMapping("getById")
		public Map getById(String  id) {
			Map m = super.success();
			RequestPara para=new RequestPara();
			para.add("id",id);
			m.put("data", listGetOneData(userService.getByPara(para)));
			return m;
		}

}
