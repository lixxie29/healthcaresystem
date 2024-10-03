package com.example.project.service;

import com.example.project.dtos.DoctorDto;
import com.example.project.entity.Doctor;
import com.example.project.entity.DoctorSpecialty;
import com.example.project.repo.DoctorRepo;
import com.example.project.repo.DoctorSpecialtyRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepo doctorRepo;
    private final PasswordEncoder encoder;
    private final DoctorSpecialtyService specialtyService;
    private final DoctorSpecialtyRepo specialtyRepo;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo,
                             PasswordEncoder encoder,
                             DoctorSpecialtyRepo specialtyRepo,
                             DoctorSpecialtyService specialtyService) {
        this.doctorRepo = doctorRepo;
        this.encoder = encoder;
        this.specialtyRepo = specialtyRepo;
        this.specialtyService = specialtyService;
    }


    @Override
    public void saveDoctor(DoctorDto doctorDto) {
        if (doctorRepo.findByEmail(doctorDto.getEmail()) != null) {
            throw new EntityExistsException(" >>> doctor with this email already exists");
        }

        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setAge(doctorDto.getAge());
        doctor.setGender(doctorDto.getGender());
        doctor.setHospitalName(doctorDto.getHospitalName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPassword(encoder.encode(doctorDto.getPassword()));

        DoctorSpecialty specialty = specialtyRepo.findById(doctorDto.getSpecialtyId()).<EntityNotFoundException>orElseThrow(() -> new EntityNotFoundException(" >>> specialty not found"));
        doctor.setSpecialty(specialty);
        doctorRepo.save(doctor);
    }

    public DoctorDto saveDoc(DoctorDto doctorDto) {
        saveDoctor(doctorDto);
        return doctorDto;
    }

    @Override
    public DoctorDto findDoctorByEmail(String email) {
        Doctor doctor = doctorRepo.findByEmail(email);
        return this.mapToDoctorDto(doctor);
    }

    @Override
    public DoctorDto findDoctorById(Long id) {
        Optional<Doctor> doctor = doctorRepo.findById(id);
        return this.mapToDoctorDto(doctor.orElse(null));
    }

    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors = doctorRepo.findAll();
        return doctors.stream()
                .map(this::mapToDoctorDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean authenticateDoctor(String email, String password) {
        Doctor doctor = doctorRepo.findByEmail(email);
        return doctor!=null && encoder.matches(password, doctor.getPassword());
    }

    @Override
    public boolean deleteDoctor(Long id) {
        if(doctorRepo.existsById(id)) {
            doctorRepo.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException(" >>> could not properly delete doctor, doctor not found");
    }

    @Override
    public boolean updateDoctor(Long doctorId, DoctorDto doctorDto) {
        if(doctorRepo.existsById(doctorId)) {
            Doctor doctor = doctorRepo.findById(doctorId).orElseThrow(() -> new EntityNotFoundException(" >>> Doctor not found for ID: " + doctorId));
            doctor.setFirstName(doctorDto.getFirstName());
            doctor.setLastName(doctorDto.getLastName());
            doctor.setAge(doctorDto.getAge());
            doctor.setGender(doctorDto.getGender());
            doctor.setHospitalName(doctorDto.getHospitalName());
            doctor.setEmail(doctorDto.getEmail());
            doctor.setPassword(encoder.encode(doctorDto.getPassword()));

            DoctorSpecialty specialty = specialtyRepo.findById(doctorDto.getSpecialtyId()).orElseThrow(() -> new EntityNotFoundException(" >>> specialty not found"));
            doctor.setSpecialty(specialty);
            doctorRepo.save(doctor);
            return true;
        }
        throw new EntityNotFoundException(" >>> could not properly update doctor based on doctor_id");
    }

    private DoctorDto mapToDoctorDto(Doctor doctor){

        if (doctor == null) {
            System.out.println("Doctor object is null in mapToDoctorDto");
            return null;
        }

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setFirstName(doctor.getFirstName());
        doctorDto.setLastName(doctor.getLastName());
        doctorDto.setAge(doctor.getAge());
        doctorDto.setGender(doctor.getGender());
        doctorDto.setHospitalName(doctor.getHospitalName());
        doctorDto.setEmail(doctor.getEmail());
        doctorDto.setPassword(doctor.getPassword());
        doctorDto.setSpecialtyId(doctor.getSpecialty().getId());
        return doctorDto;
    }

    private Doctor mapToDoctorEntity(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setAge(doctorDto.getAge());
        doctor.setGender(doctorDto.getGender());
        doctor.setHospitalName(doctorDto.getHospitalName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPassword(encoder.encode(doctorDto.getPassword()));

        DoctorSpecialty specialty = specialtyRepo.findById(doctorDto.getSpecialtyId())
                .orElseThrow(() -> new EntityNotFoundException(" >>> specialty not found"));
        doctor.setSpecialty(specialty);

        return doctor;
    }
}
