package org.example.model;

public interface AccountObserver {
    void onBalanceChanged(Account account, AccountType type, Money newBalance);
}