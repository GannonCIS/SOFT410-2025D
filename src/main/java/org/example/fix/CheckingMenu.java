package org.example.fix;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CheckingMenu implements AccountTypeMenu {

    @Override
    public void printInitialOption() {
        System.out.println("\nChecking Account");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Money");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
    }

    @Override
    public void show(MenuContext context, Runnable back) {
        Scanner input = context.input;

        Map<Integer, Runnable> actions = registerActions(context, back);

        while (true) {
            printInitialOption();

            int selection = input.nextInt();

            Runnable action = actions.get(selection);

            if (action != null) {
                action.run();
                break;
            } else {
                System.out.println("\nInvalid Choice. Please try again!\n");
            }
        }
    }

    @Override
    public Map<Integer, Runnable> registerActions(MenuContext context, Runnable back) {
        Scanner input = context.input;
        DecimalFormat format = context.format;
        Account checkingAccount = context.account;

        Map<Integer, Runnable> actions = new HashMap<>();

        actions.put(1, () -> {
            System.out.println("Checking Account Balance: " + format.format(checkingAccount.getCheckingBalance()));
            back.run();
        });

        actions.put(2, () -> {
            double amount = promptAmount(input, "Enter amount to withdraw: ");
            checkingAccount.getCheckingWithdrawInput(amount);
            back.run();
        });

        actions.put(3, () -> {
            double amount = promptAmount(input, "Enter amount to deposit: ");
            checkingAccount.getCheckingDepositInput(amount);
            back.run();
        });

        actions.put(4, back);

        return actions;
    }

    @Override
    public double promptAmount(Scanner input, String prompt) {
        System.out.print(prompt);
        while (!input.hasNextDouble()) {
            System.out.println("Please enter a valid number!");
            input.next();
            System.out.print(prompt);
        }
        return input.nextDouble();
    }
}
