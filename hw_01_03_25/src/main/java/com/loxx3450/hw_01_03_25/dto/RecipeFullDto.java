package com.loxx3450.hw_01_03_25.dto;

import com.loxx3450.hw_01_03_25.entity.Country;
import com.loxx3450.hw_01_03_25.entity.FoodType;
import com.loxx3450.hw_01_03_25.entity.MealTime;
import lombok.Data;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.List;

@Data
public class RecipeFullDto extends RecipeSummaryDto {
    private String receipt;
    private Country originCountry;
    private FoodType type;

    @PersistenceCreator
    public RecipeFullDto(
            int id,
            String title,
            String description,
            String receipt,
            String link,
            Country originCountry,
            MealTime mealTime,
            FoodType type,
            int popularity) {

        super(id, title, description, link, mealTime, popularity);

        this.receipt = receipt;
        this.originCountry = originCountry;
        this.type = type;
    }
}
