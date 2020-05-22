package com.thermomix.server.controllers;

import com.thermomix.server.dto.DishDto;
import com.thermomix.server.services.DishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/dish")
@AllArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public List<DishDto> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("{id}")
    public DishDto getDish(@PathVariable("id") int dishId) {
        return dishService.getDish(dishId);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DishDto> addDish(@RequestBody DishDto dishDto) {
        return dishService.addDish(dishDto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteDish(@PathVariable("id") int dishId) {
        return dishService.deleteDish(dishId);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity editDish(@PathVariable("id") int dishId, @RequestBody DishDto dishDto) {
        return dishService.editDish(dishId, dishDto);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity noHandlerFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
