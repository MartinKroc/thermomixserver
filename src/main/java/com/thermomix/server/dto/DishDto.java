package com.thermomix.server.dto;

import com.thermomix.server.models.Dish;
import com.thermomix.server.models.enums.DishCategory;
import com.thermomix.server.models.enums.DishDificulty;
import lombok.Value;

@Value
public class DishDto {

    private int id;
    private String name;
    private String photo;
    private String price;
    private String cookingTime;
    private String avgMark;
    private String ingredients;
    private String nvalues;
    private String preparing;

    private String category;
    private String difficulty;

    private DishCategory categoryRaw;
    private DishDificulty difficultyRaw;

    public static DishDto build(Dish dish) {
        return new DishDto(
                dish.getId(),
                dish.getName(),
                dish.getPhoto(),
                dish.getPrice(),
                dish.getCookingTime(),
                dish.getAvgMark(),
                dish.getIngredients(),
                dish.getNvalues(),
                dish.getPreparing(),
                dish.getCategory().toString(),
                dish.getDifficulty().toString(),
                dish.getCategory(),
                dish.getDifficulty()
        );
    }
}
