package com.thermomix.server.dto;

import com.thermomix.server.models.SoftProperties;
import com.thermomix.server.models.User;
import lombok.Value;

import java.time.LocalDate;

@Value
public class ChangeOptionDto {

    private String language;
    private String country;
    private int voiceLevel;
    private int screenBright;
    private String sysMeter;

    public static ChangeOptionDto build(User user) {
        return new ChangeOptionDto(
                user.getLanguage(),
                user.getCountry(),
                user.getVoiceLevel(),
                user.getScreenBright(),
                user.getSysMeter()
        );
    }

}
