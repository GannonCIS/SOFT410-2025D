package org.example.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Account {

    private final EnumMap<AccountType, Money> balances = new EnumMap<>(AccountType.class);
    private final List<AccountObserver> observers = new ArrayList<>();

    private int customerNumber;
    private int pin;

    public Account() {
        for (AccountType type : AccountType.values()) {
            balances.put(type, Money.of(0));
        }
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }


    public void addObserver(AccountObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(AccountObserver observer) {
        observers.remove(observer);
    }

    private void notifyBalanceChanged(AccountType type, Money newAmount) {
        for (AccountObserver listener : observers) {
            listener.onBalanceChanged(this, type, newAmount);
        }
    }


    public Money getBalance(AccountType type) {
        return balances.get(type);
    }

    public void setBalance(AccountType type, Money newAmount) {
        balances.put(type, newAmount);
        notifyBalanceChanged(type, newAmount);
    }

    public void deposit(AccountType type, Money amount) {
        if (amount.isNegative() || amount.compareTo(Money.of(0)) == 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Money newBalance = getBalance(type).add(amount);
        setBalance(type, newBalance);
    }

    public void withdraw(AccountType type, Money amount) {
        if (amount.isNegative() || amount.compareTo(Money.of(0)) == 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Money newBalance = getBalance(type).subtract(amount);
        if (newBalance.isNegative()) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        setBalance(type, newBalance);
    }
}