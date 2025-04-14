package com.loxx3450.hw_01_03_25.service;

import com.loxx3450.hw_01_03_25.dto.CreateRecipeDto;
import com.loxx3450.hw_01_03_25.dto.EditRecipeDto;
import com.loxx3450.hw_01_03_25.dto.RecipeFullDto;
import com.loxx3450.hw_01_03_25.dto.RecipeSummaryDto;
import com.loxx3450.hw_01_03_25.entity.Country;
import com.loxx3450.hw_01_03_25.entity.FoodType;
import com.loxx3450.hw_01_03_25.entity.Ingredient;
import com.loxx3450.hw_01_03_25.entity.MealTime;
import com.loxx3450.hw_01_03_25.entity.Recipe;
import com.loxx3450.hw_01_03_25.repository.IngredientRepository;
import com.loxx3450.hw_01_03_25.repository.RecipeRepository;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repository;
    private final IngredientRepository ingredientRepository;

    public RecipeServiceImpl(RecipeRepository repository, IngredientRepository ingredientRepository) {
        this.repository = repository;
		this.ingredientRepository = ingredientRepository;
	}

    @Override
    public <T extends RecipeSummaryDto> Page<T> getAll(Pageable pageable, Class<T> type) {
        return repository.findAllByOrderByPopularityDesc(pageable, type);
    }

    @Override
    public <T extends RecipeSummaryDto> T getById(int id, Class<T> type) {
        return repository.findById(id, type);
    }

    @Override
    public <T extends RecipeSummaryDto> T getRandom(Class<T> type) {
        Recipe recipe = repository.findRandom(PageRequest.of(0, 1))
            .stream()
            .findFirst()
            .orElse(null);

        return mapRecipeToDto(recipe, type);
    }

    @Override
    public <T extends RecipeSummaryDto> Page<T> getAllByIngredients(Pageable pageable, List<Integer> ingredients, Class<T> type) {
        Page<Recipe> recipes = repository.findAllByIngredients(pageable, ingredients);

        return recipes.map(r -> mapRecipeToDto(r, type));
    }

    @Override
    public <T extends RecipeSummaryDto> Page<T> getAllWithoutIngredients(
        Pageable pageable,
        List<Integer> ingredients,
        Class<T> type) {

        Page<Recipe> recipes = repository.findAllWithoutIngredients(pageable, ingredients);

        return recipes.map(r -> mapRecipeToDto(r, type));
    }

    @Override
    public <T extends RecipeSummaryDto> Page<T> getByOriginCountry(
        Pageable pageable,
        Country country,
        Class<T> type) {

        Page<Recipe> recipes = repository.findAllByOriginCountry(pageable, country);

        return recipes.map(r -> mapRecipeToDto(r, type));
    }

    @Override
    public List<FoodType> getAllFoodTypes() {
        return repository.findDistinctFoodTypes();
    }

    @Override
    public Page<Country> getMostPopularCountries(Pageable pageable) {
        return repository.findMostPopularCountries(pageable);
    }

    @Override
    public Page<FoodType> getMostPopularFoodTypes(Pageable pageable) {
        return repository.findMostPopularFoodTypes(pageable);
    }

    @Override
    public <T extends RecipeSummaryDto> Page<T> getByMealTime(Pageable pageable, MealTime mealTime, Class<T> type) {
        return repository.findByMealTimeOrderByPopularityDesc(pageable, mealTime, type);
    }

    @Override
    public <T extends RecipeSummaryDto> List<T> getRandomDinner(Class<T> type) {
        List<T> result = new ArrayList<>();

        var mainDish = getRandomByMealTime(MealTime.DINNER);
        var snack = getRandomByMealTime(MealTime.SNACK);
        var anythingElse = getRandomByMealTime(MealTime.ANYTIME);

        if (mainDish != null) {
            result.add(mapRecipeToDto(mainDish, type));
        }
        if (snack != null) {
            result.add(mapRecipeToDto(snack, type));
        }
        if (anythingElse != null) {
            result.add(mapRecipeToDto(anythingElse, type));
        }

        return result;
    }

    @Override
    public <T extends RecipeSummaryDto> T getRandomBreakfast(Class<T> type) {
        var recipe = getRandomByMealTime(MealTime.BREAKFAST);

        if (recipe != null) {
            return mapRecipeToDto(recipe, type);
        }

        return null;
    }

    private Recipe getRandomByMealTime(MealTime mealTime) {
        return repository.findRandomByMealTime(PageRequest.of(0, 1), mealTime)
            .stream()
            .findFirst()
            .orElse(null);
    }

    @Override
    public <T extends RecipeSummaryDto> T add(CreateRecipeDto createDto, Class<T> type) {
        Recipe recipeToAdd = new Recipe();

        recipeToAdd.setDescription(createDto.getDescription());
        recipeToAdd.setLink(createDto.getLink());
        recipeToAdd.setReceipt(createDto.getReceipt());
        recipeToAdd.setType(FoodType.fromString(createDto.getType()));
        recipeToAdd.setTitle(createDto.getTitle());
        recipeToAdd.setPopularity(createDto.getPopularity());
        recipeToAdd.setMealTime(MealTime.fromString(createDto.getMealTime()));
        recipeToAdd.setOriginCountry(Country.fromString(createDto.getOriginCountry()));

        List<Ingredient> ingredients = ingredientRepository.findAllById(createDto.getIngredientsIds());
        recipeToAdd.setIngredients(ingredients);

        Recipe savedRecipe = repository.save(recipeToAdd);

        return mapRecipeToDto(savedRecipe, type);
    }

    @Override
    public <T extends RecipeSummaryDto> T edit(int id, EditRecipeDto editDto, Class<T> type) {
        Recipe recipeToEdit = repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND));

        recipeToEdit.setDescription(editDto.getDescription());
        recipeToEdit.setLink(editDto.getLink());
        recipeToEdit.setReceipt(editDto.getReceipt());
        recipeToEdit.setType(FoodType.fromString(editDto.getType()));
        recipeToEdit.setTitle(editDto.getTitle());
        recipeToEdit.setPopularity(editDto.getPopularity());
        recipeToEdit.setMealTime(MealTime.fromString(editDto.getMealTime()));
        recipeToEdit.setOriginCountry(Country.fromString(editDto.getOriginCountry()));

        List<Ingredient> ingredients = ingredientRepository.findAllById(editDto.getIngredientsIds());
        recipeToEdit.setIngredients(ingredients);

        Recipe savedRecipe = repository.save(recipeToEdit);

        return mapRecipeToDto(savedRecipe, type);
    }

    @Override
    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
    }


    private <T extends RecipeSummaryDto> T mapRecipeToDto(Recipe recipe, Class<T> type) {
        if (recipe == null) {
            return null;
        }

        if (type.equals(RecipeFullDto.class)) {
            return (T) mapToRecipeFullDto(recipe);
        }

        return (T) mapToRecipeSummaryDto(recipe);
    }

    private RecipeSummaryDto mapToRecipeSummaryDto(Recipe recipe) {
        return new RecipeSummaryDto(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getLink(),
                recipe.getMealTime(),
                recipe.getPopularity()
        );
    }

    private RecipeFullDto mapToRecipeFullDto(Recipe recipe) {
        return new RecipeFullDto(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getReceipt(),
                recipe.getLink(),
                recipe.getOriginCountry(),
                recipe.getMealTime(),
                recipe.getType(),
                recipe.getPopularity()
        );
    }
}
