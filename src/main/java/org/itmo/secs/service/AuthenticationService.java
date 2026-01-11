package org.itmo.secs.service;

import org.itmo.secs.model.dto.JwtAuthResponse;
import org.itmo.secs.model.dto.UserDTO;
import org.itmo.secs.model.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponse signUp(UserDTO request) {

        var user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(UserDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.username());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }
}