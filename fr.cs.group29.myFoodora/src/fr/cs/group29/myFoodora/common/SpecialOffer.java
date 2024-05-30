package fr.cs.group29.myFoodora.common;

public class SpecialOffer {
    private String details;
    private double discount;

    public SpecialOffer(String details, double discount) {
        this.details = details;
        this.discount = discount;
    }

    public String getDetails() {
        return details;
    }

    public double getDiscount() {
        return discount;
    }
}