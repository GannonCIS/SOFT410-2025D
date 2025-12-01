package org.example.model;

import java.math.BigDecimal;

public final class Money {

    private static final int SCALE = 2;

    private final BigDecimal value;

    private Money(BigDecimal value) {
        this.value = value.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public static Money of(String amount) {
        return new Money(new BigDecimal(amount));
    }

    public static Money of(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money fromCents(long cents) {
        return new Money(new BigDecimal(cents).movePointLeft(2));
    }

    public Money add(Money other) {
        return new Money(this.value.add(other.value));
    }

    public Money subtract(Money other) {
        return new Money(this.value.subtract(other.value));
    }

    public boolean isNegative() {
        return value.signum() < 0;
    }

    public int compareTo(Money other) {
        return this.value.compareTo(other.value);
    }

    public BigDecimal asBigDecimal() {
        return value;
    }

    @Override
    public String toString() {
        return "$" + value.toPlainString();
    }
}