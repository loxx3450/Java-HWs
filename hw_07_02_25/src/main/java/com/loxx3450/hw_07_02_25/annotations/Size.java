package com.loxx3450.hw_07_02_25.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Size {
	long min() default 0;
	long max() default Long.MAX_VALUE;
	String message() default "Size must be between {min} and {max}!";
}
