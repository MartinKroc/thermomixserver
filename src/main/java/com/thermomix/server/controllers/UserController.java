package com.thermomix.server.controllers;

import com.thermomix.server.dto.SigninDto;
import com.thermomix.server.dto.SignupDto;
import com.thermomix.server.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<String> signupUser(@RequestBody SignupDto signupDto) {
        return userService.signupUser(signupDto);
    }

    @PostMapping("signin")
    public ResponseEntity signinUser(@RequestBody SigninDto signinDto) {
        return userService.signinUser(signinDto);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity noHandlerFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
