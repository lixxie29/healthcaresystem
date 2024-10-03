package com.example.project.service;

import com.example.project.controller.PrescriptionController;
import com.example.project.dtos.PrescriptionDto;
import com.example.project.entity.*;
import com.example.project.repo.DoctorRepo;
import com.example.project.repo.PatientRepo;
import com.example.project.repo.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private PrescriptionRepo prescriptionRepo;
    @Autowired
    private MedicationService medicationService;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientRepo patientRepo;


    @Override
    public PrescriptionDto findPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepo.findById(id).orElseThrow(() -> new RuntimeException(">>> prescription not found"));
        return this.mapToPrescriptionDto(prescription);
    }

    @Override
    public void savePrescription(PrescriptionDto prescriptionDto) {
        Prescription prescription = new Prescription();
        prescription.setId(prescriptionDto.getId());
        prescription.setDateCreated(prescriptionDto.getDateCreated());
        prescription.setSpanToBeConsumed(prescriptionDto.getSpanToBeConsumed());
        prescription.setValid(prescriptionDto.isValid());
        prescription.setMedications(medicationService.findAllMedsByMedicationId(prescriptionDto.getMedicationIds()));

        Doctor doctor = doctorRepo.findById(prescriptionDto.getDoctorId()).orElseThrow(() -> new RuntimeException(">>> doctor not found"));
        prescription.setDoctor(doctor);

        Patient patient = patientRepo.findById(prescriptionDto.getPatientId()).orElseThrow(() -> new RuntimeException(">>> patient not found"));
        prescription.setPatient(patient);
        prescriptionRepo.save(prescription);
    }

    @Override
    public boolean updatePrescription(PrescriptionDto prescriptionDto, Long doctorId) {
        if(prescriptionDto.getDoctorId().equals(doctorId)){
            Prescription prescription = prescriptionRepo.findById(prescriptionDto.getId()).orElseThrow(() -> new RuntimeException(">>> prescription not found"));
            prescription.setId(prescriptionDto.getId());
            prescription.setDateCreated(prescriptionDto.getDateCreated());
            prescription.setValid(prescriptionDto.isValid());
            prescription.setMedications(medicationService.findAllMedsByMedicationId(prescriptionDto.getMedicationIds()));

            Doctor doctor = doctorRepo.findById(prescriptionDto.getDoctorId()).orElseThrow(() -> new RuntimeException(">>> doctor not found"));
            prescription.setDoctor(doctor);

            Patient patient = patientRepo.findById(prescriptionDto.getPatientId()).orElseThrow(() -> new RuntimeException(">>> patient not found"));
            prescription.setPatient(patient);

            prescriptionRepo.save(prescription);
            return true;
        }
        throw new RuntimeException(">>> could not properly update prescription based on doctor_id");
    }

    @Override
    public boolean deletePrescription(Long prescriptionId, Long doctorId) {
        Prescription prescription = prescriptionRepo.findById(prescriptionId).orElseThrow();

        if(prescription.getDoctor().getId().equals(doctorId)){
            prescriptionRepo.delete(prescription);
            return true;
        }
        throw new RuntimeException(">>> could not properly delete prescription based on doctor_id");
    }

    @Override
    public List<PrescriptionDto> findAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepo.findAll();

        return prescriptions.stream()
                .map(this::mapToPrescriptionDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDto> findAllPrescriptionsByDoctorId(Long doctorId) {
        List<Prescription> prescriptions = prescriptionRepo.findAllByDoctorId(doctorId);

        return prescriptions.stream()
                .map(this::mapToPrescriptionDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDto> findAllPrescriptionsByPatientId(Long patientId) {
        List<Prescription> prescriptions = prescriptionRepo.findAllByPatientId(patientId);

        return prescriptions.stream()
                .map(this::mapToPrescriptionDto)
                .collect(Collectors.toList());
    }

    private PrescriptionDto mapToPrescriptionDto(Prescription prescription) {
        PrescriptionDto prescriptionDto = new PrescriptionDto();
        prescriptionDto.setId(prescription.getId());
        prescriptionDto.setDateCreated(prescription.getDateCreated());

        prescriptionDto.setSpanToBeConsumed(prescription.getSpanToBeConsumed().getMonths());

        prescriptionDto.setValid(prescription.isValid());
        prescriptionDto.setMedicationIds(prescription.getMedications().stream().map(Medication::getId).collect(Collectors.toList()));
        prescriptionDto.setPatientId(prescription.getPatient().getId());
        prescriptionDto.setDoctorId(prescription.getDoctor().getId());
        return prescriptionDto;
    }
}
