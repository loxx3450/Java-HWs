package com.loxx3450.hw_07_02_25.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Max {
	long value() default Long.MAX_VALUE;
	String message() default "Value should be less than {value}";
}

