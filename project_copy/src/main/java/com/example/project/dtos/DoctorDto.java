package com.example.project.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private int age;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String hospitalName;

    @NotEmpty
    private int speciality;

    @NotEmpty
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;
}
