package org.example.View;

import java.util.Map;
import java.util.Scanner;

public abstract class BaseMenu {
    protected final Scanner input;

    protected BaseMenu(Scanner input) {
        this.input = input;
    }

    public final void show(Map<Integer, Runnable> actions) {
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

    public abstract String title();
    public abstract void showBalance(String balanceString);
    public abstract double promptWithdraw();
    public abstract double promptDeposit();
}