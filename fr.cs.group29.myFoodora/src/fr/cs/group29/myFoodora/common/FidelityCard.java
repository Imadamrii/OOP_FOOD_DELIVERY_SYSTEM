package fr.cs.group29.myFoodora.common;


public class FidelityCard {
    private double points;
    private FidelityCardType type;

    public FidelityCard(FidelityCardType type) {
        this.points = 0; // Initialize points to zero
        this.type = type;
    }

    public void setPoints(double newPoints) {
        this.points = newPoints;
    }

    public double getPoints() {
        return this.points;
    }

    public FidelityCardType getType() {
        return type;
    }

    public void setType(FidelityCardType type) {
        this.type = type;
    }

    public double getDiscount() {
        switch (type) {
            case BASIC:
                return 0;
            case POINT:
                return (points >= 100) ? 0.1 : 0;
            case LOTTERY:
                return (Math.random() < 0.001) ? 1 : 0;
            default:
                return 0;
        }
    }

    public void updatePoints(double amount) {
        this.points += amount;
    }
}
