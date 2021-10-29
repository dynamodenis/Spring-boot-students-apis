package com.example.demo.student;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepoitory studentRepoitory;

    @Autowired
    public StudentService(StudentRepoitory studentRepoitory) {
        this.studentRepoitory = studentRepoitory;
    }
    public List<Student> getStudents() {
		return studentRepoitory.findAll();
	}

    public void addStudent(Student student){
        Optional<Student> studentByEmail = studentRepoitory.findByEmail(student.getEmail());

        // if another user by that user is present
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        // Else save the student
        studentRepoitory.save(student);
    }

    // delete a student
    public void deleteStudent(Long id){
        boolean exists = studentRepoitory.existsById(id);

        if(!exists){
            throw new IllegalStateException("Student with id does not exist");
        }
        studentRepoitory.deleteById(id);
    }
}
