package fr.cs.group29.myFoodora.common;

import java.util.List;

import fr.cs.group29.myFoodora.users.*;

public class Order {
    private Customer customer;
    private Restaurant restaurant;
    private List<MenuItem> items;
    private double grossPrice;

    public Order(Customer customer, Restaurant restaurant, List<MenuItem> items) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = items;
        this.grossPrice = computeGrossPrice();
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
        this.grossPrice += item.getPrice();
    }

    public void removeItem(MenuItem item) {
        this.items.remove(item);
        this.grossPrice -= item.getPrice();
    }

    public double computeGrossPrice() {
        double grossPrice = 0;
        for (MenuItem item : items) {
            grossPrice += item.getPrice();
        }
        return grossPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getGrossPrice() {
        return grossPrice;
    }
}
