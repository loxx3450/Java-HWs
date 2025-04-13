package com.loxx3450.hw_01_03_25.dto;

import com.loxx3450.hw_01_03_25.entity.Country;
import com.loxx3450.hw_01_03_25.entity.FoodType;
import com.loxx3450.hw_01_03_25.entity.MealTime;
import lombok.Data;
import org.springframework.data.annotation.PersistenceCreator;

@Data
public class RecipeSummaryDto {
    private int id;
    private String title;
    private String description;
    private String link;
    private MealTime mealTime;
    private int popularity;

    @PersistenceCreator
    public RecipeSummaryDto(int id, String title, String description, String link, MealTime mealTime, int popularity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.mealTime = mealTime;
        this.popularity = popularity;
    }
}
