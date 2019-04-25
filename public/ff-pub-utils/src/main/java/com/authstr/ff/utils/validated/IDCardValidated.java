package com.authstr.ff.utils.validated;

import com.authstr.ff.utils.base.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class IDCardValidated implements ConstraintValidator<IDCard,String> {

    public void initialize(IDCard constraintAnnotation) {
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.notText(s)){
            return false;
        }
        return IDCardUtil.verify(s);
    }
}
