package org.example.controller;

import org.example.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountControllerTest {

    @Test
    void testDeposit() {
        Account account = new Account();
        account.setBalance(AccountType.CHECKING, Money.of(0));
        AccountController controller = new AccountController();

        controller.deposit(account, AccountType.CHECKING, Money.of(100));

        assertEquals(
                Money.of(100),
                account.getBalance(AccountType.CHECKING),
                "Deposit should increase checking balance to $100"
        );
    }

    @Test
    void testDepositRejectsNegative() {
        AccountController controller = new AccountController();
        Account account = new Account();
        account.setBalance(AccountType.CHECKING, Money.of(0));

        assertThrows(
                IllegalArgumentException.class,
                () -> controller.deposit(account, AccountType.CHECKING, Money.of(-10)),
                "Deposit of negative amount should throw exception"
        );
    }

    @Test
    void testWithdraw() {
        AccountController controller = new AccountController();
        Account account = new Account();
        account.setBalance(AccountType.SAVING, Money.of(200));

        controller.withdraw(account, AccountType.SAVING, Money.of(50));

        assertEquals(
                Money.of(150),
                account.getBalance(AccountType.SAVING),
                "Withdrawal of $50 from $200 should leave $150"
        );
    }

    @Test
    void testWithdrawInsufficientFunds() {
        AccountController controller = new AccountController();
        Account account = new Account();
        account.setBalance(AccountType.SAVING, Money.of(50));

        assertThrows(
                IllegalArgumentException.class,
                () -> controller.withdraw(account, AccountType.SAVING, Money.of(100)),
                "Withdrawing more than available should throw exception"
        );
    }
}