package com.example.project.service;

import com.example.project.dtos.PatientDto;

import java.util.List;

public interface PatientService {
    void savePatient(PatientDto patientDto);
    PatientDto findPatientByEmail(String email);
    List<PatientDto> findAllPatients();
}
