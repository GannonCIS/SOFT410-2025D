package org.example.View;

import org.example.Model.Account;

import java.text.DecimalFormat;
import java.util.Scanner;

public class SavingMenu extends BaseMenu {

    public SavingMenu(Scanner input, DecimalFormat format, Account account) {
        super(input, format, account);
    }

    @Override protected String title() { return "Savings Account"; }

    @Override protected void onViewBalance() {
        System.out.println("Saving Balance: " + format.format(account.getSavingBalance()));
    }

    @Override protected void onWithdraw() {
        double amount = promptAmount("Enter amount to withdraw: ");
        account.getSavingWithdrawInput(amount);
    }

    @Override protected void onDeposit() {
        double amount = promptAmount("Enter amount to deposit: ");
        account.getSavingDepositInput(amount);
    }
}