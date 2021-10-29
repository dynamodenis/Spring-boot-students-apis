package com.example.demo.student;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

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

    // Update student
    @Transactional
    public void updateStudent(Long id, String name, String email){
        Student student = studentRepoitory.findById(id).orElseThrow(() ->  new IllegalStateException("User does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        // validate email
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentByEmail = studentRepoitory.findByEmail(student.getEmail());

            // if another user by that user is present
            if(studentByEmail.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            
            student.setEmail(email);
        }
    }
}
