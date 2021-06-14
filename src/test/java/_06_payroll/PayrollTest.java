package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	double hourlyWage = 11.25;
    	int numHours = 8;
    	double expected = 90;

        //when
    	double actual = payroll.calculatePaycheck(hourlyWage, numHours);

        //then
    	assertEquals(expected, actual);
    	
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int milesTraveled = 120;
    	double expected = 69;
    	
        //when
    	double actual = payroll.calculateMileageReimbursement(milesTraveled);

        //then
    	assertEquals(expected, actual);
    	
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String employeeName = "Bob";
    	double hourlyWage = 8.75;
    	String expected = "Hello Bob, We are pleased to offer you an hourly wage of 8.75";

        //when
    	String actual = payroll.createOfferLetter(employeeName, hourlyWage);

        //then
    	assertEquals(actual, expected);
    }

}