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
import org.springframework.http.ResponseEntity;
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
import static org.mockito.Mockito.doNothing;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .id(5L)
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

    // post controller
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

    // get controller
    @Test
    @Order(2)
    @DisplayName("Controller Test 2: Get DoctorList")
    public void getDoctorTest() throws Exception {
        List<DoctorDto> doctorDtoList = new ArrayList<>();
        doctorDtoList.add(doctorDto);
        doctorDtoList.add(DoctorDto.builder()
                .id(3L)
                .firstName("Ioana")
                .lastName("Pop")
                .age(30)
                .gender("female")
                .hospitalName("ABC Medical Center")
                .specialtyId(3L)
                .email("ioana_p@abcmed.com")
                .password("ioana123")
                .build());
        given(doctorService.findAllDoctors()).willReturn(doctorDtoList);
        ResultActions response = mockMvc.perform(get("/doctors"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(doctorDtoList.size())));
    }

    // get by email controller

    @Test
    @Order(3)
    @DisplayName("Controller Test 3: Get Doctor By Email")
    public void getDoctorByEmailTest() throws Exception {
        String email = "linda_m@undevamed.com";
        given(doctorService.findDoctorByEmail(email)).willReturn(doctorDto); // Mocking the service call

        ResultActions response = mockMvc.perform(get("/find_doctor_email/" + email));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName",is(doctorDto.getFirstName())))
                .andExpect(jsonPath("$.lastName",is(doctorDto.getLastName())))
                .andExpect(jsonPath("$.age",is(doctorDto.getAge())))
                .andExpect(jsonPath("$.gender",is(doctorDto.getGender())))
                .andExpect(jsonPath("$.hospitalName",is(doctorDto.getHospitalName())));

    }


    @Test
    @Order(4)
    @DisplayName("Controller Test 4: Update Doctor By Id")
    public void updateDoctorByIdTest() throws Exception {
        Long doctorId = 1L;
        DoctorDto updatedDoctorDto = DoctorDto.builder()
                        .firstName("Linda Updated")
                        .lastName("Morris Updated")
                        .age(32)
                        .gender("female")
                        .hospitalName("Updated Medical Center")
                        .specialtyId(3L)
                        .email("updated_linda_m@undevamed.com")
                        .password("linda123")
                        .build();

        given(doctorService.updateDoctor(doctorId, updatedDoctorDto)).willReturn(true);

        ResultActions response = mockMvc.perform(put("/update_doctor/" + doctorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDoctorDto)));

        response.andExpect(status().isOk())
                .andExpect(content().string(">>> updated doctor"));
    }

    // delete doctor
    @Test
    @Order(5)
    @DisplayName("Controller Test 5: Delete Doctor By Id")
    public void deleteDoctorByIdTest() throws Exception {
        Long doctorId = 1L; // Hardcoded ID for the doctor you want to delete

        // Mock the service to do nothing when deleteDoctor is called
        willReturn(true).given(doctorService).deleteDoctor(doctorId);

        // Perform the delete action
        ResultActions response = mockMvc.perform(delete("/delete_doctor/{doctor_id}", doctorId));

        // Verify the response status is OK (200)
        response.andExpect(status().isOk())
                .andExpect(content().string(">>> deleted doctor")) // Check the response body
                .andDo(print());
    }

}
