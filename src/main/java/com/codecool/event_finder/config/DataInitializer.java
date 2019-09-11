package com.codecool.event_finder.config;

import com.codecool.event_finder.entity.AppUser;
import com.codecool.event_finder.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer implements CommandLineRunner {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception{

        appUserRepository.save(AppUser.builder()
                .name("user")
                .password(encoder.encode("password"))
                .role("ROLE_USER")
                .build());

        appUserRepository.save(AppUser.builder()
                .name("admin")
                .password(encoder.encode("password"))
                .role("ROLE_USER")
                .role("ROLE_ADMIN")
                .build());
    }
}