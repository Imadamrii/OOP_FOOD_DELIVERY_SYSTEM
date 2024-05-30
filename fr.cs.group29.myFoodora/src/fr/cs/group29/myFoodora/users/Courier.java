package fr.cs.group29.myFoodora.users;

import fr.cs.group29.myFoodora.common.*;

public class Courier extends User {
    private String currentPosition;
    private boolean onDuty;
    private Order currentOrder;

    public Courier(String id, String name, String surname, String username, String email, String phone, String password, String currentPosition) {
        super(id, name, surname, username, email, phone, password);
        this.setCurrentPosition(currentPosition);
        this.onDuty = false;
    }

    public void setOnDuty() {
        this.onDuty = true;
    }

    public void setOffDuty() {
        this.onDuty = false;
    }

    public void updatePosition(String newPosition) {
        this.setCurrentPosition(newPosition);
    }

    public boolean isAvailable() {
        return onDuty && currentOrder == null;
    }

    public void assignOrder(Order order) {
        if (isAvailable()) {
            this.currentOrder = order;
            System.out.println("Order assigned to courier: " + this.getUsername());
        } else {
            System.out.println("Courier " + this.getUsername() + " is not available.");
        }
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }
}
