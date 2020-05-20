package com.thermomix.server.repositories;

import com.thermomix.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
