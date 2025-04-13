package com.loxx3450.hw_01_03_25.service;

import com.loxx3450.hw_01_03_25.dto.RecipeFullDto;
import com.loxx3450.hw_01_03_25.dto.RecipeSummaryDto;
import com.loxx3450.hw_01_03_25.entity.Ingredient;
import com.loxx3450.hw_01_03_25.entity.Recipe;
import com.loxx3450.hw_01_03_25.repository.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repository;

    public RecipeServiceImpl(RecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends RecipeSummaryDto> Page<T> getAll(Pageable pageable, Class<T> type) {
        return repository.findAllBy(pageable, type);
    }

    @Override
    public <T extends RecipeSummaryDto> T getRecipeSummaryById(int id, Class<T> type) {
        return repository.findById(id, type);
    }

    @Override
    public <T extends RecipeSummaryDto> Page<T> getRecipesByIngredients(Pageable pageable, List<Integer> ingredients, Class<T> type) {
        Page<Recipe> recipes = repository.findAllByIngredients(pageable, ingredients);

        return recipes.map(r -> mapRecipeToDto(r, type));
    }

    //    @Override
//    public RecipeDto getRandomRecipe() {
//        List<RecipeDto> recipes = repository.findAll(Page.empty().getPageable(), RecipeDto.class);
//
//        if (recipes == null || recipes.isEmpty()) {
//            return null;
//        }
//
//        Random random = new Random();
//        int randomIndex = random.nextInt(recipes.size());
//        return recipes.get(randomIndex);
//    }
//
//
    @Override
    public <T extends RecipeSummaryDto> Page<T> getMostPopularRecipes(Pageable pageable, Class<T> type) {
        return repository.findAllByOrderByPopularityDesc(pageable, type);
    }

    @Override
    public <T extends RecipeSummaryDto> Page<T> sortByPopularity(Page<T> recipes) {
        List<T> sortedList = recipes.getContent().stream()
                .sorted(Comparator.comparingInt(r -> r.getPopularity()))
                .collect(Collectors.toList());

        return new PageImpl<>(sortedList, recipes.getPageable(), recipes.getTotalElements());
    }

    private <T extends RecipeSummaryDto> T mapRecipeToDto(Recipe recipe, Class<T> type) {
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

        List<Integer> ingredientIds = recipe.getIngredients().stream()
                .map(ingredient -> ingredient.getId())
                .collect(Collectors.toList());

        return new RecipeFullDto(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getReceipt(),
                recipe.getLink(),
                recipe.getOriginCountry(),
                recipe.getMealTime(),
                recipe.getType(),
                recipe.getPopularity(),
                ingredientIds
        );
    }
}
