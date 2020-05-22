package com.thermomix.server.setup;

import com.thermomix.server.models.authority.Role;
import com.thermomix.server.models.authority.RoleName;
import com.thermomix.server.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class RoleDbStart implements ApplicationRunner {

    RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (roleRepository.count() <= 0) {
            try {
                roleRepository.saveAll(Arrays.asList(
                        new Role(RoleName.ROLE_USER),
                        new Role(RoleName.ROLE_ADMIN)));
            } catch (Error e) {
                System.out.println("Bład dodawania roli do bazy danych");
            }
        }
    }
}
