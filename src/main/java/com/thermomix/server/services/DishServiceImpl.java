package com.thermomix.server.services;

import com.thermomix.server.dto.DishDto;
import com.thermomix.server.models.Dish;
import com.thermomix.server.models.Opinion;
import com.thermomix.server.models.User;
import com.thermomix.server.repositories.DishRepository;
import com.thermomix.server.repositories.OpinionRepository;
import com.thermomix.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final OpinionRepository opinionRepository;

    @Override
    public List<DishDto> getAllDishes() {

/*        for(long i = dishRepository.count();i>0;i--) {
            List<Integer> dishmark = opinionRepository.findByDishId((int)i).stream().map(mark -> Integer.parseInt(mark.getMark())).collect(Collectors.toList());

            int result = dishmark
                    .stream()
                    .reduce(0, (subtotal, element) -> subtotal + element);

            int resu = result/5;

            Dish dish = dishRepository.findById((int)i).orElseThrow(() -> new RuntimeException("Danie nie zostało znalezione"));

            dish.setAvgMark(String.valueOf(resu));
            dishRepository.save(dish);
        }*/

        return dishRepository.findAll().stream().map(dish -> DishDto.build(dish)).collect(Collectors.toList());
    }

    @Override
    public DishDto getDish(int dishId) {
        return DishDto.build(dishRepository.findById(dishId).orElseThrow(() -> new RuntimeException("Danie o id " + dishId + " nie zostało znalezione")));
    }

    @Override
    public ResponseEntity<DishDto> addDish(DishDto dishDto) {
        Dish dish = Dish.builder()
                .name(dishDto.getName())
                .avgMark(dishDto.getAvgMark())
                .category(dishDto.getCategoryRaw())
                .cookingTime(dishDto.getCookingTime())
                .difficulty(dishDto.getDifficultyRaw())
                .ingredients(dishDto.getIngredients())
                .nvalues(dishDto.getNvalues())
                .photo(dishDto.getPhoto())
                .price(dishDto.getPrice())
                .preparing(dishDto.getPreparing())
                .build();
        dishRepository.save(dish);
        return ResponseEntity.ok(DishDto.build(dish));
    }

    @Override
    public ResponseEntity deleteDish(int dishId) {
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new RuntimeException("Danie nie zostało znalezione"));
        dishRepository.delete(dish);
        return ResponseEntity.ok("danie usunięte");
    }

    @Override
    public ResponseEntity editDish(int dishId, DishDto dishDto) {

        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new RuntimeException("Danie nie zostało znalezione"));

        dish.setAvgMark(dishDto.getAvgMark());
        dish.setCategory(dishDto.getCategoryRaw());
        dish.setCookingTime(dishDto.getCookingTime());
        dish.setDifficulty(dishDto.getDifficultyRaw());
        dish.setIngredients(dishDto.getIngredients());
        dish.setName(dishDto.getName());
        dish.setNvalues(dishDto.getNvalues());
        dish.setPhoto(dishDto.getPhoto());
        dish.setPreparing(dishDto.getPreparing());
        dish.setPrice(dishDto.getPrice());

        dishRepository.save(dish);

        return ResponseEntity.ok("zmodyfikowano danie");

    }
}
