package com.example.project.controller;

//import ch.qos.logback.core.model.Model;
import com.example.project.repo.DoctorRepo;
import com.example.project.service.PatientService;
import org.springframework.ui.Model;
import com.example.project.dtos.DoctorDto;
import com.example.project.dtos.PatientDto;
import com.example.project.entity.Doctor;
import com.example.project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientService patientService;

    public AuthController(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors() {
        return doctorService.findAllDoctors();
    }

    @GetMapping("/alldoctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }



    @PostMapping("/register_doctor")
    public String saveDoctor(@RequestBody DoctorDto doctorDto) {
        doctorService.saveDoctor(doctorDto);
        return "saved doctor";
    }



    //    @GetMapping("/index")
//    public String home(){return "index";}


//    @GetMapping("/doctors")
//    public List<DoctorDto> doctors(){return doctorService.findAllDoctors();}

//    @GetMapping("/register_doctor")
//    public String showDoctorRegistrationForm(Model model){
//        DoctorDto doctor = new DoctorDto();
//        model.addAttribute("doctor", doctor);
//        return "register_doctor";
//    }
//    @GetMapping("/register_patient")
//    public String showPatientRegistrationForm(Model model){
//        PatientDto patient = new PatientDto();
//        model.addAttribute("patient", patient);
//        return "register_patient";
//    }
}
