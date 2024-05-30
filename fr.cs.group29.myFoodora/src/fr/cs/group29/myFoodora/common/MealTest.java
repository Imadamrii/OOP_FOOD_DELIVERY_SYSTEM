package fr.cs.group29.myFoodora.common;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class MealTest {

    @Test
    public void testGetPrice_regularPrice() {
        // Test case for a meal without special offer
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Caesar Salad", 8.0, MenuItemCategory.STARTER, MenuItemType.STANDARD));
        items.add(new MenuItem("Grilled Chicken", 16.0, MenuItemCategory.MAIN_DISH, MenuItemType.STANDARD));
        Meal meal = new Meal(false, "Regular Price Meal", items);

        assertEquals(24.0, meal.getPrice(), 0.001); // Delta is set to handle double precision issues
    }

    @Test
    public void testGetPrice_specialOffer() {
        // Test case for a meal with special offer
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Soup", 5.0, MenuItemCategory.STARTER, MenuItemType.STANDARD));
        items.add(new MenuItem("Steak", 20.0, MenuItemCategory.MAIN_DISH, MenuItemType.STANDARD));
        Meal meal = new Meal(true, "Special Offer Meal", items);

        // Total price after 5% discount
        double expectedPrice = (5.0 + 20.0) * (1 - 0.05);
        assertEquals(expectedPrice, meal.getPrice(), 0.001);
    }

    @Test
    public void testGetType_standardMeal() {
        // Test case for a meal with only standard items
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Chicken Soup", 6.0, MenuItemCategory.STARTER, MenuItemType.STANDARD));
        items.add(new MenuItem("Grilled Salmon", 18.0, MenuItemCategory.MAIN_DISH, MenuItemType.STANDARD));
        Meal meal = new Meal(false, "Standard Meal", items);

        assertEquals(MenuItemType.STANDARD, meal.getType());
    }

    @Test
    public void testGetType_vegetarianMeal() {
        // Test case for a meal with only vegetarian items
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Caesar Salad", 8.0, MenuItemCategory.STARTER, MenuItemType.VEGETARIAN));
        items.add(new MenuItem("Vegetable Stir-Fry", 14.0, MenuItemCategory.MAIN_DISH, MenuItemType.VEGETARIAN));
        Meal meal = new Meal(false, "Vegetarian Meal", items);

        assertEquals(MenuItemType.VEGETARIAN, meal.getType());
    }

    @Test
    public void testGetMealSize_fullMeal() {
        // Test case for a full meal with starter, main dish, and dessert
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Caesar Salad", 8.0, MenuItemCategory.STARTER, MenuItemType.STANDARD));
        items.add(new MenuItem("Grilled Chicken", 16.0, MenuItemCategory.MAIN_DISH, MenuItemType.STANDARD));
        items.add(new MenuItem("Chocolate Cake", 7.0, MenuItemCategory.DESSERT, MenuItemType.STANDARD));
        Meal meal = new Meal(false, "Full Meal", items);

        assertEquals("full_meal", meal.getMealSize());
    }

    @Test
    public void testGetMealSize_halfMeal() {
        // Test case for a half meal with either a starter and main dish or a main dish and dessert
        List<MenuItem> items1 = new ArrayList<>();
        items1.add(new MenuItem("Soup", 5.0, MenuItemCategory.STARTER, MenuItemType.STANDARD));
        items1.add(new MenuItem("Steak", 20.0, MenuItemCategory.MAIN_DISH, MenuItemType.STANDARD));
        Meal meal1 = new Meal(false, "Half Meal 1", items1);

        List<MenuItem> items2 = new ArrayList<>();
        items2.add(new MenuItem("Fish", 18.0, MenuItemCategory.MAIN_DISH, MenuItemType.STANDARD));
        items2.add(new MenuItem("Ice Cream", 4.0, MenuItemCategory.DESSERT, MenuItemType.STANDARD));
        Meal meal2 = new Meal(false, "Half Meal 2", items2);

        assertEquals("half-meal", meal1.getMealSize());
        assertEquals("half-meal", meal2.getMealSize());
    }
}

