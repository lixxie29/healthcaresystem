package com.example.project.controller;

import com.example.project.dtos.LoginDto;
import com.example.project.entity.Doctor;
import com.example.project.service.AppointmentService;
import com.example.project.service.PatientService;
import com.example.project.dtos.DoctorDto;
import com.example.project.dtos.PatientDto;
import com.example.project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return ">>> registered doctor";
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
        return ">>> registered patient";
    }

    @GetMapping("/find_patient_email/{email}")
    public PatientDto findPatientByEmail(@PathVariable String email) {
        return patientService.findPatientByEmail(email);
    }

    @PostMapping("/login_doctor")
    public ResponseEntity<String> loginDoctor(@RequestBody LoginDto loginDto) {
        boolean isAuthenticated = doctorService.authenticateDoctor(loginDto.getEmail(), loginDto.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok(">>> login successful");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid email or password");
        }
    }

    @PostMapping("/login_patient")
    public ResponseEntity<String> loginPatient(@RequestBody LoginDto loginDto) {
        boolean isAuthenticated = patientService.authenticatePatient(loginDto.getEmail(), loginDto.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok(">>> login successful");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid email or password");
        }
    }
//    @GetMapping("/medications") the id doesn't matter here because everyone can see meds

}
