package com.example.project.service;

import com.example.project.dtos.AppointmentDto;
import com.example.project.entity.Appointment;
import com.example.project.entity.Doctor;
import com.example.project.entity.Patient;
import com.example.project.repo.AppointmentRepo;
import com.example.project.repo.DoctorRepo;
import com.example.project.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientRepo patientRepo;


    @Override
    public AppointmentDto findAppointmentById(Long id) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new RuntimeException(">>> appointment not found"));
        return this.mapToAppointmentDto(appointment);
    }


    @Override
    public void saveAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setType(appointmentDto.getType());
        appointment.setDate(appointmentDto.getDate());
        appointment.setHospitalName(appointmentDto.getHospitalName());

        Doctor doctor = doctorRepo.findById(appointmentDto.getDoctorId()).orElseThrow(() -> new RuntimeException(">>> doctor not found"));
        appointment.setDoctor(doctor);

        Patient patient = patientRepo.findById(appointmentDto.getPatientId()).orElseThrow(() -> new RuntimeException(">>> patient not found"));
        appointment.setPatient(patient);

        appointmentRepo.save(appointment);
    }



    @Override
    public boolean updateAppointmentByDoctor(AppointmentDto appointmentDto, Long doctorId) {
        if(appointmentDto.getDoctorId().equals(doctorId)) {
            Appointment appointment = appointmentRepo.findById(appointmentDto.getId()).orElseThrow(() -> new RuntimeException(">>> appointment not found"));
            appointment.setType(appointmentDto.getType());
            appointment.setDate(appointmentDto.getDate());
            appointment.setHospitalName(appointmentDto.getHospitalName());

            Doctor doctor = doctorRepo.findById(appointmentDto.getDoctorId()).orElseThrow(() -> new RuntimeException(">>> doctor not found"));
            appointment.setDoctor(doctor);

            Patient patient = patientRepo.findById(appointmentDto.getPatientId()).orElseThrow(() -> new RuntimeException(">>> patient not found"));
            appointment.setPatient(patient);


            appointmentRepo.save(appointment);
            return true;
        }
        throw new RuntimeException(">>> could not properly update appointment based on doctor_id");
    }



    @Override
    public boolean updateAppointmentByPatient(AppointmentDto appointmentDto, Long patientId) {
        if(appointmentDto.getPatientId().equals(patientId)) {
            Appointment appointment = appointmentRepo.findById(appointmentDto.getId()).orElseThrow(() -> new RuntimeException(">>> appointment not found"));
            appointment.setType(appointmentDto.getType());
            appointment.setDate(appointmentDto.getDate());
            appointment.setHospitalName(appointmentDto.getHospitalName());

            Doctor doctor = doctorRepo.findById(appointmentDto.getDoctorId()).orElseThrow(() -> new RuntimeException(">>> doctor not found"));
            appointment.setDoctor(doctor);

            Patient patient = patientRepo.findById(appointmentDto.getPatientId()).orElseThrow(() -> new RuntimeException(">>> patient not found"));
            appointment.setPatient(patient);

            appointmentRepo.save(appointment);
            return true;
        }
        throw new RuntimeException(">>> could not properly update appointment based on patient_id");
    }

    @Override
    public boolean deleteAppointmentByPatient(Long appointmentId, Long patientId) {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElseThrow();

        if(appointment.getPatient().getId().equals(patientId)) {
            appointmentRepo.delete(appointment);
            return true;
        }
        throw new RuntimeException(">>> could not properly delete appointment based on patient_id");
    }

    @Override
    public boolean deleteAppointmentByDoctor(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElseThrow();

        if(appointment.getDoctor().getId().equals(doctorId)) {
            appointmentRepo.delete(appointment);
            return true;
        }
        throw new RuntimeException(">>> could not properly delete appointment based on doctor_id");
    }

    @Override
    public List<AppointmentDto> findAllAppointments() {
        List<Appointment> appointments = appointmentRepo.findAll();

        return appointments.stream()
                .map(this::mapToAppointmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> findAllAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepo.findAllByPatientId(patientId);

        return appointments.stream()
                .map(this::mapToAppointmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> findAllAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepo.findAllByDoctorId(doctorId);

        return appointments.stream()
                .map(this::mapToAppointmentDto)
                .collect(Collectors.toList());
    }

    private AppointmentDto mapToAppointmentDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setType(appointment.getType());
        appointmentDto.setDate(appointment.getDate());
        appointmentDto.setHospitalName(appointment.getHospitalName());
        appointmentDto.setPatientId(appointment.getPatient().getId());
        appointmentDto.setDoctorId(appointment.getDoctor().getId());
        return appointmentDto;
    }
}
