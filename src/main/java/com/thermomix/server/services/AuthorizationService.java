package com.thermomix.server.services;

import com.thermomix.server.dto.SigninDto;
import com.thermomix.server.dto.SignupDto;
import com.thermomix.server.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface AuthorizationService {

    ResponseEntity<String> createUser(SignupDto signUpDto);

    ResponseEntity loginUser(SigninDto signInDto);

    UserDto getUser(Authentication authentication);

}
