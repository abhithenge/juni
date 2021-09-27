package org.clinic.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BMI Calculator Test")
class BmiCalculatorTest {

    @Test
    @DisplayName("Calculate BMI for patient")
    public void calculateBmiPositiveTest(){
        assertEquals(19.2, BmiCalculator.calculateBmi(69,130));
        assertEquals(21.52, BmiCalculator.calculateBmi(70, 150));
    }
}