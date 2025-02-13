package com.loxx3450.hw_22_02_25.validation.validator;

import com.loxx3450.hw_22_02_25.validation.annotation.NotZero;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotZeroValidator implements ConstraintValidator<NotZero, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
		return value != null && value != 0;
	}
}
