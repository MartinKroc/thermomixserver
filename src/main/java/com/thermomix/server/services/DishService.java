package com.thermomix.server.services;

import com.thermomix.server.dto.DishDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService {

    List<DishDto> getAllDishes();

    DishDto getDish(int dishId);

    ResponseEntity<DishDto> addDish(DishDto dishDto);

    ResponseEntity deleteDish(int dishId);

    ResponseEntity editDish(int dishId, DishDto dishDto);

}
