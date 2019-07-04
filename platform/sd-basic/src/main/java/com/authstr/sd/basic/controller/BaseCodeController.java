package com.authstr.sd.basic.controller;

import com.authstr.ff.model.platform.base.BaseCode;
import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.controller.AbstractController;
import com.authstr.sd.basic.service.inter.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.authstr.ff.utils.base.CollectionUtils.listGetOneData;

@RestController
@RequestMapping("basecode/v1")
public class BaseCodeController extends AbstractController {
		@Autowired
		private BaseCodeService baseCodeService;

		@RequestMapping("query")
		 public Map query(QueryCommonPage query, HttpServletRequest request) {
			Map m = super.success();
			RequestPara para=new RequestPara(request);
			m.put("page", baseCodeService.query(query,para));
			return m;
		}

		@RequestMapping("save")
		public Map save(BaseCode baseCode) {
			Map m = super.success();
			m.put("msg", baseCodeService.save(baseCode));
			return m;
		}

		@RequestMapping("remove")
		public Map remove(HttpServletRequest request) {
			Map m = super.success();
			RequestPara para=new RequestPara(request);
			m.put("data", baseCodeService.removeIds(BaseUser.class,para.getArray("ids")));
			return m;
		}

		@RequestMapping("getById")
		public Map getById(String  id) {
			Map m = super.success();
			RequestPara para=new RequestPara();
			para.add("id",id);
			m.put("data", listGetOneData(baseCodeService.getByPara(para)));
			return m;
		}

}
