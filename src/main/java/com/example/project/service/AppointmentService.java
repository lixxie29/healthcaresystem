package com.example.project.service;

import com.example.project.dtos.AppointmentDto;
import com.example.project.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    void saveAppointment(AppointmentDto appointmentDto);
    AppointmentDto findAppointmentById(Long id);

    boolean deleteAppointmentByPatient(Long appointmentId,Long patientId);
    boolean deleteAppointmentByDoctor(Long appointmentId, Long doctorId);

    void updateAppointmentByPatient(Long appointmentId, Long patientId);
    void updateAppointmentByDoctor(Long appointmentId, Long doctorId);

    List<AppointmentDto> findAllAppointmentsByPatientId(Long patientId);
    List<AppointmentDto> findAllAppointmentsByDoctorId(Long doctorId);
    List<AppointmentDto> findAllAppointments();
}
