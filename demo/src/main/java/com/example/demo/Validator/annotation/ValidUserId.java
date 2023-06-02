package com.example.demo.Validator.annotation;

import com.example.demo.Validator.ValidUserIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import  static java.lang.annotation.ElementType.TYPE;
import  static java.lang.annotation.ElementType.FIELD;
import  static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({TYPE,FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidUserIdValidator.class)
@Documented
public @interface ValidUserId {
    String message() default "Invalid user Id";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};

}
