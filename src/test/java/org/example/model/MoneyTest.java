package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTest {

    @Test
    void testAdd() {
        Money m1 = Money.of(100);
        Money m2 = Money.of(50);
        assertEquals(Money.of(150), m1.add(m2));
    }

    @Test
    void testSubtract() {
        Money m1 = Money.of(200);
        Money m2 = Money.of(50);
        assertEquals(Money.of(150), m1.subtract(m2));
    }

    @Test
    void testNegativeResult() {
        Money m1 = Money.of(50);
        Money m2 = Money.of(100);
        assertTrue(m1.subtract(m2).isNegative());
    }

    @Test
    void testCompare() {
        assertTrue(Money.of(100).compareTo(Money.of(50)) > 0);
        assertEquals(0, Money.of(100).compareTo(Money.of(100)));
    }
}