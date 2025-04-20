package com.quocdoansam.schoolsystem.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quocdoansam.schoolsystem.entity.Admin;
import com.quocdoansam.schoolsystem.enums.Role;
import com.quocdoansam.schoolsystem.repository.AdminRepository;
import com.quocdoansam.schoolsystem.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppInitConfig {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            if (userRepository.findByName("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

                Admin admin = Admin
                        .builder()
                        .password(passwordEncoder.encode("admin"))
                        .name("admin")
                        .roles(roles)
                        .level("intern")
                        .build();

                adminRepository.save(admin);
                log.warn("Admin user has been created with default password \"admin\". Please change it.");
            }
        };
    }
}
