package com.thermomix.server.services;

import com.thermomix.server.dto.SigninDto;
import com.thermomix.server.dto.SignupDto;
import com.thermomix.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public ResponseEntity<String> signupUser(SignupDto signupDto) {
        return null;
    }

    public ResponseEntity signinUser(SigninDto signinDto) {
        return null;
    }

    @Override
    public String test() {
        return "test works";
    }

}
