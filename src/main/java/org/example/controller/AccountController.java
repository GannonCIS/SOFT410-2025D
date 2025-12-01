package org.example.controller;

import org.example.model.*;

public class AccountController {

    public Money getBalance(Account account, AccountType type) {
        return account.getBalance(type);
    }

    public void deposit(Account account, AccountType type, Money amount) {
        account.deposit(type, amount);
    }

    public void withdraw(Account account, AccountType type, Money amount) {
        account.withdraw(type, amount);
    }
}