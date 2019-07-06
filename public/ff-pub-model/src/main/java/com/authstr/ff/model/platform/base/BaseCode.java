package com.authstr.ff.model.platform.base;
// EfficientWork  v2.28  2019-02-16 17:28:10
               
import javax.persistence.*;
               
@Entity
@Table(name = "base_code")	//基本编码表
public class BaseCode extends BaseModel {


	//编码名称
	@Column(nullable=false,unique= true,columnDefinition="VARCHAR(32) Comment '编码名称'")
	private String name;

	//编码序号--父-子序号（序号自动+1）-前天隐藏
	@Column(nullable=false,name = "code_index", length = 20)
	private Integer code_index;

	//编码值
	@Column(nullable=false,name = "code_value", length = 64)
	private String code_value;


	//编码英文值
	@Column(nullable=false,unique= true,name = "code_english", length = 20)
	private String code_english;


	//编码类型
	@Column(nullable=false,name = "type", length = 4)
	private Integer type;

	//所属系统
	@Column(nullable=false,name = "system_name", length = 32)
	private String system_name;

	//上级编码
	@Column(name = "parent_code", length = 20)
	private Integer parent_code;

	//编码描述
	@Column(name = "describe_info", length = 128)
	private String describe_info;

	//状态
	@Column(name = "status", length = 20)
	private Integer status;



//	public Integer getId(){
//		return id;
//	}
//	public void setId(Integer id){
//		this.id = id;
//	}
                 

	public String getName(){
		return name;
	}
                 
	public void setName(String name){
		this.name = name;
	}
                 

	public Integer getCode_index(){
		return code_index;
	}
                 
	public void setCode_index(Integer code_index){
		this.code_index = code_index;
	}
                 

	public String getCode_value(){
		return code_value;
	}
                 
	public void setCode_value(String code_value){
		this.code_value = code_value;
	}
                 

	public Integer getType(){
		return type;
	}
                 
	public void setType(Integer type){
		this.type = type;
	}
                 

	public String getSystem_name(){
		return system_name;
	}
                 
	public void setSystem_name(String system_name){
		this.system_name = system_name;
	}
                 

	public Integer getParent_code(){
		return parent_code;
	}
                 
	public void setParent_code(Integer parent_code){
		this.parent_code = parent_code;
	}

                 

	public Integer getStatus(){
		return status;
	}
                 
	public void setStatus(Integer status){
		this.status = status;
	}


	public String getCode_english() {
		return code_english;
	}

	public void setCode_english(String code_english) {
		this.code_english = code_english;
	}

	public String getDescribe_info() {
		return describe_info;
	}

	public void setDescribe_info(String describe_info) {
		this.describe_info = describe_info;
	}
}
