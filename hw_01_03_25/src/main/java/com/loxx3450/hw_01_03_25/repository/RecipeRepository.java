package com.loxx3450.hw_01_03_25.repository;

import com.loxx3450.hw_01_03_25.entity.Country;
import com.loxx3450.hw_01_03_25.entity.FoodType;
import com.loxx3450.hw_01_03_25.entity.MealTime;
import com.loxx3450.hw_01_03_25.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    <T> Page<T> findAllByOrderByPopularityDesc(Pageable pageable, Class<T> type);

    @Query("SELECT r "
        + "FROM Recipe r "
        + "JOIN r.ingredients i "
        + "WHERE i.id IN (:ingredientIds) "
        + "ORDER BY r.popularity DESC")
    Page<Recipe> findAllByIngredients(Pageable pageable, List<Integer> ingredientIds);

    @Query("SELECT r "
        + "FROM Recipe r "
        + "JOIN r.ingredients i "
        + "WHERE i.id NOT IN (:ingredientIds) "
        + "ORDER BY r.popularity DESC")
    Page<Recipe> findAllWithoutIngredients(Pageable pageable, List<Integer> ingredientIds);

    Page<Recipe> findAllByOriginCountry(Pageable pageable, Country country);

    @Query("SELECT r.originCountry "
        + "FROM Recipe r "
        + "GROUP BY r.originCountry "
        + "ORDER BY COUNT(r) DESC")
    Page<Country> findMostPopularCountries(Pageable pageable);

    @Query("SELECT r.type "
        + "FROM Recipe r "
        + "GROUP BY r.type "
        + "ORDER BY COUNT(r) DESC")
    Page<FoodType> findMostPopularFoodTypes(Pageable pageable);

    <T> T findById(int id, Class<T> type);

    @Query("SELECT r "
        + "FROM Recipe r "
        + "ORDER BY function('RANDOM')")
    Page<Recipe> findRandom(Pageable pageable);

    @Query("SELECT r "
        + "FROM Recipe r "
        + "WHERE r.mealTime = :mealTime "
        + "ORDER BY function('RANDOM')")
    Page<Recipe> findRandomByMealTime(Pageable pageable, MealTime mealTime);

    @Query("SELECT DISTINCT r.type FROM Recipe r")
    List<FoodType> findDistinctFoodTypes();

    <T> Page<T> findByMealTimeOrderByPopularityDesc(Pageable pageable, MealTime mealTime, Class<T> type);
}
