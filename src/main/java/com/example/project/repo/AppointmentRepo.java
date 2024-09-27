package com.example.project.repo;

import com.example.project.dtos.AppointmentDto;
import com.example.project.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    AppointmentDto updateAppointment(AppointmentDto appointmentDto, Long id);
}
