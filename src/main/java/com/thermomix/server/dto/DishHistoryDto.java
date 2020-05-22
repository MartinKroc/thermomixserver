package com.thermomix.server.dto;

import com.thermomix.server.models.User;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class DishHistoryDto {

    private final Long id;
    private final List<DishDto> userDishes;

    public static DishHistoryDto build(User user) {
        List<DishDto> con = user.getUserDishes().stream().map(product -> DishDto.build(product)).collect(Collectors.toList());
        return new DishHistoryDto(user.getId(), con);
    }
}
