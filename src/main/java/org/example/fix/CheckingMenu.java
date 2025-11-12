package org.example.fix;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CheckingMenu extends BaseMenu {

    public CheckingMenu(Scanner input, DecimalFormat format, Account account) {
        super(input, format, account);
    }

    @Override protected String title() { return "Checking Account"; }

    @Override protected void onViewBalance() {
        System.out.println("Checking Balance: " + format.format(account.getCheckingBalance()));
    }

    @Override protected void onWithdraw() {
        double amt = promptAmount("Enter amount to withdraw: ");
        account.getCheckingWithdrawInput(amt);
    }

    @Override protected void onDeposit() {
        double amt = promptAmount("Enter amount to deposit: ");
        account.getCheckingDepositInput(amt);
    }
}