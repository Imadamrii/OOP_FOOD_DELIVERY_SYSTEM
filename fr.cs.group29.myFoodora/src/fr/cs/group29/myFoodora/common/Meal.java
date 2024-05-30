package fr.cs.group29.myFoodora.common;

import java.util.List;

public class Meal {
	private boolean specialOffer;
	private String name;
	private List<MenuItem> items;
	
	public Meal(boolean specialOffer, String name, List<MenuItem> items) {
		super();
		this.specialOffer = specialOffer;
		this.name = name;
		this.items = items;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		double total = 0;
		for (MenuItem item : this.items) {
			total += item.getPrice();
		}
		double discount = 0;
		
		if (this.specialOffer) {
			discount = 0.05;
		}
		return total*(1-discount);
	}
	
	public MenuItemType getType() {
	    boolean vegetarianChecker = true;
	    boolean glutenChecker = true;

	    for (MenuItem item : items) {
	        if (item.getType() != MenuItemType.VEGETARIAN) {
	            vegetarianChecker = false;
	        }

	        if (item.getType() != MenuItemType.GLUTEN_FREE) {
	            glutenChecker = false;
	        }
	        
	        // If both checks fail, no need to continue iterating
	        if (!vegetarianChecker && !glutenChecker) {
	            break;
	        }
	    }

	    if (vegetarianChecker) {
	        return MenuItemType.VEGETARIAN;
	    } else if (glutenChecker) {
	        return MenuItemType.GLUTEN_FREE;
	    } else {
	        return MenuItemType.STANDARD;
	    }
	}

	
	public String getMealSize() {
	    int starter_count = 0;
	    int main_count = 0;
	    int dessert_count = 0;

	    for (MenuItem item : items) {
	        switch (item.getCategory()) {
	            case STARTER:
	                starter_count++;
	                break;
	            case MAIN_DISH:
	                main_count++;
	                break;
	            case DESSERT:
	                dessert_count++;
	                break;
	        }
	    }

	    if ((starter_count == 1 && main_count == 1 && dessert_count == 0) ||
	            (starter_count == 0 && main_count == 1 && dessert_count == 1)) {
	        return "half-meal";
	    } else {
	        return "full_meal";
	    }
	}

	
}
