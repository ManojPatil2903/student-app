package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ── Create ────────────────────────────────────────────
    public Student saveStudent(Student student) {
        if (studentRepository.existsByRollNo(student.getRollNo())) {
            throw new RuntimeException("Student with Roll No " + student.getRollNo() + " already exists");
        }
        if (studentRepository.existsByPhoneNumber(student.getPhoneNumber())) {
            throw new RuntimeException("Phone number " + student.getPhoneNumber() + " already registered");
        }
        return studentRepository.save(student);
    }

    // ── Read All ──────────────────────────────────────────
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ── Read By ID ────────────────────────────────────────
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    // ── Read By Roll No ───────────────────────────────────
    public Student getStudentByRollNo(String rollNo) {
        return studentRepository.findByRollNo(rollNo)
                .orElseThrow(() -> new RuntimeException("Student not found with Roll No: " + rollNo));
    }

    // ── Search By Name ────────────────────────────────────
    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    // ── Update ────────────────────────────────────────────
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);

        existing.setName(updatedStudent.getName());
        existing.setAddress(updatedStudent.getAddress());
        existing.setPhoneNumber(updatedStudent.getPhoneNumber());
        existing.setEmail(updatedStudent.getEmail());
        // Roll No is not updated to preserve uniqueness

        return studentRepository.save(existing);
    }

    // ── Delete ────────────────────────────────────────────
    public String deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
        return "Student with ID " + id + " deleted successfully";
    }
}
