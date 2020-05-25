package com.thermomix.server.repositories;

import com.thermomix.server.models.SoftProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftPropertiesRepo extends JpaRepository<SoftProperties, Integer> {
}
