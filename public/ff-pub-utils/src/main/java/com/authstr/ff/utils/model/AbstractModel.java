package com.authstr.ff.utils.model;

import com.authstr.ff.utils.login.LoginInfo;
import com.authstr.ff.utils.login.LoginThreadLocal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

public abstract class  AbstractModel  {

	//创建人id
	private Integer creator_id;
	//创建时间
	private Date gmt_create;
	//更新时间
	private Date gmt_modified;


	public abstract  Object getId();

	//创建model,设置创建人创建时间等
	public void createModel(){
		//获取当前登录人id
		LoginInfo loginInfo=LoginThreadLocal.get();
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
	public void updata(){
		setGmt_modified(new Date());
	}


	@Column(name = "creator_id", length = 32)
	public Integer getCreator_id(){
		return creator_id;
	}
	public void setCreator_id(Integer creator_id){
		this.creator_id = creator_id;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gmt_create", length = 19)
	public Date getGmt_create(){
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create){
		this.gmt_create = gmt_create;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gmt_modified", length = 19)
	public Date getGmt_modified(){
		return gmt_modified;
	}

	public void setGmt_modified(Date gmt_modified){
		this.gmt_modified = gmt_modified;
	}
}
