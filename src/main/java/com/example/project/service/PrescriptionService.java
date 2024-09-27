package com.example.project.service;

import com.example.project.dtos.PrescriptionDto;

import java.util.List;

public interface PrescriptionService {
    void savePrescription(PrescriptionDto prescriptionDto);
    PrescriptionDto findPrescriptionById(Long id);

    boolean deletePrescriptionByDoctorId(Long prescriptionId, Long doctorId);
    boolean updatePrescription(PrescriptionDto prescriptionDto, Long doctorId);

    List<PrescriptionDto> findAllPrescriptions();
    List<PrescriptionDto> findAllPrescriptionsByDoctorId(Long doctorId);
    List<PrescriptionDto> findAllPrescriptionsByPatientId(Long patientId);

}
