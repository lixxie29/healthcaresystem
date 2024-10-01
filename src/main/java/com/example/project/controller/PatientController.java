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
public class PatientController {

    @Autowired
    private PatientService patientService;

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

    @PutMapping("/update_patient/{patient_id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long patient_id, @RequestBody PatientDto patientDto) {
        try{
            patientService.updatePatient(patient_id, patientDto);
            return ResponseEntity.ok(">>> updated patient");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete_patient/{patient_id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long patient_id) {
        try{
            patientService.deletePatient(patient_id);
            return ResponseEntity.ok(">>> deleted patient");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}