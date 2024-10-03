package com.example.project.service;

import com.example.project.entity.DoctorSpecialty;
import com.example.project.repo.DoctorSpecialtyRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DoctorSpecialtyServiceImpl implements DoctorSpecialtyService {
    private final DoctorSpecialtyRepo doctorSpecialtyRepo;

    public DoctorSpecialtyServiceImpl(DoctorSpecialtyRepo doctorSpecialtyRepo) {
        this.doctorSpecialtyRepo = doctorSpecialtyRepo;
    }

    @Override
    public DoctorSpecialty findSpecialtyById(Long id) {
        return doctorSpecialtyRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(" >>> doctor specialty not found with id: " + id));
    }
}
