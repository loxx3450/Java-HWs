package com.loxx3450.hw_01_03_25.controller;

import com.loxx3450.hw_01_03_25.dto.CreateRecipeDto;
import com.loxx3450.hw_01_03_25.dto.EditRecipeDto;
import com.loxx3450.hw_01_03_25.dto.RecipeSummaryDto;

import com.loxx3450.hw_01_03_25.entity.Country;
import com.loxx3450.hw_01_03_25.entity.FoodType;
import com.loxx3450.hw_01_03_25.entity.MealTime;
import com.loxx3450.hw_01_03_25.enums.RecipeDetailLevel;
import com.loxx3450.hw_01_03_25.service.RecipeService;
import java.net.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping
	public ResponseEntity<Page<? extends RecipeSummaryDto>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel,
			@RequestParam(required = false) List<Integer> ingredients
	) {
		var pageable = PageRequest.of(page, size);
		var type = detailLevel.toType();

		Page<? extends RecipeSummaryDto> recipes;

		if (ingredients == null)
			recipes = recipeService.getAll(pageable, type);
		else
			recipes = recipeService.getAllByIngredients(pageable, ingredients, type);

		return ResponseEntity.ok(recipes);
	}

	@GetMapping("without-ingredients")
	public ResponseEntity<Page<? extends RecipeSummaryDto>> getAllWithoutIngredients(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "5") int size,
		@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel,
		@RequestParam List<Integer> ingredients
	) {
		var pageable = PageRequest.of(page, size);
		var type = detailLevel.toType();

		var recipes = recipeService.getAllWithoutIngredients(pageable, ingredients, type);

		return ResponseEntity.ok(recipes);
	}

	@GetMapping("origin-country")
	public ResponseEntity<Page<? extends RecipeSummaryDto>> getByOriginCountry(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "5") int size,
		@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel,
		@RequestParam String country
	) {
		var pageable = PageRequest.of(page, size);
		var type = detailLevel.toType();
		Country value = Country.fromString(country);

		var recipes = recipeService.getByOriginCountry(pageable, value, type);

		return ResponseEntity.ok(recipes);
	}

	@GetMapping("most-popular-countries")
	public ResponseEntity<Page<Country>> getMostPopularCountries(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "5") int size
	) {
		var pageable = PageRequest.of(page, size);

		var countries = recipeService.getMostPopularCountries(pageable);

		return ResponseEntity.ok(countries);
	}

	@GetMapping("most-popular-food-types")
	public ResponseEntity<Page<FoodType>> getMostPopularFoodTypes(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "5") int size
	) {
		var pageable = PageRequest.of(page, size);

		var foodTypes = recipeService.getMostPopularFoodTypes(pageable);

		return ResponseEntity.ok(foodTypes);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<RecipeSummaryDto> getById(
			@PathVariable int id,
			@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel
	) {
		var type = detailLevel.toType();

		var recipe = recipeService.getById(id, type);

		if (recipe == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(recipe);
	}

	@GetMapping(path = "random")
	public ResponseEntity<RecipeSummaryDto> getRandom(
		@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel
	) {
		var type = detailLevel.toType();

		var recipe = recipeService.getRandom(type);

		return ResponseEntity.ok(recipe);
	}

	@GetMapping(path = "types")
	public ResponseEntity<List<FoodType>> getTypes() {
		var types = recipeService.getAllFoodTypes();

		return ResponseEntity.ok(types);
	}

	@GetMapping(path = "mealTime")
	public ResponseEntity<Page<? extends RecipeSummaryDto>> getByMealTime(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "5") int size,
		@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel,
		@RequestParam(name = "meal_time") String mealTime
	) {
		var pageable = PageRequest.of(page, size);
		MealTime value = MealTime.fromString(mealTime);
		var type = detailLevel.toType();

		var recipes = recipeService.getByMealTime(pageable, value, type);

		return ResponseEntity.ok(recipes);
	}

	@GetMapping(path = "random-dinner")
	public ResponseEntity<List<RecipeSummaryDto>> getRandomDinner(
		@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel
	)
	{
		var type = detailLevel.toType();

		var recipes = recipeService.getRandomDinner(type);

		return ResponseEntity.ok(recipes);
	}

	@GetMapping(path = "random-breakfast")
	public ResponseEntity<RecipeSummaryDto> getRandomBreakfast(
		@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel
	) {
		var type = detailLevel.toType();

		var breakfast = recipeService.getRandomBreakfast(type);

		return ResponseEntity.ok(breakfast);
	}

	@PostMapping
	public ResponseEntity<RecipeSummaryDto> create(
		@RequestBody CreateRecipeDto createRecipeDto,
		@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel
	) {
		var type = detailLevel.toType();

		var createdRecipe = recipeService.add(createRecipeDto, type);

		return ResponseEntity.created(URI.create("/api/recipes" + createdRecipe.getId())).body(createdRecipe);
	}

	@PutMapping(path = "{id}")
	public ResponseEntity<RecipeSummaryDto> edit(
		@PathVariable int id,
		@RequestBody EditRecipeDto editRecipeDto,
		@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel
	) {
		var type = detailLevel.toType();

		var editedRecipe = recipeService.edit(id, editRecipeDto, type);

		return ResponseEntity.ok(editedRecipe);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		recipeService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
