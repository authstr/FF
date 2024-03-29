package com.authstr.ff.basic.service.inter;

import com.authstr.ff.model.platform.base.BaseCode;
import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.web.sevice.InterfaceService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface BaseCodeService extends InterfaceService{

    ReturnPage query(QueryCommonPage query, RequestPara para);

    List<Map> getCodeName(RequestPara para);

    List<Map> getByPara(RequestPara para);

    @Transactional
    String saveOneCode(BaseCode model);

    @Transactional
    String saveManyCode(BaseCode model, RequestPara para);

    String enabledAndDisabled(Integer[] ids, Integer status);
}
