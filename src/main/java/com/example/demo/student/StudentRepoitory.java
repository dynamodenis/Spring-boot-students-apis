package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepoitory extends JpaRepository<Student, Long>{

    // Call to check if there is another email existing
    Optional<Student> findByEmail(String email);
}
