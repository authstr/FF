package com.authstr.ff.basic.service.impl;

import com.authstr.ff.basic.dao.inter.BaseCodeDao;
import com.authstr.ff.model.platform.base.BaseCode;
import com.authstr.ff.model.platform.base.BaseConstant;
import com.authstr.ff.utils.base.SpringUtils;
import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.exception.MsgException;
import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.model.ModelUtils;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.web.sevice.AbstractService;
import com.authstr.ff.basic.service.inter.BaseCodeService;
import freemarker.template.utility.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


@Service
public class BaseCodeServiceImpl extends AbstractService implements BaseCodeService {
	@Autowired
	private BaseCodeDao baseCodeDao;
	@Override
	public ReturnPage query(QueryCommonPage query, RequestPara para){
		return baseCodeDao.query(query,para);
	}

	@Override
	public List<Map> getCodeName(RequestPara para){
		return baseCodeDao.getCodeName(para);
	}

	@Override
	public List<Map> getByPara(RequestPara para){
		return baseCodeDao.get(para);
	}

	/**
	 * 保存一个编码信息
	 * @param model
	 * @return
	 */
	@Transactional
	@Override
	public String saveOneCode(BaseCode model){
		//如果是添加,状态正常
		if(ModelUtils.isNew(model)){
			model.setStatus(BaseConstant.COMMON_STATUS_NORMAL);
		}
		//类型如果没有,填无
		if(model.getType()==null){
			model.setType(BaseConstant.BASE_CODE_TYPE_NULL);
		}
		//如果是新添加的且没有系统信息,设置为 未知系统
		if(ModelUtils.isNew(model)&&model.getSystem_name()==null){
//			String system_name= SpringUtils.getEnvironment().getProperty(BaseConstant.SYSTEM_CODE_KEY,BaseConstant.SYSTEM_CODE_DEFAULT);
			model.setSystem_name(BaseConstant.SYSTEM_CODE_DEFAULT);
		}
		Assert.isTrue(StringUtils.hasText(model.getName()),"编码名称不能为空");
		Assert.isTrue(StringUtils.hasText(model.getCode_value()),"编码值不能为空");
		Assert.isTrue(StringUtils.hasText(model.getCode_english()),"英文编码值不能为空");
		Assert.isTrue(null !=model.getCode_index(),"编码序号不能为空");
		Assert.isTrue(model.getCode_index()>0,"编码序号需是正整数");
		Assert.isTrue(null !=model.getType(),"编码类型不能为空");
		Assert.isTrue(null !=model.getStatus(),"状态不能为空");
		super.saveOrUpdate(model);
		return "保存成功!";
	}

	/**
	 * 保存多个编码信息
	 * @param model
	 * @param para
	 * @return
	 */
	@Transactional
	@Override
	public String saveManyCode(BaseCode model, RequestPara para){
		Assert.isTrue( model!=null,"无法获取要保存的对象");
		String[] values=para.getArray("code_value");
		Assert.isTrue(values!=null&&values.length!=0 ,"要保存的编码值不能为空");
		//遍历值数组,创建多个编码对象,设置值并进行保存
		for(int i=0;i<values.length;i++){
			String code_value=values[i];
			if(StringUtils.hasText(code_value)){
				BaseCode new_code=null;
				try {
					new_code=(BaseCode) BeanUtils.cloneBean(model);
				} catch (Exception e) {
					throw new MsgException("无法创建多个日志对象");
				}
				new_code.setCode_value(code_value);
				new_code.setCode_index((i+1));
				//保存
				saveOneCode(new_code);
			}
		}
		return "["+values.length+"]个编码保存成功!";
	}

	/**
	 * 启用或禁用 编码
	 * @param ids
	 * @param status
	 * @return
	 */
	@Override
	@Transactional
	public String enabledAndDisabled(Integer[] ids, Integer status){
		Assert.isTrue(ids!=null&&ids.length!=0 ,"请选择要操作的信息!");
		for(int i=0;i<ids.length;i++){
			BaseCode bc=super.get(BaseCode.class,ids[i]);
			Assert.isTrue(bc!=null ,"无法获取编码对象!");
			bc.setStatus(status);
			super.saveOrUpdate(bc);
		}
		return "操作成功!";
	}
}
