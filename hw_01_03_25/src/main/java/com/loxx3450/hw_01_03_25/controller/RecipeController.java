package com.loxx3450.hw_01_03_25.controller;

import com.loxx3450.hw_01_03_25.dto.RecipeSummaryDto;

import com.loxx3450.hw_01_03_25.enums.RecipeDetailLevel;
import com.loxx3450.hw_01_03_25.service.RecipeService;
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
			@RequestParam(required = false) List<Integer> ingredients,
			@RequestParam(defaultValue = "false", name = "sorted_by_popularity") boolean sortedByPopularity
	) {
		var pageable = PageRequest.of(page, size);
		var type = detailLevel.toType();

		Page<? extends RecipeSummaryDto> recipes;

		if (ingredients == null)
			recipes = recipeService.getAll(pageable, type);
		else
			recipes = recipeService.getRecipesByIngredients(pageable, ingredients, type);

		if (sortedByPopularity)
			recipes = recipeService.sortByPopularity(recipes);

		return ResponseEntity.ok(recipes);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<RecipeSummaryDto> getSummaryById(
			@PathVariable int id,
			@RequestParam(defaultValue = "summary", name = "detail_level") RecipeDetailLevel detailLevel
	) {
		var type = detailLevel.toType();

		var recipe = recipeService.getRecipeSummaryById(id, type);

		return ResponseEntity.ok(recipe);
	}
}
