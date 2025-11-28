package org.example.View;

import org.example.Model.Account;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class BaseMenu {
    protected final Scanner input;
    protected final DecimalFormat format;
    protected final Account account;

    protected BaseMenu(Scanner input, DecimalFormat format, Account account) {
        this.input = input;
        this.format = format;
        this.account = account;
    }

    public final void show(Runnable back) {
        Map<Integer, Runnable> actions = registerActions(back);

        while (true) {
            printBaseOptions(title());
            int selection = readInt();
            Runnable action = actions.get(selection);
            if (action != null) {
                action.run();
                break;
            } else {
                System.out.println("\nInvalid Choice. Please try again!\n");
            }
        }
    }

    protected Map<Integer, Runnable> registerActions(Runnable back) {
        Map<Integer, Runnable> actions = new HashMap<>();
        actions.put(1, () -> { onViewBalance(); back.run(); });
        actions.put(2, () -> { onWithdraw(); back.run(); });
        actions.put(3, () -> { onDeposit(); back.run(); });
        actions.put(4, back);
        return actions;
    }

    protected void printBaseOptions(String title) {
        System.out.println("\n" + title);
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Money");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");
    }

    protected int readInt() {
        while (!input.hasNextInt()) {
            System.out.println("Please enter a valid number!");
            input.next();
            System.out.print("Choice: ");
        }
        return input.nextInt();
    }

    protected double promptAmount(String prompt) {
        System.out.print(prompt);
        while (!input.hasNextDouble()) {
            System.out.println("Please enter a valid number!");
            input.next();
            System.out.print(prompt);
        }
        return input.nextDouble();
    }

    protected abstract String title();
    protected abstract void onViewBalance();
    protected abstract void onWithdraw();
    protected abstract void onDeposit();
}