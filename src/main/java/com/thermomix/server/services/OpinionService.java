package com.thermomix.server.services;

import com.thermomix.server.dto.OpinionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OpinionService {

    List<OpinionDto> getOpinionsByDish(int dishId);

    ResponseEntity<OpinionDto> createOpinion(OpinionDto opinionDto);

}
