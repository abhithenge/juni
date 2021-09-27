package org.clinic.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClinicMain {

    private static ClinicCalender calender;

    public static void main(String[] args) throws Throwable {
        Scanner sc = new Scanner(System.in);
        calender = new ClinicCalender(LocalDate.now());
        String lastOption = "";
        System.out.println("Welcome to patient registration system.");
        while(!lastOption.equalsIgnoreCase("x")){
            lastOption = showManu(sc);
        }
        System.out.println("Shutting Down!!!");
    }

    private static String showManu(Scanner sc) throws Throwable {
        System.out.println("Select any one option to proceed:");
        System.out.println("1. Enter patient appointment");
        System.out.println("2. Display all appointments");
        System.out.println("3. Display today's appointments");
        System.out.println("4. Enter patient height and weight");
        System.out.println("X. Exit");
        System.out.println("Enter option: ");
        String option = sc.next();

        switch (option){
            case "1": recordPatientDetails(sc);
                    return option;
            case "2": displayAllAppointments();
                    return option;
            case "3": displayTodaysAppointments();
                    return option;
            case "4": recordPatientHeightWeight(sc);
                    return option;
            default: System.out.println("Invalid option, re-enter correct option.");
                    return option;
        }
    }

    private static void recordPatientDetails(Scanner sc) {
        sc.nextLine();
        System.out.println("Enter patient details for appointment:");
        System.out.println("First name:");
        String firstName = sc.nextLine();
        System.out.println("Last name:");
        String lastName = sc.nextLine();
        System.out.println("Appointment Date (M/d/yyyy h:mm a):");
        String appointmentDate = sc.nextLine();
        System.out.println("Doctor name:");
        String doc = sc.nextLine();
        try{
            calender.addAppointment(firstName, lastName, doc, appointmentDate);
        }catch (Throwable t){
            System.out.println("Error occurred "+t.getMessage());
            return;
        }
        System.out.println("Patient appointment registered successfully.");
    }

    private static void displayAllAppointments() throws Throwable {
        System.out.println("\n\nAll appointments are:");
        listAppointments(calender.getAppointments());
        System.out.println("Enter any key to continue...");
        System.in.read();
        System.out.println("\n\n");
    }

    private static void displayTodaysAppointments() throws Throwable {
        System.out.println("Today's appointments are:\n");
        listAppointments(calender.getTodaysAppointments());
        System.out.println("Enter any key to continue...");
        System.in.read();
        System.out.println("\n\n");
    }

    private static void listAppointments(List<PatientAppointment> appointments) {
        for(PatientAppointment app : appointments){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String appointmentTime = formatter.format(app.getAppointmentDateTime());
            System.out.println(String.format("Time: %s    Patient: %s %s   Doctor: %s ",appointmentTime, app.getPatientFirstName(), app.getPatientLastName(),
                    app.getDoctor().getName()));
        }
    }

    private static void recordPatientHeightWeight(Scanner sc) {
        sc.nextLine();
        System.out.println("Enter patient height and weight.");
        System.out.println("Patient first name:");
        String firstName = sc.nextLine();
        System.out.println("Patient last name:");
        String lastName = sc.nextLine();
        PatientAppointment appointment = findPatientAppointment(lastName,firstName).orElse(null);
        if(appointment != null){
            System.out.println("Height in inches");
            Integer inches = sc.nextInt();
            System.out.println("Weight in inches");
            Integer pounds = sc.nextInt();
            double roundoffBmi = BmiCalculator.calculateBmi(inches, pounds);
            appointment.setApptBmi(roundoffBmi);
            System.out.println("Set patient BMI to "+roundoffBmi+"\n\n");
        }else{
            System.out.println("Patient details not found.");
        }
    }

    private static Optional<PatientAppointment> findPatientAppointment(String lastName, String firstName) {
        return calender.getTodaysAppointments().stream().filter(p -> (p.getPatientFirstName().equalsIgnoreCase(firstName) && p.getPatientFirstName().equalsIgnoreCase(firstName))).
                findFirst();
    }


}
