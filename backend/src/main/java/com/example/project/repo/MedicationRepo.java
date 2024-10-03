package com.example.project.repo;

import com.example.project.entity.Appointment;
import com.example.project.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepo extends JpaRepository<Medication, Long> {
    List<Medication> findAllByPrescriptionsId(Long prescriptionId);
}
