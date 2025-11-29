package org.example.Controller;

import org.example.Model.*;

public class AccountController {

    public Money getBalance(Account account, AccountType type) {
        return account.getBalance(type);
    }

    public void deposit(Account account, AccountType type, Money amount) {
        if (amount.isNegative() || amount.compareTo(Money.of(0)) == 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Money newBalance = account.getBalance(type).add(amount);
        account.setBalance(type, newBalance);
    }

    public void withdraw(Account account, AccountType type, Money amount) {
        if (amount.isNegative() || amount.compareTo(Money.of(0)) == 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Money newBalance = account.getBalance(type).subtract(amount);
        if (newBalance.isNegative()) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.setBalance(type, newBalance);
    }
}