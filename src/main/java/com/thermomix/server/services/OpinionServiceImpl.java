package com.thermomix.server.services;

import com.thermomix.server.dto.OpinionDto;
import com.thermomix.server.models.Dish;
import com.thermomix.server.models.Opinion;
import com.thermomix.server.models.User;
import com.thermomix.server.repositories.DishRepository;
import com.thermomix.server.repositories.OpinionRepository;
import com.thermomix.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;

    @Override
    public List<OpinionDto> getOpinionsByDish(int dishId) {
        List<Opinion> list = opinionRepository.findByDishId(dishId);
        return list.stream().map(opinion -> OpinionDto.build(opinion)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<OpinionDto> createOpinion(String username, OpinionDto opinionDto) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie został znaleziony"));
        Dish dish = dishRepository.findById(opinionDto.getDishId()).orElseThrow(() -> new UsernameNotFoundException("Danie nie zostało znalezione"));

        Opinion opinion = Opinion.builder()
                .content(opinionDto.getContent())
                .mark(opinionDto.getMark())
                .dish(dish)
                .userId(user)
                .build();

        opinionRepository.save(opinion);
        return ResponseEntity.ok(OpinionDto.build(opinion));
    }
}
