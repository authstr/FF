package com.authstr.ff.model.platform.base;
// EfficientWork  v2.28  2019-02-16 17:28:09
               
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.authstr.ff.utils.model.AbstractModel;
import org.springframework.format.annotation.DateTimeFormat;
               
@Entity
@Table(name = "base_user")	//基本用户表
public class BaseUser extends AbstractModel implements Serializable {
	private Integer id;		//主键 
	private String username;		//用户名 
	private String password;		//密码 
	private Integer sex_id;		//性别
	private String email;		//邮箱 
	private String phone;		//手机号 
	private String remark;		//备注 
	private Integer status_id;		//状态 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", unique = true, nullable = false, length = 20)
	public Integer getId(){
		return id;
	}
                 
	public void setId(Integer id){
		this.id = id;
	}
                 
	@Column(name = "username", length = 64)
	public String getUsername(){
		return username;
	}
                 
	public void setUsername(String username){
		this.username = username;
	}
                 
	@Column(name = "password", length = 128)
	public String getPassword(){
		return password;
	}
                 
	public void setPassword(String password){
		this.password = password;
	}
                 
	@Column(name = "sex_id", length = 32)
	public Integer getSex_id(){
		return sex_id;
	}
                 
	public void setSex_id(Integer sex_id){
		this.sex_id = sex_id;
	}
                 
	@Column(name = "email", length = 64)
	public String getEmail(){
		return email;
	}
                 
	public void setEmail(String email){
		this.email = email;
	}
                 
	@Column(name = "phone", length = 32)
	public String getPhone(){
		return phone;
	}
                 
	public void setPhone(String phone){
		this.phone = phone;
	}

	@NotNull
	@Column(name = "remark", length = 512)
	public String getRemark(){
		return remark;
	}
                 
	public void setRemark(String remark){
		this.remark = remark;
	}
                 
	@Column(name = "status_id", length = 32)
	public Integer getStatus_id(){
		return status_id;
	}
                 
	public void setStatus_id(Integer status_id){
		this.status_id = status_id;
	}
                 

                 
                 
}
