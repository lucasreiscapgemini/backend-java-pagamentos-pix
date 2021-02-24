package com.capgemini.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface Unique {
	String message() default "{javax.validation.constraints.Unique.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String table();

	String column();
}