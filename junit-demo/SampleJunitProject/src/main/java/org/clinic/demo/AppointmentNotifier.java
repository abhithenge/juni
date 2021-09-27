package org.clinic.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AppointmentNotifier {

    private ClinicCalender calender;
    private EmailNotifier notifier;

    public AppointmentNotifier(ClinicCalender calender, EmailNotifier notifier) {
        this.calender = calender;
        this.notifier = notifier;
    }

    public void send(){
        for(PatientAppointment appt : calender.getTomorrowsAppointments()){
            String email = appt.getPatientFirstName()+appt.getPatientLastName()+"@gmail.com";
            System.out.println("Sending message "+ buildMessageBody(appt));
            notifier.sendNotification("Appointment Reminder", buildMessageBody(appt), email);
        }
    }

    private String buildMessageBody(PatientAppointment appt) {
        return "You have an appointment tomorrow at " + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US)) +
                " with "+appt.getDoctor().getName()+".";
    }
}
