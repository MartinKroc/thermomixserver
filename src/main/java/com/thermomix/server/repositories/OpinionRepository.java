package com.thermomix.server.repositories;

import com.thermomix.server.models.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionRepository extends JpaRepository<Opinion, Integer> {
}
