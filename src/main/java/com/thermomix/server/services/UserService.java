package com.thermomix.server.services;

import com.thermomix.server.dto.*;
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

    ResponseEntity<OptionDto> getOptions(String username);

    ResponseEntity<ChangeOptionDto> setOptions(String username, ChangeOptionDto changeOptionDto);

    ResponseEntity<String> checkUpdate(String username);

    ResponseEntity<String> changeCleaning(String username);
}
