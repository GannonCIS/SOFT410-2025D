package org.example.Model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Account {

    private EnumMap<AccountType, Money> balances;
    private final List<AccountObserver> observers = new ArrayList<>();

    private int customerNumber;
    private int pin;

    public Account() {
        balances = new EnumMap<>(AccountType.class);
        balances.put(AccountType.CHECKING, Money.of(1000));
        balances.put(AccountType.SAVING, Money.of(500));
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
}