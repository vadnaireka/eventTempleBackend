package com.codecool.event_finder.controller;


import com.codecool.event_finder.config.JwtTokenServices;
import com.codecool.event_finder.entity.UserCredentials;
import com.codecool.event_finder.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    public ValidationController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, AppUserRepository users) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserCredentials data) {
        try {
            String username = data.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }


}