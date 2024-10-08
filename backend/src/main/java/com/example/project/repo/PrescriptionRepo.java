package com.example.project.repo;

import com.example.project.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByDoctorId(Long doctorId);
    List<Prescription> findAllByPatientId(Long patientId);
}
