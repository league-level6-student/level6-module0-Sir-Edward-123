package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    
    String name = "Bob";
    
    @Mock
    Car car;
    
    @Mock
    CellPhone cellPhone;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	deliveryDriver = new DeliveryDriver(name, car, cellPhone);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expected = true;
    	when(cellPhone.browseCatMemes()).thenReturn(true);

        //when
    	boolean actual = deliveryDriver.wasteTime();

        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldRefuel() {
        //given
    	boolean expected = true;
    	int octane = 90;
    	when(car.fillTank(octane)).thenReturn(true);

        //when
    	boolean actual = deliveryDriver.refuel(octane);

        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	boolean expected = true;
    	String phoneNum = "8581234567";
    	when(cellPhone.call(phoneNum)).thenReturn(true);

        //when
    	boolean actual = deliveryDriver.contactCustomer(phoneNum);

        //then
    	assertEquals(expected, actual);
    }

}