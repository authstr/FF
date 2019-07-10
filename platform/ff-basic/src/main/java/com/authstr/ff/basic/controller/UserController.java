package com.authstr.ff.basic.controller;

import java.util.Map;
import com.authstr.ff.model.platform.base.BaseCode;
import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.http.RequestPara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import com.authstr.ff.basic.service.inter.UserService;


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
			m.put("data", CollectionUtils.lastElement(userService.getByPara(para)));
			return m;
		}

}
