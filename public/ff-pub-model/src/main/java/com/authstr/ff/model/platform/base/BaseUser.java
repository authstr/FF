package com.authstr.ff.model.platform.base;
// EfficientWork  v2.28  2019-02-16 17:28:09
               
import javax.persistence.*;
import com.authstr.ff.utils.model.AbstractModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "base_user")	//基本用户表
public class BaseUser extends BaseModel {

	//用户名
	@NotBlank(message = "用户名不能为空")
	@Length(max = 16,message = "用户名长度不能超过{max}位")
	@Column(columnDefinition = "VARCHAR(64) comment '用户名'")
	private String username;

	//密码
	@NotBlank(message = "密码不能为空")
	@Column(columnDefinition = "VARCHAR(128) comment '密码'")
	private String password;

//	性别
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
//	@JoinColumn(name = "sex_id")//默认关联id
//	@JoinColumn(name="sex_id",referencedColumnName="id",nullable=false,insertable=false,updatable=false)
//	@Column(columnDefinition = "bigint(20) comment '性别'")
	private BaseCode sex;

	//邮箱
	@Email(message = "邮箱不符合规范")
	@Column(columnDefinition = "VARCHAR(64) comment '邮箱'")
	private String email;

	//手机号
	@Column(columnDefinition = "VARCHAR(32) comment '手机号'")
	private String phone;

	//备注
	@Length(max = 500,message = "备注长度不能超过{max}位")
	@Column(columnDefinition = "VARCHAR(512) comment '备注'")
	private String remark;

	//状态
	@Column(columnDefinition = "bigint(20) comment '用户状态'")
	private Integer status_id;





	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

                 
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}

	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	public Integer getStatus_id(){
		return status_id;
	}
	public void setStatus_id(Integer status_id){
		this.status_id = status_id;
	}


	public BaseCode getSex() {
		return sex;
	}

	public void setSex(BaseCode sex) {
		this.sex = sex;
	}
}
