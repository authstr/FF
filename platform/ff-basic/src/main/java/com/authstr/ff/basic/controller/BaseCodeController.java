package com.authstr.ff.basic.controller;

import com.authstr.ff.model.platform.base.BaseCode;
import com.authstr.ff.model.platform.base.BaseConstant;
import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.controller.AbstractController;
import com.authstr.ff.basic.service.inter.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

import static com.authstr.ff.utils.base.CollectionUtils.listGetOneData;

@RestController
@RequestMapping("base_code/")
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
		@RequestMapping("get_code_name")
		public Map getCodeName( HttpServletRequest request) {
			Map m = super.success();
			RequestPara para=new RequestPara(request);
			m.put("data", baseCodeService.getCodeName(para));
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



		@RequestMapping("save_many")
		public Map saveMany(BaseCode baseCode,HttpServletRequest request) {
			Map m = super.success();
			RequestPara para=new RequestPara(request);
			m.put("msg", baseCodeService.saveManyCode(baseCode,para));
			return m;
		}
		@RequestMapping("save_one")
		public Map saveOne(BaseCode baseCode) {
			Map m = super.success();
			m.put("msg", baseCodeService.saveOneCode(baseCode));
			return m;
		}

		@RequestMapping("remove")
		public Map remove(HttpServletRequest request) {
			Map m = super.success();
			RequestPara para=new RequestPara(request);
			m.put("data", baseCodeService.removeIds(BaseCode.class,para.getArray("ids")));
			return m;
		}

		/**
		 * 启用指定的编码
		 * @param ids
		 * @return
		 */
		@RequestMapping("enabled_code")
		public Map enabledCode(Integer[] ids) {
			Map m = super.success();
			m.put("msg", baseCodeService.enabledAndDisabled(ids, BaseConstant.COMMON_STATUS_NORMAL));
			return m;
		}

		/**
		 * 禁用指定的编码
		 * @param ids
		 * @return
		 */
		@RequestMapping("disabled_code")
		public Map disabledCode(Integer[] ids) {
			Map m = super.success();
			m.put("msg", baseCodeService.enabledAndDisabled(ids, BaseConstant.COMMON_STATUS_DISABLED));
			return m;
		}

}
