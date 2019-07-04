package com.authstr.sd.basic.service.impl;

import com.authstr.ff.model.platform.base.BaseCode;
import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.encryption.Md5Salt;
import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.exception.ErrorException;
import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.model.ModelUtils;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.web.sevice.AbstractService;
import com.authstr.sd.basic.dao.inter.BaseCodeDao;
import com.authstr.sd.basic.service.inter.BaseCodeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
	public List<Map> getByPara(RequestPara para){
		return baseCodeDao.get(para);
	}

	@Transactional
	@Override
	public String save(BaseCode baseCode){
		Assert.isTrue(StringUtils.hasText(baseCode.getName()),"编码名称不能为空",false);
		Assert.isTrue(super.isUnique(baseCode,new String[]{"name"}),"编码名称已存在");
		Assert.isTrue(StringUtils.hasText(baseCode.getCode_value()),"编码值不能为空");
		Assert.isTrue(StringUtils.hasText(baseCode.getCode_english()),"英文编码值不能为空");
		Assert.isTrue(null !=baseCode.getType(),"编码类型不能为空");
		Assert.isTrue(null !=baseCode.getStatus(),"状态不能为空");
		if(ModelUtils.isNew(baseCode)){
			super.save(baseCode);
			return "保存成功";
		}else{
			super.updata(baseCode);
			return "修改成功";
		}

	}
}
