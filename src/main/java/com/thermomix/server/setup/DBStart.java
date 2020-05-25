package com.thermomix.server.setup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thermomix.server.models.Dish;
import com.thermomix.server.models.SoftProperties;
import com.thermomix.server.repositories.DishRepository;
import com.thermomix.server.repositories.SoftPropertiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class DBStart implements ApplicationRunner {

    DishRepository dishRepository;
    SoftPropertiesRepo softPropertiesRepo;

    @Autowired
    public DBStart(DishRepository dishRepository, SoftPropertiesRepo softPropertiesRepo) {
        this.dishRepository = dishRepository;
        this.softPropertiesRepo = softPropertiesRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (dishRepository.count() <= 0) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<Dish> dishes = mapper.readValue(
                        new File("src/main/resources/dishes.json"),
                        new TypeReference<List<Dish>>() {
                        }
                );
                dishes.forEach(el -> dishRepository.save(el));
            } catch (Error e) {
                System.out.println("Bład dodawania dania do bazy danych");
            }
        }

        if (softPropertiesRepo.count() <= 0) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<SoftProperties> props = mapper.readValue(
                        new File("src/main/resources/sprop.json"),
                        new TypeReference<List<SoftProperties>>() {
                        }
                );
                props.forEach(el -> softPropertiesRepo.save(el));
            } catch (Error e) {
                System.out.println("Bład dodawania ustawien");
            }
        }
    }

}
