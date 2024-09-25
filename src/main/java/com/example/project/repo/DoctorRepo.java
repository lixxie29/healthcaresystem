package com.example.project.repo;

import com.example.project.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,Long> {
    Doctor findByEmail(String email);
}
