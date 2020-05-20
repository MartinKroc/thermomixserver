package com.thermomix.server.services;

import com.thermomix.server.dto.OpinionDto;
import com.thermomix.server.repositories.OpinionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;

    @Override
    public ResponseEntity<List<OpinionDto>> getOpinionsByDish(int dishId) {
        return null;
    }

    @Override
    public ResponseEntity<OpinionDto> createOpinion(OpinionDto opinionDto) {
        return null;
    }
}
