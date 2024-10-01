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
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors() {
        return doctorService.findAllDoctors();
    }

    @PostMapping("/register_doctor")
    public ResponseEntity<Void> saveDoctor(@RequestBody DoctorDto doctorDto) {
        doctorService.saveDoctor(doctorDto);  // Void method call
        return ResponseEntity.ok().build();  // Return HTTP 200 OK with no body
    }

//    @GetMapping("/find_doctor_email/{email}")
//    public DoctorDto findDoctorByEmail(@PathVariable String email) {
//        return doctorService.findDoctorByEmail(email);
//    }

    @GetMapping("/find_doctor_email/{email}")
    public ResponseEntity<DoctorDto> findDoctorByEmail(@PathVariable String email) {
        DoctorDto doctorDto = doctorService.findDoctorByEmail(email);
        if(doctorDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else{
            return (ResponseEntity<DoctorDto>) ResponseEntity.ok(doctorDto);
        }
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

    @PutMapping("/update_doctor/{doctor_id}")
    public ResponseEntity<String> updateDoctor(@PathVariable Long doctor_id, @RequestBody DoctorDto doctorDto) {
        try{
            doctorService.updateDoctor(doctor_id, doctorDto);
            return ResponseEntity.ok(">>> updated doctor");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete_doctor/{doctor_id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctor_id) {
        try{
            doctorService.deleteDoctor(doctor_id);
            return ResponseEntity.ok(">>> deleted doctor");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}