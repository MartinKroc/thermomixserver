package com.thermomix.server.services;

import com.thermomix.server.dto.DishDto;
import com.thermomix.server.dto.DishHistoryDto;
import com.thermomix.server.dto.SigninDto;
import com.thermomix.server.dto.SignupDto;
import com.thermomix.server.models.Dish;
import com.thermomix.server.models.User;
import com.thermomix.server.models.UserPrincipal;
import com.thermomix.server.repositories.DishRepository;
import com.thermomix.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    UserRepository userRepository;
    DishRepository dishRepository;

    @Override
    public ResponseEntity<String> signupUser(SignupDto signupDto) {
        return null;
    }

    public ResponseEntity signinUser(SigninDto signinDto) {
        return null;
    }

    @Override
    public String test(String username) {

        return username;
    }

    @Override
    public ResponseEntity<DishHistoryDto> getUserDishes(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ResponseEntity.ok(DishHistoryDto.build(user));
    }

    @Override
    public ResponseEntity<DishHistoryDto> addUserDish(String username, int dishId) {

        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new RuntimeException("Produkt nie został znaleziony"));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Użytkownik nie został znaleziony"));

        user.getUserDishes().add(dish);
        userRepository.save(user);

        return ResponseEntity.ok(DishHistoryDto.build(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return UserPrincipal.build(user);
    }

}
