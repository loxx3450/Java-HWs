package com.loxx3450.hw_22_02_25.controller;

import com.loxx3450.hw_22_02_25.dto.FractionPair;
import com.loxx3450.hw_22_02_25.entity.Fraction;
import com.loxx3450.hw_22_02_25.service.FractionService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("fractions/xml")
public class SoapFractionController {

	private final FractionService fractionService;
	private final Validator validator;

	public SoapFractionController(FractionService fractionService, Validator validator) {
		this.fractionService = fractionService;
		this.validator = validator;
	}

	@GetMapping(produces = "application/xml")
	public ResponseEntity<Boolean> checkFraction(@RequestParam int numerator, @RequestParam int denominator) {
		Fraction fraction = new Fraction(numerator, denominator);

		Set<ConstraintViolation<Fraction>> violations = validator.validate(fraction);

		return ResponseEntity.ok(violations.isEmpty());
	}

	@PostMapping(value = "reduction", produces = "application/xml")
	public ResponseEntity<Fraction> reduceFraction(@Valid @RequestBody Fraction fraction) {
		var reducedFraction = fractionService.reduceFraction(fraction);

		return ResponseEntity.ok(reducedFraction);
	}

	@PostMapping(value = "addition", produces = "application/xml")
	public ResponseEntity<Fraction> addFraction(@Valid @RequestBody FractionPair fractionPair) {
		var result = fractionService.addFraction(fractionPair);

		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "subtraction", produces = "application/xml")
	public ResponseEntity<Fraction> subtractFraction(@Valid @RequestBody FractionPair fractionPair) {
		var result = fractionService.subtractFraction(fractionPair);

		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "multiplication", produces = "application/xml")
	public ResponseEntity<Fraction> multiplyFraction(@Valid @RequestBody FractionPair fractionPair) {
		var result = fractionService.multiplyFraction(fractionPair);

		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "division", produces = "application/xml")
	public ResponseEntity<Fraction> divideFraction(@Valid @RequestBody FractionPair fractionPair) {
		var result = fractionService.divideFraction(fractionPair);

		return ResponseEntity.ok(result);
	}
}
