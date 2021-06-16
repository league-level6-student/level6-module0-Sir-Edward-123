package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

    MyDonutShop myDonutShop;
    
    @Mock
    BakeryService bakeryService;
    
    @Mock
    PaymentService paymentService;
    
    @Mock
    DeliveryService deliveryService;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	Order order = new Order(
    			"Name",
    			"Phone_Number",
    			1, 
    			2.50,
    			"Credit_Card_Number",
    			true);
    	myDonutShop.openForTheDay();
    	when(bakeryService.getDonutsRemaining()).thenReturn(1);
    	
        //when 	
        //then
    	assertDoesNotThrow(() -> myDonutShop.takeOrder(order));
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
    	Order order = new Order(
    			"Name",
    			"Phone_Number",
    			5, 
    			2.50,
    			"Credit_Card_Number",
    			true);
    	myDonutShop.openForTheDay();
    	when(bakeryService.getDonutsRemaining()).thenReturn(1);
    	
        //when
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
    	
        //then
    	assertEquals("Insufficient donuts remaining", exceptionThrown.getMessage());
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given
    	Order order = new Order(
    			"Name",
    			"Phone_Number",
    			5, 
    			2.50,
    			"Credit_Card_Number",
    			true);
    	myDonutShop.closeForTheDay();

        //when
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));

        //then
    	assertEquals("Sorry we're currently closed", exceptionThrown.getMessage());
    }

}