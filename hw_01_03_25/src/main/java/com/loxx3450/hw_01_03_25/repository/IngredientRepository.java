package com.loxx3450.hw_01_03_25.repository;

import com.loxx3450.hw_01_03_25.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
