package fr.cs.group29.myFoodora.users;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.cs.group29.myFoodora.common.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerTest {

    private Customer customer;
    private Restaurant restaurant;
    private Order order;

    @BeforeEach
    void setUp() {
        customer = new Customer("1", "John", "Doe", "johndoe", "john@example.com", "1234567890", "password", "1234 Street");
        restaurant = new Restaurant("2", "RestaurantName", "Doe", "restaurant123", "restaurant@example.com", "0987654321", "password", "5678 Avenue");
        
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Pizza", 10.0, MenuItemCategory.MAIN_DISH, MenuItemType.STANDARD));
        order = new Order(customer, restaurant, items);
    }

    @Test
    void testSetAndGetAddress() {
        customer.setAddress("4321 Avenue");
        assertEquals("4321 Avenue", customer.getAddress());
    }

    @Test
    void testSetAndGetFidelityCardType() {
        customer.setFidelityCard(FidelityCardType.POINT);
        assertEquals(FidelityCardType.POINT, customer.getFidelityCardType());
    }

    @Test
    void testGetDiscount() {
        assertEquals(0, customer.getDiscount()); // BASIC card by default
        customer.setFidelityCard(FidelityCardType.POINT);
        assertEquals(0, customer.getDiscount()); // No points yet
        customer.updatePoints(100);
        assertEquals(0.1, customer.getDiscount()); // 100 points, 10% discount
    }

    @Test
    void testGetAndSetPoints() {
        assertEquals(0, customer.getPoints());
        customer.setPoints(50);
        assertEquals(50, customer.getPoints());
    }

    @Test
    void testUpdatePoints() {
        customer.updatePoints(10);
        assertEquals(10, customer.getPoints());
        customer.updatePoints(15);
        assertEquals(25, customer.getPoints());
    }

    @Test
    void testAddOrderToHistory() {
        customer.addOrderToHistory(order);
        assertEquals(1, customer.getCustomerOrders().size());
        assertEquals(order, customer.getCustomerOrders().get(0));
    }

    @Test
    void testSubscribeToOffers() {
        assertFalse(customer.isSubscribedToOffers());
        customer.subscribeToOffers();
        assertTrue(customer.isSubscribedToOffers());
    }

    @Test
    void testUnsubscribeFromOffers() {
        customer.subscribeToOffers();
        assertTrue(customer.isSubscribedToOffers());
        customer.unsubscribeFromOffers();
        assertFalse(customer.isSubscribedToOffers());
    }
}
