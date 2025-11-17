package org.example.fix;

import java.util.EnumMap;

public class BankAccount {
    private final EnumMap<AccountType, Money> balances = new EnumMap<>(AccountType.class);

    public BankAccount() {
        balances.put(AccountType.CHECKING, Money.of("0.00"));
        balances.put(AccountType.SAVING, Money.of("0.00"));
    }

    public Money getBalance(AccountType type) { return balances.get(type); }

    public void deposit(AccountType type, Money amount) {
        requirePositive(amount);
        balances.put(type, balances.get(type).add(amount));
    }

    public void withdraw(AccountType type, Money amount) {
        requirePositive(amount);
        Money next = balances.get(type).subtract(amount);
        if (next.isNegative()) throw new IllegalArgumentException("Insufficient funds");
        balances.put(type, next);
    }

    private void requirePositive(Money m) {
        if (m.compareTo(Money.of("0.00")) <= 0) throw new IllegalArgumentException("Amount must be > 0");
    }
}
