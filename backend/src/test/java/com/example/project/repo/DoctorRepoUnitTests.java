package com.example.project.repo;

import com.example.project.entity.Doctor;
import com.example.project.entity.DoctorSpecialty;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorRepoUnitTests {

    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private DoctorSpecialtyRepo doctorSpecialtyRepo;

    @Test
    @DisplayName("Test 1: Save Doctor")
    @Order(1)
    @Rollback(false)
    public void saveDoctorTest(){

        Long specialtyId = 5L;
        DoctorSpecialty specialty = doctorSpecialtyRepo.findById(specialtyId).orElseThrow(() -> new EntityNotFoundException(" >>> specialty not found"));

        Doctor doctor = Doctor.builder()
                .firstName("Emily")
                .lastName("Johnson")
                .age(35)
                .gender("female")
                .hospitalName("Harborview Medical")
                .email("emily.johnson@harborview.com")
                .specialty(specialty)
                .password("emily123")
                .build();

        doctorRepo.save(doctor);

        System.out.println(doctor);
        Assertions.assertThat(doctor.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test 2: Display Doctor")
    @Order(2)
    public void displayDoctorTest(){
        Doctor doctor = doctorRepo.findById(1L).orElseThrow(() -> new EntityNotFoundException(" >>> doctor not found"));
        System.out.println(doctor);
        Assertions.assertThat(doctor.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Test 3: Display list of all Doctors")
    @Order(3)
    public void getListofDoctorsTest(){
        List<Doctor> doctors = doctorRepo.findAll();
        System.out.println(doctors);
        Assertions.assertThat(doctors.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test 4: Update Doctor")
    @Order(4)
    @Rollback(value = false)
    public void updateDoctorTest(){
        Doctor doctor = doctorRepo.findById(1L).orElseThrow(() -> new EntityNotFoundException(" >>> doctor not found"));
        doctor.setFirstName("Alexandra");
        Doctor doctorUpdated = doctorRepo.save(doctor);

        System.out.println(doctorUpdated);
        Assertions.assertThat(doctorUpdated.getFirstName()).isEqualTo("Alexandra");
    }

    @Test
    @DisplayName("Test 5: Delete Doctor")
    @Order(5)
    @Rollback(value = false)
    public void deleteDoctorTest(){
        doctorRepo.deleteById(2L);
        Optional<Doctor> doctor = doctorRepo.findById(2L);

        Assertions.assertThat(doctor).isEmpty();
    }


}
