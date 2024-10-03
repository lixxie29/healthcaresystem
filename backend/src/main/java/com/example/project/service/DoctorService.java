package com.example.project.service;

import com.example.project.dtos.DoctorDto;

import java.util.List;

public interface DoctorService {
    void saveDoctor(DoctorDto doctorDto);
    DoctorDto findDoctorByEmail(String email);
    DoctorDto findDoctorById(Long id);
    List<DoctorDto> findAllDoctors();
    boolean authenticateDoctor(String email, String password);
    boolean deleteDoctor(Long id);
    boolean updateDoctor(Long id, DoctorDto doctorDto);
}
