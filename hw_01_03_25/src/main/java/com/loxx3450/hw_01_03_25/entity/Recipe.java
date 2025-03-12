package com.loxx3450.hw_01_03_25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "recipes")
public class Recipe {
	@Id
	private int id;
	private String title;
	private String description;
	private String receipt;
	private String link;
	@Enumerated(EnumType.STRING)
	@Column(name = "origin_country")
	private Country originCountry;
	@Enumerated(EnumType.STRING)
	@Column(name = "meal_time")
	private MealTime mealTime;
	@Enumerated(EnumType.STRING)
	private FoodType type;
	private int popularity;

	@ManyToMany
	@JoinTable(
		name = "recipes_ingredients",
		joinColumns = @JoinColumn(name = "recipe_id"),
		inverseJoinColumns = @JoinColumn(name = "ingredient_id")
	)
	private List<Ingredient> ingredients;
}
