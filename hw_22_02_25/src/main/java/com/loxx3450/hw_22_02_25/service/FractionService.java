package com.loxx3450.hw_22_02_25.service;

import com.loxx3450.hw_22_02_25.dto.FractionPair;
import com.loxx3450.hw_22_02_25.entity.Fraction;

public interface FractionService {
	Fraction reduceFraction(Fraction fraction);

	Fraction addFraction(FractionPair fractionPair);

	Fraction subtractFraction(FractionPair fractionPair);

	Fraction multiplyFraction(FractionPair fractionPair);

	Fraction divideFraction(FractionPair fractionPair);
}
