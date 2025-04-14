package com.loxx3450.hw_01_03_25.dto;

import com.loxx3450.hw_01_03_25.entity.Country;
import com.loxx3450.hw_01_03_25.entity.FoodType;
import com.loxx3450.hw_01_03_25.entity.Ingredient;
import com.loxx3450.hw_01_03_25.entity.MealTime;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Data;

@Data
public class CreateRecipeDto {
	private String title;
	private String description;
	private String receipt;
	private String link;
	private String originCountry;
	private String mealTime;
	private String type;
	private int popularity;
	private List<Integer> ingredientsIds;

	public CreateRecipeDto(String title, String description, String receipt, String link,
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
