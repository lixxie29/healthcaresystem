package com.example.project.controller;

import com.example.project.service.PatientService;
import com.example.project.dtos.DoctorDto;
import com.example.project.dtos.PatientDto;
import com.example.project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors() {
        return doctorService.findAllDoctors();
    }

    @PostMapping("/register_doctor")
    public String saveDoctor(@RequestBody DoctorDto doctorDto) {
        doctorService.saveDoctor(doctorDto);
        return "saved doctor";
    }

    @GetMapping("/find_doctor_email/{email}")
    public DoctorDto findDoctorByEmail(@PathVariable String email) {
        return doctorService.findDoctorByEmail(email);
    }

    @GetMapping("/patients")
    public List<PatientDto> getPatients() {return patientService.findAllPatients();}

    @PostMapping("/register_patient")
    public String savePatient(@RequestBody PatientDto patientDto) {
        patientService.savePatient(patientDto);
        return "saved patient";
    }

    @GetMapping("/find_patient_email/{email}")
    public PatientDto findPatientByEmail(@PathVariable String email) {
        return patientService.findPatientByEmail(email);
    }
}
