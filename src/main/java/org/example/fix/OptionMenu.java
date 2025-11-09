package org.example.fix;

import java.text.DecimalFormat; // Import for formatting balance outputs as currency
import java.util.HashMap; // Import for storing customer number and PIN pairs
import java.util.Map;
import java.util.Scanner; // Import for capturing user input from the console
import java.util.function.Supplier;

public class OptionMenu {
    private final Account account;

    private final Map<Integer, Supplier<AccountTypeMenu>> accountMenuRegister = Map.of(
            1, CheckingMenu::new,
            2, SavingMenu::new
    );

    public OptionMenu(Account account) {
        this.account = account;
    }


    Scanner menuInput = new Scanner(System.in); // Scanner object for reading user input
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00"); // Currency formatter

    HashMap<Integer, Integer> data = new HashMap<>(); // Stores customer number as key, PIN as value

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
        MenuContext context = new MenuContext(menuInput, moneyFormat, account);

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

            Supplier<AccountTypeMenu> mockFactory = accountMenuRegister.get(selection);

            if (mockFactory == null) {
                System.out.println("\nInvalid Choice\n");
                continue;
            }

            AccountTypeMenu typeMenu = mockFactory.get();
            typeMenu.show(context, this::getAccountType);
        }
    }
}

