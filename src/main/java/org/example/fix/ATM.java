package org.example.fix;

public class ATM  {
    // Main method: entry point of the program
    public static void main(String[] args) {
        Account account = new Account();

        account.setCheckingBalance(1000.00);
        account.setSavingBalance(5000);

        OptionMenu options = new OptionMenu(account); // Creates an object of OptionMenu class
        options.getLogin(); // Calls the getLogin method to prompt user login
    }
}
