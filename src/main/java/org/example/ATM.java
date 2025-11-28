package org.example;

import org.example.Controller.AccountController;
import org.example.Model.Account;
import org.example.View.OptionMenu;

public class ATM  {
    // Main method: entry point of the program
    public static void main(String[] args) {
        Account account = new Account();
        AccountController accountController = new AccountController();

        OptionMenu options = new OptionMenu(account, accountController); // Creates an object of OptionMenu class
        options.getLogin(); // Calls the getLogin method to prompt user login
    }
}
