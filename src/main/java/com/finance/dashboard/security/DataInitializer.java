package com.finance.dashboard.security;

import com.finance.dashboard.model.Role;
import com.finance.dashboard.model.User;
import com.finance.dashboard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SuppressWarnings("null")
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .name("Admin User")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .status(true)
                    .build();

            User analyst = User.builder()
                    .name("Analyst User")
                    .email("analyst@example.com")
                    .password(passwordEncoder.encode("analyst123"))
                    .role(Role.ANALYST)
                    .status(true)
                    .build();

            User viewer = User.builder()
                    .name("Viewer User")
                    .email("viewer@example.com")
                    .password(passwordEncoder.encode("viewer123"))
                    .role(Role.VIEWER)
                    .status(true)
                    .build();

            userRepository.saveAll(List.of(admin, analyst, viewer));
            System.out.println("Sample users have been initialized successfully.");
        }
    }
}
