package com.thermomix.server.controllers;

import com.thermomix.server.dto.*;
import com.thermomix.server.services.AuthorizationService;
import com.thermomix.server.services.UserService;
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
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthorizationService authorizationService;

    @GetMapping("hello")
    public String hello() {
        return "it works";
    }

    @GetMapping("test")
    @PreAuthorize("hasRole('USER')")
    public String test(Authentication authentication) {
        return userService.test(AuthMiner.getUsername(authentication));
    }

    @PostMapping("signup")
    public ResponseEntity<String> signupUser(@RequestBody SignupDto signupDto) {
        return authorizationService.createUser(signupDto);
    }

    @PostMapping("signin")
    public ResponseEntity signinUser(@RequestBody SigninDto signinDto) {
        return authorizationService.loginUser(signinDto);
    }

    @GetMapping("history")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DishHistoryDto> getUserDishes(Authentication authentication) {
        return userService.getUserDishes(AuthMiner.getUsername(authentication));
    }

    @GetMapping("history/add/{dishId}")
    public ResponseEntity<DishHistoryDto> addUserDish(Authentication authentication, @PathVariable("dishId")int dishId) {
        return userService.addUserDish(AuthMiner.getUsername(authentication), dishId);
    }

    @GetMapping("options")
    public ResponseEntity<OptionDto> getOptions(Authentication authentication) {
        return userService.getOptions(AuthMiner.getUsername(authentication));
    }

    @PutMapping("options")
    public ResponseEntity<ChangeOptionDto> setOptions(Authentication authentication, @RequestBody ChangeOptionDto changeOptionDto) {
        return userService.setOptions(AuthMiner.getUsername(authentication), changeOptionDto);
    }

    @GetMapping("options/update")
    public ResponseEntity<String> checkUpdate(Authentication authentication) {
        return userService.checkUpdate(AuthMiner.getUsername(authentication));
    }

    @GetMapping("options/cleaning")
    public ResponseEntity<String> changeCleaning(Authentication authentication) {
        return userService.changeCleaning(AuthMiner.getUsername(authentication));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity noHandlerFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
