package com.thermomix.server.repositories;

import com.thermomix.server.models.authority.Role;
import com.thermomix.server.models.authority.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
