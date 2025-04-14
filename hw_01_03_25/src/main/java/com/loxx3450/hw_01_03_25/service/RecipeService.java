package com.loxx3450.hw_01_03_25.service;

import com.loxx3450.hw_01_03_25.dto.CreateRecipeDto;
import com.loxx3450.hw_01_03_25.dto.EditRecipeDto;
import com.loxx3450.hw_01_03_25.dto.RecipeSummaryDto;
import com.loxx3450.hw_01_03_25.entity.Country;
import com.loxx3450.hw_01_03_25.entity.FoodType;
import com.loxx3450.hw_01_03_25.entity.MealTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeService {
    <T extends RecipeSummaryDto> Page<T> getAll(Pageable pageable, Class<T> type);

    <T extends RecipeSummaryDto> T getById(int id, Class<T> type);

    <T extends RecipeSummaryDto> T getRandom(Class<T> type);

    <T extends RecipeSummaryDto> Page<T> getAllByIngredients(Pageable pageable, List<Integer> ingredients, Class<T> type);

    <T extends RecipeSummaryDto> Page<T> getAllWithoutIngredients(Pageable pageable, List<Integer> ingredients, Class<T> type);

    <T extends RecipeSummaryDto> Page<T> getByOriginCountry(Pageable pageable, Country country, Class<T> type);

    List<FoodType> getAllFoodTypes();

    Page<Country> getMostPopularCountries(Pageable pageable);

    Page<FoodType> getMostPopularFoodTypes(Pageable pageable);

    <T extends RecipeSummaryDto> Page<T> getByMealTime(Pageable pageable, MealTime mealTime, Class<T> type);

    <T extends RecipeSummaryDto> List<T> getRandomDinner(Class<T> type);

    <T extends RecipeSummaryDto> T getRandomBreakfast(Class<T> type);

    <T extends RecipeSummaryDto> T add(CreateRecipeDto createDto, Class<T> type);

    <T extends RecipeSummaryDto> T edit(int id, EditRecipeDto editDto, Class<T> type);

    void delete(int id);
}
