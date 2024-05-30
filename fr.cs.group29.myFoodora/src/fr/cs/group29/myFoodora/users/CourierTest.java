package fr.cs.group29.myFoodora.users;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.cs.group29.myFoodora.common.*;

import java.util.ArrayList;
import java.util.List;

public class CourierTest {

    private Courier courier;
    private Customer customer;
    private Restaurant restaurant;
    private Order order;

    @BeforeEach
    void setUp() {
        courier = new Courier("1", "Jane", "Doe", "janedoe", "jane@example.com", "1234567890", "password", "LocationA");
        customer = new Customer("2", "John", "Smith", "johnsmith", "john@example.com", "0987654321", "password", "1234 Street");
        restaurant = new Restaurant("3", "FoodPlace", "Owner", "foodplace", "food@example.com", "1122334455", "password", "5678 Avenue");
        
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Pizza", 10.0, MenuItemCategory.MAIN_DISH, MenuItemType.STANDARD));
        order = new Order(customer, restaurant, items);
    }

    @Test
    void testSetOnDuty() {
        courier.setOnDuty();
        assertTrue(courier.isAvailable());
    }

    @Test
    void testSetOffDuty() {
        courier.setOnDuty();
        courier.setOffDuty();
        assertFalse(courier.isAvailable());
    }

    @Test
    void testUpdatePosition() {
        courier.updatePosition("LocationB");
        assertEquals("LocationB", courier.getCurrentPosition());
    }

    @Test
    void testIsAvailable() {
        assertFalse(courier.isAvailable()); // Initially off-duty
        courier.setOnDuty();
        assertTrue(courier.isAvailable()); // Now on-duty and no current order
    }

    @Test
    void testAssignOrderWhenAvailable() {
        courier.setOnDuty();
        assertTrue(courier.isAvailable());
        courier.assignOrder(order);
        assertFalse(courier.isAvailable()); // Should not be available after assignment
    }

    @Test
    void testAssignOrderWhenNotAvailable() {
        assertFalse(courier.isAvailable());
        courier.assignOrder(order);
        assertNull(courier.getCurrentOrder()); // Order should not be assigned
    }
}
