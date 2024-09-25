package com.example.project.service;

import com.example.project.dtos.PatientDto;
import com.example.project.entity.Patient;

import java.util.List;

public interface PatientService {
    void savePatient(PatientDto patientDto);
    Patient findPatientByEmail(String email);
    List<PatientDto> findAllPatients();
}
