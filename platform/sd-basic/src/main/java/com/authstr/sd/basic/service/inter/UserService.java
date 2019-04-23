package com.authstr.sd.basic.service.inter;

import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.ReturnPage;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.sevice.InterfaceService;

import java.util.List;
import java.util.Map;

public interface UserService extends InterfaceService{

    /**
     * 查询
     * @param query
     * @return
     * @time 2018年10月27日 下午6:17:40
     * @author authstr
     */
    ReturnPage query(QueryCommonPage query, RequestPara para);

    List<Map> getByPara(RequestPara para);

    String save(BaseUser user);
}
