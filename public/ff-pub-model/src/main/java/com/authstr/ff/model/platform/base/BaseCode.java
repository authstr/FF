package com.authstr.ff.model.platform.base;
// EfficientWork  v2.28  2019-02-16 17:28:10
               
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import com.authstr.ff.utils.model.AbstractModel;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
               
@Entity
@Table(name = "base_code")	//基本编码表
public class BaseCode extends AbstractModel implements Serializable {
	private Integer id;		//主键
	private String name;		//编码名称 
	private Integer code_index;		//编码序号 
	private String code_value;		//编码值
	private String code_english;	//编码英文值
	private Integer type;		//编码类型 
	private String system_name;		//所属系统 
	private Integer parent_code;		//上级编码
	private String describe;		//编码描述 
	private Integer status;		//状态


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", unique = true, nullable = false, length = 20)
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
                 
	@Column(name = "name", length = 32)
	public String getName(){
		return name;
	}
                 
	public void setName(String name){
		this.name = name;
	}
                 
	@Column(name = "code_index", length = 20)
	public Integer getCode_index(){
		return code_index;
	}
                 
	public void setCode_index(Integer code_index){
		this.code_index = code_index;
	}
                 
	@Column(name = "code_value", length = 64)
	public String getCode_value(){
		return code_value;
	}
                 
	public void setCode_value(String code_value){
		this.code_value = code_value;
	}
                 
	@Column(name = "type", length = 4)
	public Integer getType(){
		return type;
	}
                 
	public void setType(Integer type){
		this.type = type;
	}
                 
	@Column(name = "system_name", length = 32)
	public String getSystem_name(){
		return system_name;
	}
                 
	public void setSystem_name(String system_name){
		this.system_name = system_name;
	}
                 
	@Column(name = "parent_code", length = 20)
	public Integer getParent_code(){
		return parent_code;
	}
                 
	public void setParent_code(Integer parent_code){
		this.parent_code = parent_code;
	}
                 
	@Column(name = "describe", length = 128)
	public String getDescribe(){
		return describe;
	}
                 
	public void setDescribe(String describe){
		this.describe = describe;
	}
                 
	@Column(name = "status", length = 20)
	public Integer getStatus(){
		return status;
	}
                 
	public void setStatus(Integer status){
		this.status = status;
	}

	@Column(name = "code_english", length = 20)
	public String getCode_english() {
		return code_english;
	}

	public void setCode_english(String code_english) {
		this.code_english = code_english;
	}
}
