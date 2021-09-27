package org.clinic.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClinicCalender {

    private List<PatientAppointment> appointments;
    private LocalDate today;

    public ClinicCalender(LocalDate today) {
        this.appointments = new ArrayList<>();
        this.today = today;
    }

    public void addAppointment(String patientFirstName, String patientLastName, String doctorKey, String dateTime){
        Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
        LocalDateTime localDateTime = DateTimeConverter.convertToDateTimeFromString(dateTime,today);
        PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName, localDateTime, doc);
        appointments.add(appointment);
    }

    //below method moved to utility class DateTimeConverter
    /*private LocalDateTime convertToDateTimeFromString(String dateTime) {
        LocalDateTime localDateTime;
        try{
            if(dateTime.toLowerCase().startsWith("today")){
                String[] parts = dateTime.split(" ",2);
                LocalTime time = LocalTime.parse(parts[1].toUpperCase(), DateTimeFormatter.ofPattern("h:mm a", Locale.US));
                localDateTime = LocalDateTime.of(today,time);
            }else{
                localDateTime = LocalDateTime.parse(dateTime.toUpperCase(), DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
            }
        }catch (Throwable t){
            System.out.println("Exception occurred: "+t.getMessage());
            throw new RuntimeException("Failed to create date time from : ["+ dateTime.toUpperCase()+"], enter date in format [M/d/yyyy h:mm a]");
        }
        return localDateTime;
    }*/

    public List<PatientAppointment> getAppointments() {
        return this.appointments;
    }

    public List<PatientAppointment> getTodaysAppointments(){
        return getAppointmentsForDate(today);
    }

    public List<PatientAppointment> getTomorrowsAppointments(){
        LocalDate tomorrowsDate = today.plusDays(1);
        return getAppointmentsForDate(tomorrowsDate);
    }

    public boolean hasAppointment(LocalDate date){
        boolean hasAppt = false;
        for (PatientAppointment app : appointments){
            if(app.getAppointmentDateTime().toLocalDate().equals(date))
                hasAppt = true;
        }
        return hasAppt;
    }

    private List<PatientAppointment> getAppointmentsForDate(LocalDate appointmnetDate){
        List<PatientAppointment> appointmentList = new ArrayList<>();
        for(PatientAppointment appt : appointments){
            if(appt.getAppointmentDateTime().toLocalDate().equals(appointmnetDate))
                appointmentList.add(appt);
        }
        return appointmentList;
    }
}
