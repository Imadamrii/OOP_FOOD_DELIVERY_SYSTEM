package fr.cs.group29.myFoodora.common;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import fr.cs.group29.myFoodora.users.Customer;
import fr.cs.group29.myFoodora.users.Restaurant;

public class OrderTest {

    private Customer customer;
    private Restaurant restaurant;
    private List<MenuItem> items;
    private Order order;

    @BeforeEach
    void setUp() {
        customer = new Customer("1", "John", "Doe", "johndoe", "john@example.com", "1234567890", "password", "1234 Street");
        restaurant = new Restaurant("2", "RestaurantName", "Doe", "restaurant123", "restaurant@example.com", "0987654321", "password", "5678 Avenue");
        items = new ArrayList<>();

        MenuItem item1 = new MenuItem("Burger", 5.99, MenuItemCategory.MAIN_DISH, MenuItemType.STANDARD);
        MenuItem item2 = new MenuItem("Fries", 2.49, MenuItemCategory.STARTER, MenuItemType.STANDARD);
        items.add(item1);
        items.add(item2);

        order = new Order(customer, restaurant, items);
    }

    @Test
    void testInitialGrossPrice() {
        double expectedGrossPrice = 8.48; // 5.99 + 2.49
        assertEquals(expectedGrossPrice, order.getGrossPrice());
    }

    @Test
    void testAddItem() {
        MenuItem item = new MenuItem("Drink", 1.99, MenuItemCategory.DESSERT, MenuItemType.STANDARD);
        order.addItem(item);
        assertTrue(order.getItems().contains(item));
        assertEquals(10.47, order.getGrossPrice()); // 8.48 + 1.99
    }

    @Test
    void testRemoveItem() {
        MenuItem item = items.get(0); // "Burger"
        order.removeItem(item);
        assertFalse(order.getItems().contains(item));
        assertEquals(2.49, order.getGrossPrice()); // Only "Fries" left
    }

    @Test
    void testComputeGrossPrice() {
        double grossPrice = order.computeGrossPrice();
        assertEquals(8.48, grossPrice);
    }

    @Test
    void testGetCustomer() {
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void testGetRestaurant() {
        assertEquals(restaurant, order.getRestaurant());
    }

    @Test
    void testGetItems() {
        assertEquals(items, order.getItems());
    }

    @Test
    void testGrossPriceAfterModifications() {
        MenuItem item = new MenuItem("Drink", 1.99, MenuItemCategory.DESSERT, MenuItemType.STANDARD);
        order.addItem(item);
        assertEquals(10.47, order.getGrossPrice());
        order.removeItem(item);
        assertEquals(8.48, order.getGrossPrice());
    }
}
