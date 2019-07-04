package com.authstr.ff.model.platform.base;
// EfficientWork  v2.28  2019-06-28 21:36:57
               
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal; 
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
               
@Entity
@Table(name = "base_business_log")	//业务日志表
public class BaseBusinessLog extends BaseModel  {

	@Column(nullable=false,length = 16,columnDefinition="VARCHAR(32) Comment '所属系统'")
	private String system_name;		//所属系统

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false,columnDefinition="datetime Comment '业务请求时间'")
	private Date enter_time;		//业务请求时间

	@Column(columnDefinition="VARCHAR(32) Comment '请求的来源ip '")
	private String source_ip;		//请求的来源ip

	@Column(columnDefinition="VARCHAR(32) Comment '终端的类型 '")
	private String source_type;		//请求的来源类型

	@Column(columnDefinition="VARCHAR(64) Comment '终端的类型和版本 '")
	private String browser_info;		//浏览器的类型和版本

	@Column(columnDefinition="VARCHAR(256) Comment '请求的url'")
	private String server_uri;		//请求的url

	@Column(columnDefinition="VARCHAR(2048) Comment '请求使用的参数'")
	private String params;		//请求使用的参数

	@Column(columnDefinition="VARCHAR(16) Comment '操作类型'")
	private String typy;		//操作类型

	@Column(columnDefinition="bigint(20) Comment '操作用户'")
	private Integer operate_user;		//操作用户

	@Column(columnDefinition="bigint(2048) Comment '业务过程'")
	private String business_course;		//业务过程

	@Column(columnDefinition="bigint(64) Comment '业务结果'")
	private String business_result;		//业务结果

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="datetime Comment '业务结束时间'")
	private Date end_time;		//业务结束时间 


	public String getSource_ip(){
		return source_ip;
	}
	public void setSource_ip(String source_ip){
		this.source_ip = source_ip;
	}
                 
	public String getSource_type(){
		return source_type;
	}
	public void setSource_type(String source_type){
		this.source_type = source_type;
	}
                 
	public String getBrowser_info(){
		return browser_info;
	}
	public void setBrowser_info(String browser_info){
		this.browser_info = browser_info;
	}
                 
	public String getServer_uri(){
		return server_uri;
	}
	public void setServer_uri(String server_uri){
		this.server_uri = server_uri;
	}
                 
	public String getParams(){
		return params;
	}
	public void setParams(String params){
		this.params = params;
	}
                 
	public String getTypy(){
		return typy;
	}
	public void setTypy(String typy){
		this.typy = typy;
	}
                 
	public Integer getOperate_user(){
		return operate_user;
	}
	public void setOperate_user(Integer operate_user){
		this.operate_user = operate_user;
	}
                 
	public String getBusiness_course(){
		return business_course;
	}
	public void setBusiness_course(String business_course){
		this.business_course = business_course;
	}
                 
	public String getBusiness_result(){
		return business_result;
	}
	public void setBusiness_result(String business_result){
		this.business_result = business_result;
	}
                 
	public Date getEnd_time(){
		return end_time;
	}
	public void setEnd_time(Date end_time){
		this.end_time = end_time;
	}

	public Date getEnter_time() {
		return enter_time;
	}
	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public String getSystem_name() {
		return system_name;
	}

	public void setSystem_name(String system_name) {
		this.system_name = system_name;
	}
}
