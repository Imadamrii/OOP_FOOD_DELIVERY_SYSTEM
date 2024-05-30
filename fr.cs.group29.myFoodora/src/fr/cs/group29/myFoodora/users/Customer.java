package fr.cs.group29.myFoodora.users;
import java.util.ArrayList;
import java.util.List;

import  fr.cs.group29.myFoodora.common.*;


public class Customer extends User {
    private String address;
    private FidelityCard fidelityCard;
    private List<Order> customerOrders;
    private boolean isSubscribedToOffers;
    
    public List<Order> getCustomerOrders() {
		return customerOrders;
	}

	public Customer(String id, String name, String surname, String username, String email, String phone, String password, String address) {
        super(id, name, surname, username, email, phone, password);
        this.address = address;
        this.fidelityCard = new FidelityCard(FidelityCardType.BASIC);
        this.customerOrders = new ArrayList<>();
    }

    public Customer(String id, String name, String surname, String username, String email, String phone, String password, String address, FidelityCardType type) {
        super(id, name, surname, username, email, phone, password);
        this.address = address;
        this.fidelityCard = new FidelityCard(type);
        this.customerOrders = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFidelityCard(FidelityCardType fidelityCardType) {
        this.fidelityCard.setType(fidelityCardType);
    }

    public FidelityCardType getFidelityCardType() {
        return this.fidelityCard.getType();
    }

    public double getDiscount() {
        return this.fidelityCard.getDiscount();
    }

    public double getPoints() {
        return this.fidelityCard.getPoints();
    }

    public void setPoints(double newpoints) {
        this.fidelityCard.setPoints(newpoints);
    }

    public void updatePoints(double amount) {
        this.fidelityCard.updatePoints(amount);
    }

    public void addOrderToHistory(Order order) {
        customerOrders.add(order);
        return;
    }
    
    public boolean isSubscribedToOffers() {
        return isSubscribedToOffers;
    }

    public void subscribeToOffers() {
        this.isSubscribedToOffers = true;
        System.out.println(this.getUsername() + " subscribed to special offers.");
    }

    public void unsubscribeFromOffers() {
        this.isSubscribedToOffers = false;
        System.out.println(this.getUsername() + " unsubscribed from special offers.");
    }
}
