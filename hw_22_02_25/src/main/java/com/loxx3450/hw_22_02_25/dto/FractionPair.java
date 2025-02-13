package com.loxx3450.hw_22_02_25.dto;

import com.loxx3450.hw_22_02_25.entity.Fraction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
public class FractionPair {
	@Valid
	@NotNull
	private final Fraction fraction1;

	@Valid
	@NotNull
	private final Fraction fraction2;
}
