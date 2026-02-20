package com.example.studentapp.repository;

import com.example.studentapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by roll number
    Optional<Student> findByRollNo(String rollNo);

    // Find student by phone number
    Optional<Student> findByPhoneNumber(String phoneNumber);

    // Search students by name (case-insensitive)
    List<Student> findByNameContainingIgnoreCase(String name);

    // Check if roll number already exists
    boolean existsByRollNo(String rollNo);

    // Check if phone number already exists
    boolean existsByPhoneNumber(String phoneNumber);
}
