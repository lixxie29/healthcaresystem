package com.example.project.dtos;

import com.example.project.entity.Patient;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDto {
    private Long id;
    @NotEmpty
    private LocalDate dateCreated;
    @NotEmpty
    private Period spanToBeConsumed;
    @NotEmpty
    private boolean valid;
    @NotEmpty
    private List<Long> medicationIds;
    @NotEmpty
    private Long doctorId;
    @NotEmpty
    private Long patientId;
}
