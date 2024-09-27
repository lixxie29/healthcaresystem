package com.example.project.controller;

import com.example.project.dtos.AppointmentDto;
import com.example.project.dtos.PrescriptionDto;
import com.example.project.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/prescriptions")
    public List<PrescriptionDto> getAllPrescriptions() {
        return prescriptionService.findAllPrescriptions();
    }

    @PostMapping("/create_prescription")
    public String createPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        prescriptionService.savePrescription(prescriptionDto);
        return ">>> created prescription";
    }

    @GetMapping("/find_prescription_by_id/{id}")
    public ResponseEntity<PrescriptionDto> findPrescriptionById(@PathVariable Long id) {
        PrescriptionDto prescriptionDto = prescriptionService.findPrescriptionById(id);
        return new ResponseEntity<>(prescriptionDto, HttpStatus.OK);
    }

    @GetMapping("/prescriptions_per_doctor/{doctor_id}")
    public ResponseEntity<String> findPrescriptionsByDoctorId(@PathVariable Long doctor_id) {
        try{
            prescriptionService.findAllPrescriptionsByDoctorId(doctor_id);
            return ResponseEntity.ok(">>> all prescriptions with given doctor_id found");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/prescriptions_per_patient/{patient_id}")
    public ResponseEntity<String> findPrescriptionsByPatientId(@PathVariable Long patient_id) {
        try{
            prescriptionService.findAllPrescriptionsByPatientId(patient_id);
            return ResponseEntity.ok(">>> all prescriptions with given doctor_id found");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete_prescription_by_doctor/{prescription_id}")
    public ResponseEntity<String> deletePrescriptionByDoctor(@PathVariable Long prescription_id, @RequestParam Long doctor_id) {
        try{
            prescriptionService.deletePrescription(prescription_id, doctor_id);
            return ResponseEntity.ok(">>> deleted prescription by doctor");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PutMapping("/update_prescription_by_doctor")
    public ResponseEntity<String> updatePrescriptionByDoctor(@RequestBody PrescriptionDto prescriptionDto, @RequestParam Long doctor_id) {
        try{
            prescriptionService.updatePrescription(prescriptionDto, doctor_id);
            return ResponseEntity.ok(">>> updated appointment by doctor");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


}
