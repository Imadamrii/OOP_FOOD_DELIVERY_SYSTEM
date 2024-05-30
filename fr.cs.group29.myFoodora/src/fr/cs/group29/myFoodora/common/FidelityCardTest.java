package fr.cs.group29.myFoodora.common;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FidelityCardTest {
    private FidelityCard basicCard;
    private FidelityCard pointCard;
    private FidelityCard lotteryCard;

    @BeforeEach
    void setUp() {
        basicCard = new FidelityCard(FidelityCardType.BASIC);
        pointCard = new FidelityCard(FidelityCardType.POINT);
        lotteryCard = new FidelityCard(FidelityCardType.LOTTERY);
    }

    @Test
    void testInitialPoints() {
        assertEquals(0, basicCard.getPoints());
        assertEquals(0, pointCard.getPoints());
        assertEquals(0, lotteryCard.getPoints());
    }

    @Test
    void testSetAndGetPoints() {
        pointCard.setPoints(50);
        assertEquals(50, pointCard.getPoints());

        lotteryCard.setPoints(100);
        assertEquals(100, lotteryCard.getPoints());
    }

    @Test
    void testSetAndGetType() {
        assertEquals(FidelityCardType.BASIC, basicCard.getType());
        assertEquals(FidelityCardType.POINT, pointCard.getType());
        assertEquals(FidelityCardType.LOTTERY, lotteryCard.getType());

        basicCard.setType(FidelityCardType.POINT);
        assertEquals(FidelityCardType.POINT, basicCard.getType());
    }

    @Test
    void testGetDiscount() {
        // Test basic card
        assertEquals(0, basicCard.getDiscount());

        // Test point card with less than 100 points
        pointCard.setPoints(99);
        assertEquals(0, pointCard.getDiscount());

        // Test point card with 100 points
        pointCard.setPoints(100);
        assertEquals(0.1, pointCard.getDiscount());

        // Test lottery card
        int freeMeals = 0;
        int totalTests = 100000;
        for (int i = 0; i < totalTests; i++) {
            if (lotteryCard.getDiscount() == 1) {
                freeMeals++;
            }
        }
        // Check if the probability is close to 0.1%
        double probability = (double) freeMeals / totalTests;
        assertTrue(probability > 0.0005 && probability < 0.0015);
    }

    @Test
    void testUpdatePoints() {
        pointCard.updatePoints(10);
        assertEquals(10, pointCard.getPoints());

        pointCard.updatePoints(15);
        assertEquals(25, pointCard.getPoints());

        lotteryCard.updatePoints(5);
        assertEquals(5, lotteryCard.getPoints());
    }
}
