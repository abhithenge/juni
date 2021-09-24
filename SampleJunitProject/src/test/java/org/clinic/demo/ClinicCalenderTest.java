package org.clinic.demo;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalenderTest {

    private ClinicCalender calender;

    @BeforeAll
    static void setUp(){
        System.out.println("Before All");
    }

    @BeforeEach
    void init(){
        System.out.println("Before Each");
        calender = new ClinicCalender(LocalDate.of(2021,9,24));
    }

    @Test
    public void addAppointmentTest() {
        System.out.println("adding patient appointment");
        calender.addAppointment("Sujay","Joshi", "ajay", "09/02/2021 2:00 pm");
        List<PatientAppointment> appointments = calender.getAppointments();
        assertNotNull(appointments);
        assertEquals(1,appointments.size());
        PatientAppointment app = appointments.get(0);
        assertEquals("Sujay", app.getPatientFirstName());
        assertEquals("Joshi", app.getPatientLastName());
        assertEquals(Doctor.ajay, app.getDoctor());
        assertSame(Doctor.ajay, app.getDoctor());//checks whether these 2 objects points to exactly same object in memory.
        assertEquals("9/2/2021 02:00 PM", app.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
        /*assertAll(
             () ->    assertEquals("Sujay1", app.getPatientFirstName()),
             () ->    assertEquals("Joshi1", app.getPatientLastName()),
             () ->    assertEquals(Doctor.ajay, app.getDoctor()),
             () ->    assertSame(Doctor.ajay, app.getDoctor()),//checks whether these 2 objects points to exactly same object in memory.
             () ->    assertEquals("9/2/2021 02:00 PM", app.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
        );*///used to check all and log all asserts and assert failure.
    }

    @Test
    @Tag("selected")//for tag based execution
    public void hasAppointmentTRUETest(){
        System.out.println("hasAppointment True Test");
        calender.addAppointment("Sujay", "Joshi", "ajay", "09/23/2021 2:00 pm");
        assertTrue(calender.hasAppointment(LocalDate.of(2021, 9, 23)));
    }

    @Test
    @Tag("selected")//for tag based execution
    public void hasAppointmentFALSETest(){
        System.out.println("hasAppointment False Test");
        calender.addAppointment("Sujay", "Joshi", "ajay", "09/02/2021 2:00 pm");
        assertFalse(calender.hasAppointment(LocalDate.of(2021, 9, 23)));
    }

    @Test
    //@Disabled //to ignore the test case being executed.
    public void displayTodaysAppointmentsTest(){
        System.out.println("display today's appointment");
        calender.addAppointment("Ravi", "Shastri","mangesh","09/24/2021 02:00 pm");
        calender.addAppointment("Mohamad", "Ajharuddin","ajay","09/24/2021 05:00 pm");
        calender.addAppointment("Mithali", "Raj","sunita","08/20/2021 01:00 pm");
        assertEquals(2, calender.getTodaysAppointments().size());
        //assertEquals(calender.getTodaysAppointments(), calender.getAppointments());//This fails as the todays appointments having 2 records where appointments having all appointments.
        //assertIterableEquals(calender.getTodaysAppointments(), calender.getAppointments());// we can use this asset method to compare 2 collections type objects.
    }

    @AfterEach
    void tearDownEachTest(){
        System.out.println("After Each");
    }

    @AfterAll
    static void tearDownTestClass(){
        System.out.println("After All");
    }

}