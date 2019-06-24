package com.authstr.ff.model.platform.base;

import com.authstr.ff.utils.login.LoginInfo;
import com.authstr.ff.utils.login.LoginThreadLocal;
import com.authstr.ff.utils.model.AbstractModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseModel extends AbstractModel {

    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", unique = true, nullable = false, length = 20)
    private Integer id;

    //创建人id
    @Column(columnDefinition="INTEGER Comment '创建人id'")
    protected Integer creator_id;

    @Override
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    //创建model,设置创建人创建时间等
    @Override
    public void createModel(){
        //获取当前登录人id
        LoginInfo loginInfo= LoginThreadLocal.get();
        if(null!=loginInfo&&null!=loginInfo.getUserID()){
            try {
                setCreator_id(Integer.valueOf(loginInfo.getUserID()));
            } catch (Exception e) {
            }
        }
        //设置创建时间和更新时间
        setGmt_create(new Date());
        setGmt_modified(new Date());
    }

    //更新model,设置更新时间
    @Override
    public  void updata(){
        setGmt_modified(new Date());
    }



    public Serializable getCreator_id(){
        return creator_id;
    }
    public void setCreator_id(Serializable creator_id){
        this.creator_id = (Integer) creator_id;
    }

}
