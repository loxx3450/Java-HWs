package com.loxx3450.hw_04_02_25.task2;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoFilter {
	@Builder.Default
	private Optional<String> manufacturer = Optional.empty();

	@Builder.Default
	private Optional<String> color = Optional.empty();

	@Builder.Default
	private Optional<Double> engineCapacity = Optional.empty();

	@Builder.Default
	private Optional<AutoType> type = Optional.empty();

	@Builder.Default
	private Optional<Integer> year = Optional.empty();
}
