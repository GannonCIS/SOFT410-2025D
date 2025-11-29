package org.example.Model;

public interface AccountObserver {
    void onBalanceChanged(Account account, AccountType type, Money newBalance);
}