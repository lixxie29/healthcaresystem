package com.example.project.service;


import com.example.project.dtos.PatientDto;
import com.example.project.entity.Patient;
import com.example.project.repo.PatientRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepo;
    private final PasswordEncoder passwordEncoder;

    public PatientServiceImpl(PatientRepo patientRepo, PasswordEncoder passwordEncoder) {
        this.patientRepo = patientRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void savePatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setAge(patientDto.getAge());
        patient.setGender(patientDto.getGender());
        patient.setEmail(patientDto.getEmail());
        patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        patientRepo.save(patient);
    }

    @Override
    public Patient findPatientByEmail(String email) {
        return patientRepo.findByEmail(email);
    }

    @Override
    public List<PatientDto> findAllPatients() {
        List<Patient> patients = patientRepo.findAll();
        return patients.stream()
                .map((Patient) -> mapToPatientDto(Patient))
                .collect(Collectors.toList());
    }

    private PatientDto mapToPatientDto(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setAge(patient.getAge());
        patientDto.setGender(patient.getGender());
        patientDto.setEmail(patient.getEmail());
        patientDto.setPassword(passwordEncoder.encode(patient.getPassword()));
        return patientDto;
    }
}
