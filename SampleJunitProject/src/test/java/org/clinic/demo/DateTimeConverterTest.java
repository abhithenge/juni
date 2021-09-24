package org.clinic.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Tag("dateTime")//for tag based execution
@DisplayName("DateTimeConverter Utility Test")
class DateTimeConverterTest {

    @Nested
    @DisplayName("Test TODAY's date scenarios")
    class TodaysDateTest{
        @Test
        @DisplayName("Test converting TODAY's date from string into date and time")
        void convertToDateTimeFromStringTODAYTest(){
            LocalDate today = LocalDate.of(2021, 9,24);
            LocalDateTime dateTime = DateTimeConverter.convertToDateTimeFromString("today 1:00 pm",today);
            assertEquals(dateTime, LocalDateTime.of(2021,9,24,13,0), "Failed to convert" +
                    "'today' string to expected date and time, date passed in today is :"+ today);// message in assert will only invoke when test failed or in failure case.
        }

        @Test
        @DisplayName("Test converting TODAY's date from string into date and time ignoreCase")
        void convertToDateTimeFromStringTODAYTestCaseInsensitive(){
            LocalDate today = LocalDate.of(2021, 9,24);
            LocalDateTime dateTime = DateTimeConverter.convertToDateTimeFromString("ToDay 1:00 pm",today);
            assertEquals(dateTime, LocalDateTime.of(2021,9,24,13,0), () -> "Failed to convert" +
                    "'today' string to expected date and time, date passed in today is :"+ today);//using lambda function will save overhead of computing passed message.
        }
    }

    @Test
    @Tag("selected")//for tag based execution
    @DisplayName("Test convert passed date and time string pattern into Date and time")
    void convertToDateTimeFromStringPATTERNTest(){
        LocalDateTime dateTime = DateTimeConverter.convertToDateTimeFromString("09/24/2021 1:00 pm",
                LocalDate.of(2021, 9,24));
        assertEquals(dateTime, LocalDateTime.of(2021,9,24,13,0));
    }

    @Test
    @DisplayName("Test exception in converting date time from string")
    void convertToDateTimeFromStringExceptionTest(){
        //LocalDateTime dateTime = DateTimeConverter.convertToDateTimeFromString("09/24/2021 100 pm",
        //        LocalDate.of(2021, 9,24));
        //assertEquals(dateTime, LocalDateTime.of(2021,9,24,13,0));This will throw an exception.
        //Junit5 provides the exception checking assert methods as below
        Throwable message = assertThrows(RuntimeException.class, () -> DateTimeConverter.convertToDateTimeFromString("09/24/2021 100 pm",
                LocalDate.of(2021, 9,24)));
        //assertEquals("Failed to create date time from : [09/24/2021 100 PM], enter date in format [M/d/yyyy h:mm a]", message);
    }
}