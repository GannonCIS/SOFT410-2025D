package org.example.controller;

import org.example.model.Account;
import org.example.view.OptionMenu;

import java.util.HashMap;
import java.util.Map;

public class LoginHandler {

    private static final int MAX_ATTEMPTS = 3;

    private final Map<Integer, Integer> credentials = new HashMap<>();
    private final Account account;
    private final OptionMenu view;

    public LoginHandler(Account account, OptionMenu view) {
        this.account = account;
        this.view = view;

        credentials.put(123, 123);
        credentials.put(456, 456);
    }

    public boolean login() {
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            try {
                view.showWelcome();
                int customerNumber = view.promptCustomerNumber();
                int pin = view.promptPin();

                if (authenticate(customerNumber, pin)) {
                    account.setCustomerNumber(customerNumber);
                    account.setPin(pin);
                    view.showLoginSuccess();
                    return true;
                } else {
                    view.showLoginFailure();
                }
            } catch (Exception e) {
                view.showInvalidInputError(e);
            }

            attempts++;
        }

        return false;
    }

    private boolean authenticate(int customerNumber, int pin) {
        return credentials.containsKey(customerNumber)
                && credentials.get(customerNumber) == pin;
    }
}