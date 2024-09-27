package com.example.project.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String type;
    @NotEmpty
    private String description;
    @NotEmpty
    private String dosage;
    @NotEmpty
    private List<Long> prescriptionIds;
}
