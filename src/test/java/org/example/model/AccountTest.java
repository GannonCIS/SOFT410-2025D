package org.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    @Test
    void testSetAndGetBalance() {
        Account account = new Account();
        account.setBalance(AccountType.CHECKING, Money.of(500));
        assertEquals(Money.of(500), account.getBalance(AccountType.CHECKING));
    }
}