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
public class UpdateAutoDTO {
	private int id;
	private Optional<String> manufacturer;
	private Optional<String> model;
	private Optional<Double> engineCapacity;
	private Optional<Integer> year;
	private Optional<String> color;
	private Optional<AutoType> type;
}
