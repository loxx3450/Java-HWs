package com.loxx3450.hw_01_03_25.dto;

import java.util.List;
import lombok.Data;

@Data
public class EditRecipeDto {
	private String title;
	private String description;
	private String receipt;
	private String link;
	private String originCountry;
	private String mealTime;
	private String type;
	private int popularity;
	private List<Integer> ingredientsIds;

	public EditRecipeDto(String title, String description, String receipt, String link,
		String originCountry, String mealTime, String type, int popularity,
		List<Integer> ingredientsIds) {

		this.title = title;
		this.description = description;
		this.receipt = receipt;
		this.link = link;
		this.originCountry = originCountry;
		this.mealTime = mealTime;
		this.type = type;
		this.popularity = popularity;
		this.ingredientsIds = ingredientsIds;
	}
}
