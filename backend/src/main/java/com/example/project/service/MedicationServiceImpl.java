package com.example.project.service;

import com.example.project.dtos.MedicationDto;
import com.example.project.entity.Medication;
import com.example.project.entity.Prescription;
import com.example.project.repo.MedicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {
    @Autowired
    private MedicationRepo medicationRepo;

    @Override
    public Medication findMedById(Long id) {
        Medication medication = medicationRepo.findById(id).orElseThrow(() -> new RuntimeException(">>> medication not found"));
        return medication;
    }

    @Override
    public List<MedicationDto> findAllMedications() {
        List<Medication> medications = medicationRepo.findAll();

        return medications.stream()
                .map(this::mapToMedicationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicationDto> findAllMedsByPrescriptionId(Long prescriptionId) {
        List<Medication> medications = medicationRepo.findAllByPrescriptionsId(prescriptionId);

        return medications.stream()
                .map(this::mapToMedicationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Medication> findAllMedsByMedicationId(List<Long> medicationId) {
        return medicationRepo.findAllById(medicationId);
    }

    private MedicationDto mapToMedicationDto(Medication medication) {
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setId(medication.getId());
        medicationDto.setName(medication.getName());
        medicationDto.setType(medication.getType());
        medicationDto.setDescription(medication.getDescription());
        medicationDto.setDosage(medication.getDosage());
        medicationDto.setPrescriptionIds(medication.getPrescriptions().stream().map(Prescription::getId).collect(Collectors.toList()));
        return medicationDto;
    }
}
