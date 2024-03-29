package com.authstr.ff.utils.validated;


import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.exception.MsgException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.*;
import java.util.Set;

/**
 * 提供使用Hibernate注解进行model的约束,获取约束验证的返回值
 * @time 2019年4月25日15:13:24
 * @author author
 */
@Component
public class HibernateValidatorUtils {
    private static Validator validator;

    //注解PostConstruct用于在项目启动的时候执行该方法
    @PostConstruct
    public synchronized void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static Validator getValidator() {
        return validator;
    }

    public static void validate(Object obj)throws MsgException {
        //依据约束条件进行判断,获取判断结果
        Set<ConstraintViolation<Object>> s = validator.validate(obj, new Class[0]);
        //处理判断结果
        if (s != null && !s.isEmpty()) {
            //throw new ConstraintViolationException(s);
            throw Assert.ThrowByViolationResult(s);
        }
    }
}
