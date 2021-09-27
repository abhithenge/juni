package org.clinic.demo;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentNotifierTest {

    private EmailNotifierTest notifierTest;

    @BeforeEach
    void init(){
        notifierTest = new EmailNotifierTest();
    }

    @Test
    void sendTest(){
        ClinicCalender calender = new ClinicCalender(LocalDate.of(2021,9,26));
        calender.addAppointment("suhit","rode","ajay","09/27/2021 1:00 pm");
        AppointmentNotifier notifier = new AppointmentNotifier(calender, notifierTest);
        notifier.send();

        assertEquals(1, notifierTest.receivedMessage.size());
        EmailNotifierTest.Message emailMsg = notifierTest.receivedMessage.get(0);
        assertAll(
                () -> assertEquals("suhitrode@gmail.com",emailMsg.toEmailId),
                () -> assertEquals("Appointment Reminder", emailMsg.subject),
                () -> assertEquals("You have an appointment tomorrow at 1:00 PM with Dr Ajay.", emailMsg.body)

        );
    }

}