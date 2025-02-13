package com.loxx3450.hw_22_02_25.validation.annotation;

import com.loxx3450.hw_22_02_25.validation.validator.NotZeroValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotZeroValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotZero {
	String message() default "value cannot be zero";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
