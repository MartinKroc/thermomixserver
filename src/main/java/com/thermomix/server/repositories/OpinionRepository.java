package com.thermomix.server.repositories;

import com.thermomix.server.models.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpinionRepository extends JpaRepository<Opinion, Integer> {
    List<Opinion> findByDishId(int dishId);
}
