package com.example.project.repo;

import com.example.project.entity.DoctorSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorSpecialtyRepo extends JpaRepository<DoctorSpecialty,Long> {
}
