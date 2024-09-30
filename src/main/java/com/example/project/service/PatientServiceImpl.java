package com.example.project.service;


import com.example.project.dtos.PatientDto;
import com.example.project.entity.Doctor;
import com.example.project.entity.Patient;
import com.example.project.repo.PatientRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
        if (patientRepo.findByEmail(patientDto.getEmail()) != null) {
            throw new EntityExistsException(" >>> patient with this email already exists");
        }

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
    public PatientDto findPatientByEmail(String email) {
        return this.mapToPatientDto(patientRepo.findByEmail(email));
    }

    @Override
    public List<PatientDto> findAllPatients() {
        List<Patient> patients = patientRepo.findAll();
        return patients.stream()
                .map(this::mapToPatientDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean authenticatePatient(String email, String password) {
        Patient patient = patientRepo.findByEmail(email);
        return patient!=null && passwordEncoder.matches(password, patient.getPassword());
    }

    @Override
    public boolean deletePatient(Long id) {
        if(patientRepo.existsById(id)) {
            patientRepo.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException(" >>> could not properly delete user, user not found");
    }

    @Override
    public boolean updatePatient(Long patientId, PatientDto patientDto) {
        if(patientRepo.existsById(patientId)) {
            Patient patient = patientRepo.findById(patientId).orElseThrow();
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setAge(patientDto.getAge());
            patient.setGender(patientDto.getGender());
            patient.setEmail(patientDto.getEmail());
            patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
            patientRepo.save(patient);
            return true;
        }
        throw new EntityNotFoundException(" >>> could not properly update patient based on patient_id");
    }

    private PatientDto mapToPatientDto(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setAge(patient.getAge());
        patientDto.setGender(patient.getGender());
        patientDto.setEmail(patient.getEmail());
        patientDto.setPassword(patient.getPassword());
        return patientDto;
    }
}
