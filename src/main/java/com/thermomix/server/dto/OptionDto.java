package com.thermomix.server.dto;

import com.thermomix.server.models.SoftProperties;
import com.thermomix.server.models.User;
import lombok.Value;

import java.time.LocalDate;

@Value
public class OptionDto {

    private String version;
    private String receipeVer;
    private String lastUpdate;

    private String username;
    private String language;
    private String country;
    private int voiceLevel;
    private int screenBright;
    private String sysMeter;
    private LocalDate regDate;
    private LocalDate lastClean;

    public static OptionDto build(SoftProperties softProperties, User user) {
        return new OptionDto(
                softProperties.getVersion(),
                softProperties.getReceipeVer(),
                softProperties.getLastUpdate(),
                user.getUsername(),
                user.getLanguage(),
                user.getCountry(),
                user.getVoiceLevel(),
                user.getScreenBright(),
                user.getSysMeter(),
                user.getRegDate(),
                user.getLastClean()
        );
    }
}
