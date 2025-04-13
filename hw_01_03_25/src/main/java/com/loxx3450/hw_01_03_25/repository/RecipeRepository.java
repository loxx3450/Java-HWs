package com.loxx3450.hw_01_03_25.repository;

import com.loxx3450.hw_01_03_25.entity.Ingredient;
import com.loxx3450.hw_01_03_25.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    <T> Page<T> findAllBy(Pageable pageable, Class<T> type);

    <T> T findById(int id, Class<T> type);

    @Query("SELECT r FROM Recipe r JOIN r.ingredients i WHERE i.id IN (:ingredientIds)")
    Page<Recipe> findAllByIngredients(Pageable pageable, List<Integer> ingredientIds);

    <T> Page<T> findAllByOrderByPopularityDesc(Pageable pageable, Class<T> type);
}
