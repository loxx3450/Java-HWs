package com.loxx3450.hw_01_03_25.enums;

import com.loxx3450.hw_01_03_25.dto.RecipeFullDto;
import com.loxx3450.hw_01_03_25.dto.RecipeSummaryDto;

public enum RecipeDetailLevel {
    SUMMARY,
    FULL;

    public Class toType() {
        return switch (this) {
            case SUMMARY -> RecipeSummaryDto.class;
            case FULL -> RecipeFullDto.class;
        };
    }

    public static RecipeDetailLevel fromString(String text) {
        if (text == null) {
            return SUMMARY;
        }

        for (RecipeDetailLevel level : RecipeDetailLevel.values()) {
            if (level.name().equalsIgnoreCase(text)) {
                return level;
            }
        }
        return SUMMARY;
    }
}

