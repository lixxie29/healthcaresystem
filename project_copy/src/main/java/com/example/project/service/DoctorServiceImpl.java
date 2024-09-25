package com.example.project.service;

import com.example.project.dtos.DoctorDto;
import com.example.project.entity.Doctor;
import com.example.project.entity.DoctorSpecialty;
import com.example.project.repo.DoctorRepo;
import com.example.project.repo.DoctorSpecialtyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService{
//    @Autowired
//    private final DoctorRepo doctorRepo;
//    @Autowired
//    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    public DoctorServiceImpl(DoctorRepo doctorRepo, BCryptPasswordEncoder encoder) {
//        this.doctorRepo = doctorRepo;
//        this.encoder = encoder;
//    }

    private final DoctorRepo doctorRepo;
    private final PasswordEncoder encoder;

    @Autowired
    private DoctorSpecialtyService specialtyService;

    // Use constructor injection for both dependencies
    public DoctorServiceImpl(DoctorRepo doctorRepo, PasswordEncoder encoder, DoctorSpecialtyService specialtyService) {
        this.doctorRepo = doctorRepo;
        this.encoder = encoder;
        this.specialtyService = specialtyService;
    }


    @Override
    public void saveDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setAge(doctorDto.getAge());
        doctor.setGender(doctorDto.getGender());
        doctor.setHospitalName(doctorDto.getHospitalName());
        //doctor.setSpecialty(doctorDto.getSpeciality());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPassword(encoder.encode(doctorDto.getPassword()));
        doctorRepo.save(doctor);
    }

    @Override
    public Doctor findDoctorByEmail(String email) {
        return doctorRepo.findByEmail(email);
    }

    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors = doctorRepo.findAll();
        return doctors.stream()
                .map((Doctor) -> mapToDoctorDto(Doctor))
                .collect(Collectors.toList());
//        return doctors;
    }

    @Override
    public List<Doctor> findDoctorsAll(){
        return doctorRepo.findAll();
    }

    private DoctorDto mapToDoctorDto(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setFirstName(doctor.getFirstName());
        doctorDto.setLastName(doctor.getLastName());
        doctorDto.setAge(doctor.getAge());
        doctorDto.setGender(doctor.getGender());
        doctorDto.setHospitalName(doctor.getHospitalName());
        doctorDto.setEmail(doctor.getEmail());
        doctorDto.setPassword(encoder.encode(doctor.getPassword()));
        return doctorDto;
    }
}
