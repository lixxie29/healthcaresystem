package com.example.project.controller;

import com.example.project.dtos.AppointmentDto;
import com.example.project.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @DeleteMapping("/delete_appointment_by_patient/{appointment_id}")
    public ResponseEntity<String> deleteAppointmentByPatient(@PathVariable Long appointment_id, @RequestParam Long patient_id) {
        try{
            appointmentService.deleteAppointmentByPatient(appointment_id, patient_id);
            return ResponseEntity.ok("Appointment deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete_appointment_by_doctor/{appointment_id}")
    public ResponseEntity<String> deleteAppointmentByDoctor(@PathVariable Long appointment_id, @RequestParam Long doctor_id) {
        try{
            appointmentService.deleteAppointmentByDoctor(appointment_id, doctor_id);
            return ResponseEntity.ok(">>> deleted appointment");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PutMapping("/update_appointment_by_doctor")
    public ResponseEntity<String> updateAppointmentByDoctor(@RequestBody AppointmentDto appointmentDto, @RequestParam Long doctor_id) {
        try{
            appointmentService.updateAppointmentByDoctor(appointmentDto, doctor_id);
            return ResponseEntity.ok(">>> updated appointment by doctor");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PutMapping("/update_appointment_by_patient")
    public ResponseEntity<String> updateAppointmentByPatient(@RequestBody AppointmentDto appointmentDto, @RequestParam Long patient_id) {
        try{
            appointmentService.updateAppointmentByPatient(appointmentDto, patient_id);
            return ResponseEntity.ok(">>> updated appointment by patient");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


    //    @GetMapping("/appointments")
//    @GetMapping("/appointments_per_doctor/{doctor_id}")
//    @GetMapping("/appointments_per_patient/{patient_id}")

//    @PostMapping("/create_appointment") doctor_id + pat_id

//    @PutMapping("/update_appointment_by_patient/{appointment_id}") patient_id

//

}
