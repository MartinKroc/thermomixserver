package com.thermomix.server.services;

import com.thermomix.server.dto.*;
import com.thermomix.server.models.Dish;
import com.thermomix.server.models.SoftProperties;
import com.thermomix.server.models.User;
import com.thermomix.server.models.UserPrincipal;
import com.thermomix.server.repositories.DishRepository;
import com.thermomix.server.repositories.SoftPropertiesRepo;
import com.thermomix.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    UserRepository userRepository;
    DishRepository dishRepository;
    SoftPropertiesRepo softPropertiesRepo;

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
    public ResponseEntity<OptionDto> getOptions(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Użytkownik nie został znaleziony"));
        SoftProperties softProperties = softPropertiesRepo.findById(1).orElseThrow(() -> new RuntimeException("Ustawienia nie znaleziono"));

        return ResponseEntity.ok(OptionDto.build(softProperties,user));
    }

    @Override
    public ResponseEntity<ChangeOptionDto> setOptions(String username, ChangeOptionDto changeOptionDto) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Użytkownik nie został znaleziony"));

        user.setLanguage(changeOptionDto.getLanguage());
        user.setCountry(changeOptionDto.getCountry());
        user.setVoiceLevel(changeOptionDto.getVoiceLevel());
        user.setScreenBright(changeOptionDto.getScreenBright());
        user.setSysMeter(changeOptionDto.getSysMeter());

        userRepository.save(user);

        return ResponseEntity.ok(ChangeOptionDto.build(user));
    }

    @Override
    public ResponseEntity<String> checkUpdate(String username) {

        SoftProperties softProperties = softPropertiesRepo.findById(1).orElseThrow(() -> new RuntimeException("Ustawienia nie znaleziono"));
        if(softProperties.getVersion().equals("1.1")) {
            return ResponseEntity.ok("Oprogramowanie nie jest aktualne");
        }
        else return ResponseEntity.ok("Oprogramowanie jest aktualne");
    }

    @Override
    public ResponseEntity<String> changeCleaning(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Użytkownik nie został znaleziony"));

        user.setLastClean(LocalDate.now());
        userRepository.save(user);

        return ResponseEntity.ok("zmieniono date ostateniego czyszczenia");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return UserPrincipal.build(user);
    }

}
