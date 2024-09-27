package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCreated;
    private Period spanToBeConsumed;
    private boolean valid;

    @ManyToMany
    List<Medication> medications;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    public void setSpanToBeConsumed(int months){
        this.spanToBeConsumed = Period.ofMonths(months);
    }

    public void setValid() {

        LocalDate today = LocalDate.now();
        LocalDate expirationDate;
        expirationDate = dateCreated.plusMonths(spanToBeConsumed.getMonths());

        if(today.isAfter(expirationDate)) {this.valid = false;}
        else
            {this.valid = true;}
    }
}

