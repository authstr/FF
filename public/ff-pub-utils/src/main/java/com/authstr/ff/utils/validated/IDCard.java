package com.authstr.ff.utils.validated;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Constraint(validatedBy = IDCardValidated.class)
@Target({METHOD,FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IDCard {

    String message() default "身份证号码不符合规范";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}