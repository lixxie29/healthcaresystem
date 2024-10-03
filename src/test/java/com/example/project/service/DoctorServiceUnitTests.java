package com.example.project.service;

import com.example.project.dtos.DoctorDto;
import com.example.project.entity.Doctor;
import com.example.project.entity.DoctorSpecialty;
import com.example.project.repo.DoctorRepo;
import com.example.project.repo.DoctorSpecialtyRepo;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceUnitTests {

    @Mock
    private DoctorRepo doctorRepo;

    @Mock
    private DoctorSpecialtyRepo doctorSpecialtyRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks // Injects the real service with mocks
    private DoctorServiceImpl doctorService;

    private Doctor doctor;
    private DoctorDto doctorDto;

    @BeforeEach
    public void setup() {
        DoctorSpecialty specialty = new DoctorSpecialty();
        specialty.setId(4L);
        specialty.setName("Pediatrics");

        doctor = Doctor.builder()
                .id(2L)
                .firstName("Clara")
                .lastName("Johnson")
                .age(30)
                .gender("female")
                .hospitalName("Harborview Medical")
                .email("jclara@harborview.com")
                .specialty(specialty)
                .password("clara123")
                .build();

        doctorDto = DoctorDto.builder()
                .id(2L)
                .firstName("Clara")
                .lastName("Johnson")
                .age(30)
                .gender("female")
                .hospitalName("Harborview Medical")
                .specialtyId(4L)
                .email("jclara@harborview.com")
                .password("clara123")
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Service Test 1: Save Doctor")
    public void saveDoctorTest() {
        // Mock the behavior for the save method
        given(doctorRepo.findByEmail(doctorDto.getEmail())).willReturn(null); // No existing doctor
        given(doctorSpecialtyRepo.findById(doctorDto.getSpecialtyId())).willReturn(Optional.of(doctor.getSpecialty()));
        // Act
        doctorService.saveDoctor(doctorDto); // Calls the service method

        // Assert
        assertThat(doctorDto).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("Service Test 2: Get Doctor By Id")
    public void getDoctorByIdTest() {
        // Mock the behavior for finding a doctor by ID
        given(doctorRepo.findById(2L)).willReturn(Optional.of(doctor));

        // Act
        DoctorDto existingDoctor = doctorService.findDoctorById(doctorDto.getId());

        // Assert
        assertThat(existingDoctor).isNotNull();
        assertThat(existingDoctor.getId()).isEqualTo(2L);
        assertThat(existingDoctor.getFirstName()).isEqualTo("Clara");
        assertThat(existingDoctor.getLastName()).isEqualTo("Johnson");
        assertThat(existingDoctor.getEmail()).isEqualTo("jclara@harborview.com");
    }

    @Test
    @Order(3)
    @DisplayName("Service Test 3: Get All Doctors")
    public void getAllDoctorsTest() {
        DoctorSpecialty specialty = new DoctorSpecialty();
        specialty.setId(3L);
        specialty.setName("Neurology");

        Doctor doctor1 = Doctor.builder()
                .id(3L)
                .firstName("Crina")
                .lastName("Sas")
                .age(21)
                .gender("female")
                .hospitalName("Medlife")
                .email("scrina@medlife.com")
                .specialty(specialty)
                .password("crina123")
                .build();

        given(doctorRepo.findAll()).willReturn(List.of(doctor, doctor1));

        List<DoctorDto> doctorList = doctorService.findAllDoctors();
        assertThat(doctorList).isNotNull();
        assertThat(doctorList.size()).isGreaterThan(1);
    }

}
