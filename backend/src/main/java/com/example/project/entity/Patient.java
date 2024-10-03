package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    private String email;
    private String password;

    @OneToMany(mappedBy = "id")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "id")
    private List<Prescription> prescriptions;
}
