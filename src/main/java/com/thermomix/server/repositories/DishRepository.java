package com.thermomix.server.repositories;

import com.thermomix.server.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
