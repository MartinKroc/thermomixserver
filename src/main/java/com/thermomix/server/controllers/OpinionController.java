package com.thermomix.server.controllers;

import com.thermomix.server.dto.OpinionDto;
import com.thermomix.server.services.OpinionService;
import com.thermomix.server.utilities.AuthMiner;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/opinion")
@AllArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;

    @GetMapping("{id}")
    public List<OpinionDto> getOpinionsByDish(@PathVariable("id") int dishId) {
        return opinionService.getOpinionsByDish(dishId);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OpinionDto> createOpinion(Authentication authentication, @RequestBody OpinionDto opinionDto) {
        return opinionService.createOpinion(AuthMiner.getUsername(authentication) , opinionDto);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity noHandlerFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
