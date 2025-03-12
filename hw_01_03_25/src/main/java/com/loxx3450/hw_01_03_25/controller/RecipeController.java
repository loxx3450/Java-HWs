package com.loxx3450.hw_01_03_25.controller;

import com.loxx3450.hw_01_03_25.entity.Recipe;
import com.loxx3450.hw_01_03_25.repository.RecipeRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
	// TEST!!!!
	private final RecipeRepository recipeRepository;

	public RecipeController(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@GetMapping
	public ResponseEntity<List<Recipe>> getAll() {
		List<Recipe> recipes = recipeRepository.findAll();

		return ResponseEntity.ok(recipes);
	}
}
