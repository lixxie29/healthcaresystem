package com.example.project.controller;

import com.example.project.dtos.DoctorDto;
import com.example.project.entity.Doctor;
import com.example.project.entity.DoctorSpecialty;
import com.example.project.repo.DoctorSpecialtyRepo;
import com.example.project.service.DoctorService;
import com.example.project.service.DoctorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;


import static org.mockito.BDDMockito.given;

@WebMvcTest(DoctorController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Autowired
    private ObjectMapper objectMapper;

    Doctor doctor;
    DoctorDto doctorDto;

    @MockBean
    private DoctorSpecialtyRepo doctorSpecialtyRepo;

    @BeforeEach
    public void setUp() {
//        Long specialtyId = 3L;
//        DoctorSpecialty specialty = doctorSpecialtyRepo.findById(specialtyId).orElseThrow(() -> new EntityNotFoundException(" >>> specialty not found"));

        DoctorSpecialty specialty = new DoctorSpecialty();
        specialty.setId(3L);
        specialty.setName("Cardiology");
        given(doctorSpecialtyRepo.findById(3L)).willReturn(Optional.of(specialty));

        doctor = Doctor.builder()
                .firstName("Linda")
                .lastName("Morris")
                .age(32)
                .gender("female")
                .hospitalName("Undeva Medical Center")
                .specialty(specialty)
                .email("linda_m@undevamed.com")
                .password("linda_morris88")
                .build();

        doctorDto = DoctorDto.builder()
                .firstName("Linda")
                .lastName("Morris")
                .age(32)
                .gender("female")
                .hospitalName("Undeva Medical Center")
                .specialtyId(3L) // Reference the specialty by ID
                .email("linda_m@undevamed.com")
                .password("linda_morris88")
                .build();
    }

    //post controller
    @Test
    @Order(1)
    @DisplayName("Controller Test 1: Save Doctor")
    public void saveDoctorTest() throws Exception {

//      given(doctorService.saveDoctor(any(DoctorDto.class))).willReturn(doctor);

        doNothing().when(doctorService).saveDoctor(any(DoctorDto.class));

        String doctorDtoJson = objectMapper.writeValueAsString(doctorDto);
        mockMvc.perform(post("/register_doctor")
        .contentType(MediaType.APPLICATION_JSON)
                .content(doctorDtoJson))
                .andExpect(status().isOk())
                .andDo(print());

        verify(doctorService, times(1)).saveDoctor(any(DoctorDto.class));

    }
}
