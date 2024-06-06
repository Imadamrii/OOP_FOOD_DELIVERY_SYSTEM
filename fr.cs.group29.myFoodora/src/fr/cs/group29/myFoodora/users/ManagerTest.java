package fr.cs.group29.myFoodora.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManagerTest {

    private Manager manager;
    private List<User> users;
    private List<Order> orders;
    private List<Customer> customers;

    @BeforeEach
    public void setUp() {
        manager = new Manager("1", "John", "Doe", "johndoe", "john.doe@example.com", "1234567890", "password123");
        users = new ArrayList<>();
        orders = new ArrayList<>();
        customers = new ArrayList<>();
    }

    @Test
    public void testManagerCreation() {
        assertNotNull(manager);
        assertEquals("1", manager.getId());
        assertEquals("John", manager.getName());
        assertEquals("Doe", manager.getSurname());
        assertEquals("johndoe", manager.getUsername());
        assertEquals("john.doe@example.com", manager.getEmail());
        assertEquals("1234567890", manager.getPhone());
        assertEquals("password123", manager.getPassword());
    }

    @Test
    public void testAddAndRemoveUser() {
        User newUser = new Customer("2", "Jane", "Smith", "janesmith", "jane.smith@example.com", "0987654321", "password456");
        manager.addUser(newUser, users);
        assertTrue(users.contains(newUser));

        manager.removeUser(newUser, users);
        assertFalse(users.contains(newUser));
    }

    @Test
    public void testActivateAndDeactivateUser() {
        User newUser = new Customer("2", "Jane", "Smith", "janesmith", "jane.smith@example.com", "0987654321", "password456");
        manager.addUser(newUser, users);

        manager.deactivateUser(newUser);
        assertFalse(newUser.isActive());

        manager.activateUser(newUser);
        assertTrue(newUser.isActive());
    }

    @Test
    public void testSetServiceFeePercentage() {
        manager.setServiceFeePercentage(5.0);
        assertEquals(5.0, manager.getServiceFeePercentage());
    }

    @Test
    public void testSetMarkupPercentage() {
        manager.setMarkupPercentage(10.0);
        assertEquals(10.0, manager.getMarkupPercentage());
    }

    @Test
    public void testSetDeliveryCost() {
        manager.setDeliveryCost(2.5);
        assertEquals(2.5, manager.getDeliveryCost());
    }

    @Test
    public void testComputeTotalIncome() {
        // Assuming Order class has a constructor Order(double totalPrice)
        orders.add(new Order(50.0));
        orders.add(new Order(100.0));

        double totalIncome = manager.computeTotalIncome(orders);
        assertEquals(150.0, totalIncome);
    }

    @Test
    public void testComputeTotalProfit() {
        // Assuming Order class has a constructor Order(double totalPrice)
        orders.add(new Order(50.0));
        orders.add(new Order(100.0));
        manager.setMarkupPercentage(10.0);

        double totalProfit = manager.computeTotalProfit(orders);
        assertEquals(15.0, totalProfit); // 10% of 150.0
    }

    @Test
    public void testComputeAverageIncomePerCustomer() {
        Customer customer1 = new Customer("3", "Alice", "Brown", "alicebrown", "alice.brown@example.com", "1231231234", "password789");
        Customer customer2 = new Customer("4", "Bob", "White", "bobwhite", "bob.white@example.com", "3213214321", "password101");

        customers.add(customer1);
        customers.add(customer2);

        orders.add(new Order(50.0, customer1));
        orders.add(new Order(100.0, customer2));

        double averageIncome = manager.computeAverageIncomePerCustomer(orders, customers);
        assertEquals(75.0, averageIncome);
    }
}
