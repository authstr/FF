package com.authstr.ff.utils.validated;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Constraint(validatedBy = IDCardValidated.class)
@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IDCard {

    String message() default "身份号码不符合规范";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}