package org.example.Controller;

import org.example.Model.Account;
import org.example.View.OptionMenu;

import java.util.HashMap;
import java.util.Map;

public class LoginHandler {

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
        int x = 1;
        do {
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
                x = 2;
            }
        } while (x == 1);

        return false;
    }

    private boolean authenticate(int customerNumber, int pin) {
        return credentials.containsKey(customerNumber)
                && credentials.get(customerNumber) == pin;
    }
}