package org.example.Model;

import java.util.EnumMap;

public class Account {

    private EnumMap<AccountType, Money> balances;

    public Account() {
        // this.customerNumber = customerNumber;
        // this.pin = pin;

        balances = new EnumMap<>(AccountType.class);
        balances.put(AccountType.CHECKING, Money.of(0));
        balances.put(AccountType.SAVING, Money.of(0));
    }

    /** public int getCustomerNumber() {
        return customerNumber;
    }

    public int getPin() {
        return pin;
    } */

    public Money getBalance(AccountType type) {
        return balances.get(type);
    }

    public void setBalance(AccountType type, Money newAmount) {
        balances.put(type, newAmount);
    }
}