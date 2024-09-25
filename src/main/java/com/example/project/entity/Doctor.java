package com.example.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String hospitalName;

    private String email;
    private String password;

    @OneToMany(mappedBy = "id")
    private List<Appointment> appointments;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private DoctorSpecialty specialty;

    @OneToMany(mappedBy = "id")
    private List<Prescription> prescriptions;
}
