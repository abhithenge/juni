package org.clinic.demo;

import java.time.LocalDateTime;

public class PatientAppointment {

    private String patientFirstName;
    private String patientLastName;
    private LocalDateTime appointmentDateTime;
    private Doctor doctor;

    public PatientAppointment(String patientFirstName, String patientLastName, LocalDateTime appointmentDateTime, Doctor doctor) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.appointmentDateTime = appointmentDateTime;
        this.doctor = doctor;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
