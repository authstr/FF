package com.authstr.ff.utils.model;



import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.exception.Assert;



public class ModelUtils {
	 public static boolean isNew(AbstractModel model) {
	        Assert.isTrue(model != null, "model 是空的 ", true);
	        Object id = model.getId();
	        if (model.getId() == null) {
	            return true;
	        }
	        if(id instanceof Integer ){//如果id的类型的int或者String
	        	if(model.getId() != null ){
	        		return false;
	        	}
	        	return true;
	        }
	        if(id instanceof String ){
	        	if (model.getId() != null && !StringUtils.hasText(id.toString())) {
	                return false;
	            }
	            return true;
	        }
	        return false;
	    }
}
