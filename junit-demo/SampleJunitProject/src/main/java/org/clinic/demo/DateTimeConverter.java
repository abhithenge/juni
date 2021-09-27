package org.clinic.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeConverter {

    public static LocalDateTime convertToDateTimeFromString (String dateTimeString, LocalDate today){
        LocalDateTime localDateTime;
        try{
            if(dateTimeString.toLowerCase().startsWith("today")){
                String[] parts = dateTimeString.split(" ",2);
                LocalTime time = LocalTime.parse(parts[1].toUpperCase(), DateTimeFormatter.ofPattern("h:mm a", Locale.US));
                localDateTime = LocalDateTime.of(today,time);
            }else{
                localDateTime = LocalDateTime.parse(dateTimeString.toUpperCase(), DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
            }
        }catch (Throwable t){
            System.out.println("Exception occurred: "+t.getMessage());
            throw new RuntimeException("Failed to create date time from : ["+ dateTimeString.toUpperCase()+"], enter date in format [M/d/yyyy h:mm a]");
        }
        return localDateTime;
    }
}
