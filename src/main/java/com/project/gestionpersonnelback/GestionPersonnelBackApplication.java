package com.project.gestionpersonnelback;

import com.project.gestionpersonnelback.entities.OurUsers;
import com.project.gestionpersonnelback.repositories.UsersRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionPersonnelBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionPersonnelBackApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UsersRepo usersRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            usersRepo.save(new OurUsers(null, "youness@gmail.com", "Youness", "0663368766",passwordEncoder.encode("password"), "ADMIN"));
        };
    }
}
