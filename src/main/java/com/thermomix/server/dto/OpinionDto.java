package com.thermomix.server.dto;

import com.thermomix.server.models.Opinion;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OpinionDto {

    private String username;
    private String mark;
    private String content;

    public static OpinionDto build(Opinion opinion) {
        return new OpinionDto(
                opinion.getUserId().getUsername(),
                opinion.getMark(),
                opinion.getContent()
        );
    }
}
