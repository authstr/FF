package com.authstr.sd.basic.service.inter;

import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.http.RequestPara;
import com.authstr.ff.utils.page.Page;
import com.authstr.ff.utils.page.QueryCommonPage;
import com.authstr.ff.utils.web.sevice.InterfaceService;

public interface UserService extends InterfaceService{

    /**
     * 查询
     * @param query
     * @return
     * @time 2018年10月27日 下午6:17:40
     * @author authstr
     */
    Page query(QueryCommonPage query, RequestPara para);

    String save(BaseUser user);
}