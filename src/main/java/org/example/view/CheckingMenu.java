package org.example.view;

import org.example.model.Account;
import org.example.model.AccountObserver;
import org.example.model.AccountType;
import org.example.model.Money;

import java.util.Scanner;

public class CheckingMenu extends BaseMenu implements AccountObserver {

    private final Account account;
    private final AccountType type = AccountType.CHECKING;

    public CheckingMenu(Scanner input, Account account) {
        super(input);
        this.account = account;
        this.account.addObserver(this);
    }

    @Override
    public String title() {
        return "Checking Account";
    }

    @Override
    public void showBalance(String balanceString) {
        System.out.println("Checking Balance: " + balanceString);
    }

    @Override
    public double promptWithdraw() {
        return promptAmount("Enter amount to withdraw from Checking: ");
    }

    @Override
    public double promptDeposit() {
        return promptAmount("Enter amount to deposit to Checking: ");
    }

    @Override
    public void onBalanceChanged(Account account, AccountType type, Money newBalance) {
        if (account == this.account && type == this.type) {
            System.out.println("\nBalance updated: " + newBalance);
        }
    }
}