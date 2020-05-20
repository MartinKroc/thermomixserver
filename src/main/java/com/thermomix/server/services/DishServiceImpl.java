package com.thermomix.server.services;

import com.thermomix.server.dto.DishDto;
import com.thermomix.server.repositories.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public List<DishDto> getAllDishes() {
        return null;
    }

    @Override
    public DishDto getDish(int dishId) {
        return null;
    }

    @Override
    public ResponseEntity<DishDto> addDish(DishDto dishDto) {
        return null;
    }

    @Override
    public ResponseEntity deleteDish(int dishId) {
        return null;
    }

    @Override
    public ResponseEntity editDish(int dishId, DishDto dishDto) {
        return null;
    }
}
