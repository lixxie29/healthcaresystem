package java.com.example.project.service;

import com.example.project.dtos.DoctorDto;
import com.example.project.entity.Doctor;
import com.example.project.entity.DoctorSpecialty;
import com.example.project.repo.DoctorRepo;
import com.example.project.repo.DoctorSpecialtyRepo;
import com.example.project.service.DoctorServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

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

    @Test
    @Order(4)
    @DisplayName("Service Test 4: Update Doctor")
    public void updateDoctorTest() {
        // Arrange: Create a DoctorSpecialty
        DoctorSpecialty specialty = new DoctorSpecialty();
        specialty.setId(1L);
        specialty.setName("Oncology");

        // Create a DoctorDto for the doctor to update
        DoctorDto doctorDtoToUpdate = DoctorDto.builder()
                .id(3L)
                .firstName("Clint")
                .lastName("Johnson")
                .age(35)
                .gender("male")
                .hospitalName("Harborview Medical")
                .specialtyId(1L)  // Ensure this matches the mocked specialty ID
                .email("jclint@harborview.com")
                .password("clint123")
                .build();

        // Mock the specialty repo to return the existing specialty
        given(doctorSpecialtyRepo.findById(1L)).willReturn(Optional.of(specialty));

        // Mock the behavior of finding a doctor by email
        given(doctorRepo.findByEmail("jclint@harborview.com")).willReturn(null); // No existing doctor

        // Mock the behavior of saving a doctor during the initial save
        given(doctorRepo.save(any(Doctor.class))).willAnswer(invocation -> {
            Doctor savedDoctor = invocation.getArgument(0);
            savedDoctor.setId(3L); // Simulate that the doctor now has an ID after saving
            return savedDoctor;
        });

        // Act: Save the initial doctor
        doctorService.saveDoctor(doctorDtoToUpdate); // This ensures the doctor exists

        // Prepare the doctor entity for update
        Doctor doctorToUpdate = Doctor.builder()
                .id(3L)
                .firstName("Clint")
                .lastName("Johnson")
                .age(35)
                .gender("male")
                .hospitalName("Harborview Medical")
                .email("jclint@harborview.com")
                .specialty(specialty)
                .password("encoded_clint123")
                .build();

        // Mock existsById to return true for the doctor's ID
        given(doctorRepo.existsById(3L)).willReturn(true);

        // Mock findById to return the saved doctor
        given(doctorRepo.findById(3L)).willReturn(Optional.of(doctorToUpdate));

        // Update the doctor's information in the DTO
        doctorDtoToUpdate.setEmail("max@gmail.com");
        doctorDtoToUpdate.setFirstName("Max");

        // Act: Update the doctor
        doctorService.updateDoctor(3L, doctorDtoToUpdate);

        // Verify that save was called with the updated doctor
        ArgumentCaptor<Doctor> doctorCaptor = ArgumentCaptor.forClass(Doctor.class);
        verify(doctorRepo, times(2)).save(doctorCaptor.capture()); // save called during save and update

        // Get the updated doctor from the captor
        Doctor updatedDoctor = doctorCaptor.getValue();

        // Assert: Verify that the update was successful
        assertThat(updatedDoctor.getEmail()).isEqualTo("max@gmail.com");
        assertThat(updatedDoctor.getFirstName()).isEqualTo("Max");
    }

    @Test
    @Order(5)
    @DisplayName("Service Test 5: Delete Doctor")
    public void deleteDoctorTest() {
        given(doctorRepo.existsById(doctor.getId())).willReturn(true);
        willDoNothing().given(doctorRepo).deleteById(doctor.getId());
        doctorService.deleteDoctor(doctorDto.getId());
        verify(doctorRepo, times(1)).deleteById(doctor.getId());
    }


}
