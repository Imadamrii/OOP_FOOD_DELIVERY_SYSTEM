package fr.cs.group29.myFoodora.users;

import java.util.List;
import fr.cs.group29.myFoodora.system.*;

public class Manager extends User {

	private double serviceFeePercentage;
	private double markupPercentage;
	private double deliveryCost;

	public Manager(String id, String name, String surname, String username, String email, String phone, String password) {
		super(id, name, surname, username, email, phone, password);
	}

	// Method to add a user to the system
	public void addUser(User user, List<User> users) {
		users.add(user);
	}

	// Method to remove a user from the system
	public void removeUser(User user, List<User> users) {
		users.remove(user);
	}

	// Method to activate a user
	public void activateUser(User user) {
		user.setActive(true);
	}

	// Method to deactivate a user
	public void deactivateUser(User user) {
		user.setActive(false);
	}

	// Method to change the service fee percentage
	public void setServiceFeePercentage(double serviceFeePercentage) {
		this.serviceFeePercentage = serviceFeePercentage;
	}

	// Method to change the markup percentage
	public void setMarkupPercentage(double markupPercentage) {
		this.markupPercentage = markupPercentage;
	}

	// Method to change the delivery cost
	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	// Method to compute total income over a time period
	public double computeTotalIncome(List<Order> orders) {
		double totalIncome = 0;
		for (Order order : orders) {
			totalIncome += order.getTotalPrice();
		}
		return totalIncome;
	}

	// Method to compute total profit over a time period
	public double computeTotalProfit(List<Order> orders) {
		double totalProfit = 0;
		for (Order order : orders) {
			double orderProfit = order.getTotalPrice() * (markupPercentage / 100);
			totalProfit += orderProfit;
		}
		return totalProfit;
	}

	// Method to compute average income per customer over a time period
	public double computeAverageIncomePerCustomer(List<Order> orders, List<Customer> customers) {
		double totalIncome = computeTotalIncome(orders);
		long activeCustomers = customers.stream().filter(customer -> customer.getOrders().size() > 0).count();
		return totalIncome / activeCustomers;
	}

	// Additional methods for determining the most/least selling restaurant, most/least active courier, etc., can be added similarly.

	// Example of determining the most selling restaurant
	public Restaurant getMostSellingRestaurant(List<Order> orders) {
		Map<Restaurant, Integer> restaurantSales = new HashMap<>();
		for (Order order : orders) {
			Restaurant restaurant = order.getRestaurant();
			restaurantSales.put(restaurant, restaurantSales.getOrDefault(restaurant, 0) + 1);
		}
		return restaurantSales.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null).getKey();
	}

	// Example of determining the most active courier
	public Courier getMostActiveCourier(List<Order> orders) {
		Map<Courier, Integer> courierDeliveries = new HashMap<>();
		for (Order order : orders) {
			Courier courier = order.getCourier();
			courierDeliveries.put(courier, courierDeliveries.getOrDefault(courier, 0) + 1);
		}
		return courierDeliveries.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null).getKey();
	}
}
