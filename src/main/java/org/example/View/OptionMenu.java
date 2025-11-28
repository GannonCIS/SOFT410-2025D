package org.example.View;

import org.example.Controller.AccountController;
import org.example.Model.Account;

import java.text.DecimalFormat; // Import for formatting balance outputs as currency
import java.util.HashMap; // Import for storing customer number and PIN pairs
import java.util.Map;
import java.util.Scanner; // Import for capturing user input from the console
import java.util.function.Supplier;

public class OptionMenu {
    private final Account account;
    private final AccountController accountController;

    private final Map<Integer, Supplier<BaseMenu>> accountMenuRegister = new HashMap<>();

    Scanner menuInput = new Scanner(System.in); // Scanner object for reading user input
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00"); // Currency formatter

    HashMap<Integer, Integer> data = new HashMap<>(); // Stores customer number as key, PIN as value

    public OptionMenu(Account account, AccountController accountController) {
        this.account = account;
        this.accountController = accountController;

        accountMenuRegister.put(1, () -> new CheckingMenu(menuInput, moneyFormat, account, accountController));
        accountMenuRegister.put(2, () -> new SavingMenu(menuInput, moneyFormat, account));
    }


    // Handles login process
    public void getLogin() {
        int x = 1;
        do {
            try {
                // Predefined dummy accounts
                data.put(123, 123);
                data.put(456, 456);

                System.out.println("Welcome to ATM");
                System.out.println("Enter your Customer Number");
                account.setCustomerNumber(menuInput.nextInt()); // Reads and sets customer number

                System.out.println("Enter your PIN Number");
                account.setPinNumber(menuInput.nextInt()); // Reads and sets PIN
            }
            catch(Exception e) {
                // Handles non-numeric input
                System.out.println("\nInvalid Characters Only Numbers Allowed\n" + e);
                x = 2; // Prevents loop continuation on error
            }

            int cn = account.getCustomerNumber();
            int pn = account.getPinNumber();

            // Validates login credentials
            if (data.containsKey(cn) && data.get(cn) == pn) {
                getAccountType(); // Proceed to account menu
            }
            else {
                System.out.println("\nWrong Customer Number or Wrong PIN Number\n\n");
            }
        } while (x == 1); // Loop continues until valid login or exception
    }

    // Displays account type options
    public void getAccountType() {
        while (true) {
            System.out.println("\nSelect Account Type you want to Access");
            System.out.println("Type 1 - Checking Account");
            System.out.println("Type 2 - Savings Account");
            System.out.println("Type 3 - Exit");
            System.out.print("Choice: ");

            int selection = menuInput.nextInt();

            if (selection == 3) {
                System.out.println("Thank you for using ATM, BYE");
                System.exit(0);
            }

            Supplier<BaseMenu> chosenMenu = accountMenuRegister.get(selection);

            if (chosenMenu == null) {
                System.out.println("\nInvalid Choice\n");
                continue;
            }

            BaseMenu typeMenu = chosenMenu.get();
            typeMenu.show(this::getAccountType);
        }
    }

    public void registerMenu(int id, Supplier<BaseMenu> menu) {
        if (accountMenuRegister.containsKey(id)) {
            throw new IllegalArgumentException("Menu id " + id + " is already registered!");
        }
        accountMenuRegister.put(id, menu);
    }
}

