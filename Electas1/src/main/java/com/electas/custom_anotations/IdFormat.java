package com.electas.custom_anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.electas.customCode.idFomatValidator;

import java.lang.annotation.RetentionPolicy;

@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
@Constraint (validatedBy= idFomatValidator.class)
public @interface IdFormat {
	
	String message() default "Please enter a valid SA id Number";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
