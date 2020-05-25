package com.thermomix.server.services;

import com.thermomix.server.configuration.jwt.JwtProvider;
import com.thermomix.server.dto.*;
import com.thermomix.server.models.*;
import com.thermomix.server.models.authority.Role;
import com.thermomix.server.models.authority.RoleName;
import com.thermomix.server.repositories.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider provider;
    private final AuthenticationManager manager;
    private final OpinionService opinionService;


    @Override
    public ResponseEntity<String> createUser(SignupDto signUpDto) {

        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return ResponseEntity.badRequest().body("Użytkownik " + signUpDto.getUsername() + " już istnieje");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository
                .findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Rola nie istnieje")));

        User user = new User(signUpDto.getUsername(),
                passwordEncoder.encode(signUpDto.getPassword()),
                roles);

        user.setRegDate(LocalDate.now());
        user.setCountry("Polska");
        user.setLanguage("Polski");
        user.setSysMeter("metryczne");
        user.setLastClean(LocalDate.now());


        userRepository.save(user);

        return ResponseEntity.ok("Użytkownik pomyślnie został zarejestrowany");
    }

    @Override
    public ResponseEntity loginUser(SigninDto signInDto) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDto.getUsername(),
                        signInDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = provider.generateToken(authentication);

        return ResponseEntity.ok(new JwtTokenDto(token));
    }

    @Override
    public UserDto getUser(Authentication authentication) {
        return UserDto.build((UserPrincipal) authentication.getPrincipal());
    }
}
