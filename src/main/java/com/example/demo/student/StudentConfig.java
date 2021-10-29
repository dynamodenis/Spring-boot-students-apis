package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner (StudentRepoitory repoitory){
        return args -> {
           Student denis = new Student(
                "Denis",
                "d@gmail.com",
                LocalDate.of(1997, Month.APRIL, 28)
            );

            Student jack = new Student(
                "Jack",
                "jac@gmail.com",
                LocalDate.of(1987, Month.APRIL, 05)
            );

            repoitory.saveAll(List.of(denis, jack));
        };
    }
}
