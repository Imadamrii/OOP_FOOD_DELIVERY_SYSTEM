package fr.cs.group29.myFoodora.common;
import java.util.ArrayList;
import java.util.List;



import  fr.cs.group29.myFoodora.users.*;


import java.util.ArrayList;
import java.util.List;

import fr.cs.group29.myFoodora.users.*;

public class FoodoraSystem {

    private List<Customer> customers;
    private List<Restaurant> restaurants;
    private List<Courier> couriers;
    private List<Manager> managers;
    private List<Order> orders;

    public FoodoraSystem() {
        customers = new ArrayList<>();
        restaurants = new ArrayList<>();
        couriers = new ArrayList<>();
        managers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void registerUser(User user) {
        if (user instanceof Customer) {
            customers.add((Customer) user);
        } else if (user instanceof Restaurant) {
            restaurants.add((Restaurant) user);
        } else if (user instanceof Courier) {
            couriers.add((Courier) user);
        } else if (user instanceof Manager) {
            managers.add((Manager) user);
        } else {
            System.out.println("Unknown user type: " + user.getClass().getName());
        }
    }

    public void placeOrder(Customer customer, Restaurant restaurant, List<MenuItem> items) {
        if (!customers.contains(customer)) {
            System.out.println("Error: Customer is not in the database");
            return;
        }

        // We create the order
        Order order = new Order(customer, restaurant, items);
        orders.add(order);

        double grossPrice = order.computeGrossPrice();
        double discount = customer.getDiscount();
        double finalPrice = grossPrice * (1 - discount);

        // Update fidelity points if applicable
        if (discount == 0.1) {
            customer.updatePoints(-100); // Deduct 100 points for the 10% discount
        } else {
            customer.updatePoints(grossPrice / 10); // Add points based on the order amount
        }

        customer.addOrderToHistory(order);

        System.out.println("Order placed successfully for customer: " + customer.getUsername() + " at the price: " + finalPrice);
    }

    public void assignCourier(Order order) {
        // Find an available courier and assign the order to them
        for (Courier courier : couriers) {
            if (courier.isAvailable()) {
                courier.assignOrder(order);
                System.out.println("Order assigned to courier: " + courier.getUsername());
                return;
            }
        }
        System.out.println("No available couriers to assign the order.");
    }

    public void notifyUsersOfOffers(SpecialOffer offer) {
        for (Customer customer : customers) {
            if (customer.isSubscribedToOffers()) {
                // Send notification to the customer
                System.out.println("Notifying " + customer.getUsername() + " of new offer: " + offer.getDetails());
            }
        }
    }
}