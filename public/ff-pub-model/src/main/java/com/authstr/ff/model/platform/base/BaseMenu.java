package com.authstr.ff.model.platform.base;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


/**
 * 基础菜单表
 * @author authstr
 * 2019年7月6日14:41:50
 */
@Entity
@Table(name = "base_menu")
public class BaseMenu extends BaseModel  {

	@NotBlank(message = "菜单编码不能为空!")
	@Length(max = 32,message = "菜单编码不能超过32位")
	@Column(unique = true,columnDefinition="VARCHAR(32) Comment '菜单编码'")
	private String code;

	@NotBlank(message = "菜单名称不能为空!")
	@Length(max = 16,message = "菜单名称不能超过16位")
	@Column(columnDefinition = "VARCHAR(16) comment '菜单名称'")
	private String name;

	@NotBlank
	@Column(columnDefinition = "VARCHAR(512) comment '链接地址'")
	private String url;

	@NotBlank
	@Column(columnDefinition = "SMALLINT comment '排序号'")
	private  Integer orderno;

	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
//	@Column(columnDefinition = "bigint(20) comment '菜单'")
	private BaseMenu paraent_menu;

	/**
	 * 	菜单参数将会拼接到链接地址的后面,作为请求的参数
	 *  参数的保存形式为json,期望对象是Map
	 */
	@Column(columnDefinition = "VARCHAR(1024) comment '菜单参数'")
	private String para;

	@Column(columnDefinition = "tinyint comment '菜单类型'")
	private Integer type ;

	@Column(columnDefinition = "VARCHAR(512) comment '菜单图标的url'")
	private String icon_uri;

	@Column(columnDefinition = "VARCHAR(2048) comment '说明'")
	private String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public BaseMenu getParaent_menu() {
		return paraent_menu;
	}

	public void setParaent_menu(BaseMenu paraent_menu) {
		this.paraent_menu = paraent_menu;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon_uri() {
		return icon_uri;
	}

	public void setIcon_uri(String icon_uri) {
		this.icon_uri = icon_uri;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
