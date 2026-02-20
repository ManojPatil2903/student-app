package com.example.studentapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data                   // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor      // Generates default constructor
@AllArgsConstructor     // Generates all-args constructor
@Builder                // Enables builder pattern
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roll_no", unique = true, nullable = false)
    @NotBlank(message = "Roll number is required")
    private String rollNo;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Column(name = "address")
    @NotBlank(message = "Address is required")
    private String address;

    @Column(name = "phone_number", unique = true)
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Column(name = "email")
    @Email(message = "Please provide a valid email")
    private String email;
}
