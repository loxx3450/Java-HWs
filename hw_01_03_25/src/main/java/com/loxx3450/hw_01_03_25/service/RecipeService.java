package com.loxx3450.hw_01_03_25.service;

import com.loxx3450.hw_01_03_25.dto.RecipeFullDto;
import com.loxx3450.hw_01_03_25.dto.RecipeSummaryDto;
import com.loxx3450.hw_01_03_25.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeService {
    <T extends RecipeSummaryDto> Page<T> getAll(Pageable pageable, Class<T> type);

    <T extends RecipeSummaryDto> T getRecipeSummaryById(int id, Class<T> type);

//    RecipeDto getRandomRecipe();
//
    <T extends RecipeSummaryDto> Page<T> getRecipesByIngredients(Pageable pageable, List<Integer> ingredients, Class<T> type);

    <T extends RecipeSummaryDto> Page<T> getMostPopularRecipes(Pageable pageable, Class<T> type);

    <T extends RecipeSummaryDto> Page<T> sortByPopularity(Page<T> recipes);
}
