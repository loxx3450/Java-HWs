package com.loxx3450.hw_01_03_25.converter;

import org.springframework.core.convert.converter.Converter;
import com.loxx3450.hw_01_03_25.enums.RecipeDetailLevel;
import org.springframework.stereotype.Component;

@Component
public class StringToRecipeDetailLevelConverter implements Converter<String, RecipeDetailLevel> {

    @Override
    public RecipeDetailLevel convert(String source) {
        return RecipeDetailLevel.fromString(source);
    }
}
