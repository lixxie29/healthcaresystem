package com.example.project.dtos;

import com.example.project.entity.Doctor;
import com.example.project.entity.Patient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Long id;
    @NotEmpty
    private String type;
    @NotEmpty
    private Long doctorId;
    @NotEmpty
    private Date date;
    @NotEmpty
    private Long patientId;
    @NotEmpty
    private String hospitalName;
}
