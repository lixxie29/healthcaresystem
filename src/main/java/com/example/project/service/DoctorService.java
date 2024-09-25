package com.example.project.service;

import com.example.project.dtos.DoctorDto;

import java.util.List;

public interface DoctorService {
    void saveDoctor(DoctorDto doctorDto);
    DoctorDto findDoctorByEmail(String email);
    List<DoctorDto> findAllDoctors();
}
