package com.thermomix.server.dto;

import com.thermomix.server.models.User;
import com.thermomix.server.models.UserPrincipal;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class UserDto {

    private Long id;
    private String username;
    private List<String> roles;

    public static UserDto build(User user) {
        if (user != null) {
            List<String> roles = user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList());
            return new UserDto(user.getId(), user.getUsername(), roles);
        } else
            return null;
    }

    public static UserDto build(UserPrincipal user) {
        List<String> roles = user.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toList());
        return new UserDto(user.getId(), user.getUsername(), roles);
    }

}
