package com.example.project.service;

import com.example.project.dtos.AppointmentDto;
import com.example.project.entity.Appointment;
import com.example.project.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;


    @Override
    public void saveAppointment(AppointmentDto appointmentDto) {

    }

    @Override
    public AppointmentDto findAppointmentById(Long id) {
        return null;
    }

    @Override
    public boolean deleteAppointmentByPatient(Long appointmentId, Long patientId) {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElseThrow();

        if(appointment.getPatient().getId().equals(patientId)) {
            appointmentRepo.delete(appointment);
            return true;
        }
        throw new RuntimeException("couldnt properly delete appointment based on patient_id");
    }

    @Override
    public boolean deleteAppointmentByDoctor(Long appointmentId, Long doctorId) {

    }

    @Override
    public void updateAppointmentByPatient(Long appointmentId, Long patientId) {

    }

    @Override
    public void updateAppointmentByDoctor(Long appointmentId, Long doctorId) {

    }


    @Override
    public List<AppointmentDto> findAllAppointmentsByPatientId(Long patientId) {
        return List.of();
    }

    @Override
    public List<AppointmentDto> findAllAppointmentsByDoctorId(Long doctorId) {
        return List.of();
    }

    @Override
    public List<AppointmentDto> findAllAppointments() {
        return List.of();
    }
}
