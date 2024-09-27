package com.example.project.controller;

import com.example.project.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    //    @GetMapping("/appointments")
//    @GetMapping("/appointments_per_doctor/{doctor_id}")
//    @GetMapping("/appointments_per_patient/{patient_id}")

//    @PostMapping("/create_appointment") doctor_id + pat_id

//    @PutMapping("/update_appointment_by_doctor/{appointment_id}") doctor_id
//    @PutMapping("/update_appointment_by_patient/{appointment_id}") patient_id

//
//    @DeleteMapping("/delete_appointment_by_doctor/{appointment_id}")
}
