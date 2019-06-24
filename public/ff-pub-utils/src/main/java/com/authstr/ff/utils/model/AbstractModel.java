package com.authstr.ff.utils.model;

import com.authstr.ff.utils.login.LoginInfo;
import com.authstr.ff.utils.login.LoginThreadLocal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public abstract class  AbstractModel implements Serializable  {



	//创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="datetime Comment '创建时间'")
	private Date gmt_create;

	//更新时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="datetime Comment '更新时间'")
	private Date gmt_modified;


	public abstract  Serializable getId();

	//创建model,设置创建人创建时间等
	public abstract void createModel();

	//更新model,设置更新时间
	public abstract void updata();



	public Date getGmt_create(){
		return gmt_create;
	}
	public void setGmt_create(Date gmt_create){
		this.gmt_create = gmt_create;
	}


	public Date getGmt_modified(){
		return gmt_modified;
	}
	public void setGmt_modified(Date gmt_modified){
		this.gmt_modified = gmt_modified;
	}

	public abstract Serializable getCreator_id();
	public abstract void setCreator_id(Serializable creator_id);

}
