package com.loxx3450.hw_22_02_25.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.loxx3450.hw_22_02_25.validation.annotation.NotZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(localName = "fraction")
public class Fraction {
	private int numerator;

	@NotZero(message = "denominator cannot be zero!")
	private int denominator;
}
