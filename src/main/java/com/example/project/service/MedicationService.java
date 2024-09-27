package com.example.project.service;

import com.example.project.dtos.MedicationDto;
import com.example.project.entity.Medication;

import java.util.List;

public interface MedicationService {
    Medication findMedById(Long id);

    List<MedicationDto> findAllMedications();
    List<MedicationDto> findAllMedsByPrescriptionId(Long prescriptionId);

    List<Medication> findAllMedsByMedicationId(List<Long> medicationId);
}


//2024-09-27T17:25:27.980+03:00  WARN 24304 --- [project_copy] [nio-8080-exec-4]
// .w.s.m.s.DefaultHandlerExceptionResolver :
// Resolved [org.springframework.http.converter.HttpMessageNotReadableException:
// JSON parse error: Cannot deserialize value of type `java.time.Period` from
// Object value (token `JsonToken.START_OBJECT`)]