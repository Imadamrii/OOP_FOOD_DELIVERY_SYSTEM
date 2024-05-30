package fr.cs.group29.myFoodora.common;

public class MenuItem {
	private String name;
	private double price;
	private MenuItemCategory category;
	private MenuItemType type;
	
	
	
	public MenuItem(String name, double price, MenuItemCategory category, MenuItemType type) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public MenuItemCategory getCategory() {
		return category;
	}

	public MenuItemType getType() {
		return type;
	}

	
	public  double getPrice() {
		return this.price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
}
