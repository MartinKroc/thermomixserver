package com.thermomix.server.services;

import com.thermomix.server.dto.OpinionDto;
import com.thermomix.server.models.Dish;
import com.thermomix.server.models.Opinion;
import com.thermomix.server.repositories.DishRepository;
import com.thermomix.server.repositories.OpinionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;
    private final DishRepository dishRepository;

    @Override
    public List<OpinionDto> getOpinionsByDish(int dishId) {
        List<Opinion> list = opinionRepository.findByDishId(dishId);
        return list.stream().map(opinion -> OpinionDto.build(opinion)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<OpinionDto> createOpinion(OpinionDto opinionDto) {
        return null;
    }
}
