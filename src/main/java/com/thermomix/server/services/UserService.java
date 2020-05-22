package com.thermomix.server.services;

import com.thermomix.server.dto.DishDto;
import com.thermomix.server.dto.DishHistoryDto;
import com.thermomix.server.dto.SigninDto;
import com.thermomix.server.dto.SignupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ResponseEntity<String> signupUser(SignupDto signupDto);

    ResponseEntity signinUser(SigninDto signinDto);

    String test(String username);

    ResponseEntity<DishHistoryDto> getUserDishes(String username);

    ResponseEntity<DishHistoryDto> addUserDish(String username, int dishId);
}
