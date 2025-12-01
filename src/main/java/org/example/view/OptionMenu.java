package org.example.view;

import java.util.Scanner;

public class OptionMenu {

    private final Scanner input;

    public OptionMenu(Scanner input) {
        this.input = input;
    }

    public void showWelcome() {
        System.out.println("Welcome to ATM!");
    }

    public int promptCustomerNumber() {
        System.out.println("\nEnter your Customer Number");
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Numbers only.");
            input.next();
            System.out.println("\nEnter your Customer Number");
        }
        return input.nextInt();
    }

    public int promptPin() {
        System.out.println("\nEnter your PIN Number");
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Numbers only.");
            input.next();
            System.out.println("\nEnter your PIN Number");
        }
        return input.nextInt();
    }

    public void showLoginSuccess() {
        System.out.println("\nLogin successful.\n");
    }

    public void showLoginFailure() {
        System.out.println("\nWrong Customer Number or Wrong PIN Number\n");
        System.exit(0);
    }

    public void showInvalidInputError(Exception e) {
        System.out.println("\nInvalid Characters Only Numbers Allowed\n" + e);
    }

    public int promptAccountType() {
        System.out.println("\nSelect Account Type you want to Access");
        System.out.println("Type 1 - Checking Account");
        System.out.println("Type 2 - Savings Account");
        System.out.println("Type 3 - Exit");
        System.out.print("Choice: ");

        while (!input.hasNextInt()) {
            System.out.println("Please enter a valid number!");
            input.next();
            System.out.print("Choice: \n");
        }
        return input.nextInt();
    }

    public void showInvalidAccountTypeChoice() {
        System.out.println("\nInvalid Choice\n");
    }

    public void showGoodbye() {
        System.out.println("Thank you for using ATM, BYE!");
    }
}