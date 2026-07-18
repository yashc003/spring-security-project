package org.example.springsecurityproject.config;

import org.example.springsecurityproject.entity.AppUser;
import org.example.springsecurityproject.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedUsers(AppUserRepository repository,PasswordEncoder passwordEncoder){
        return args -> {
            if (repository.findByUsername("john").isEmpty()) {
                AppUser john = new AppUser();
                john.setUsername("john");
                john.setPassword(passwordEncoder.encode("1234"));
                john.setRole("USER");
                repository.save(john);
            }
            if (repository.findByUsername("admin").isEmpty()){
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole("ADMIN");
                repository.save(admin);

            }
        };
    }

}
