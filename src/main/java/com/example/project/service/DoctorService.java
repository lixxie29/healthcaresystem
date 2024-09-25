package com.example.project.service;

import com.example.project.dtos.DoctorDto;
import com.example.project.entity.Doctor;

import java.util.List;

public interface DoctorService {
    void saveDoctor(DoctorDto doctorDto);
    Doctor findDoctorByEmail(String email);
    List<DoctorDto> findAllDoctors();
    List<Doctor> findDoctorsAll();
}
