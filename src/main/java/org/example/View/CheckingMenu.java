package org.example.View;

import org.example.Controller.AccountController;
import org.example.Model.*;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CheckingMenu extends BaseMenu {
    private final AccountController accountController;
    private AccountType accountType = AccountType.CHECKING;

    public CheckingMenu(Scanner input, DecimalFormat format, Account account, AccountController accountController) {
        super(input, format, account);
        this.accountController = accountController;
    }

    @Override protected String title() { return "Checking Account"; }

    @Override protected void onViewBalance() {
        // System.out.println("Checking Balance: " + format.format(account.getCheckingBalance()));
        Money balance = accountController.getBalance(account, accountType);
        System.out.println("Checking Balance: " + format.format(balance.toString()));
    }

    @Override protected void onWithdraw() {
        double amt = promptAmount("Enter amount to withdraw: ");
        accountController.withdraw(account, accountType, Money.of(amt));
    }

    @Override protected void onDeposit() {
        double amt = promptAmount("Enter amount to deposit: ");
        accountController.deposit(account, AccountType.CHECKING, Money.of(amt));
    }
}