package com.thermomix.server.services;

import com.thermomix.server.dto.SigninDto;
import com.thermomix.server.dto.SignupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<String> signupUser(SignupDto signupDto);

    ResponseEntity signinUser(SigninDto signinDto);

    String test();

}
