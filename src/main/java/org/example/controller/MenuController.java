package org.example.controller;

import org.example.model.Account;
import org.example.model.AccountType;
import org.example.model.Money;
import org.example.view.BaseMenu;
import org.example.view.OptionMenu;

import java.util.HashMap;
import java.util.Map;

public class MenuController {

    private final Account account;
    private final AccountController accountController;
    private final OptionMenu optionView;

    private final Map<Integer, BaseMenu> accountMenuRegister = new HashMap<>();

    public MenuController(Account account,
                          AccountController accountController,
                          OptionMenu optionView,
                          Map<Integer, BaseMenu> menus) {
        this.account = account;
        this.accountController = accountController;
        this.optionView = optionView;

        this.accountMenuRegister.putAll(menus);
    }

    public void runAccountTypeLoop() {
        while (true) {
            int selection = optionView.promptAccountType();

            if (selection == 3) {
                optionView.showGoodbye();
                System.exit(0);
            }

            BaseMenu menu = accountMenuRegister.get(selection);
            if (menu == null) {
                optionView.showInvalidAccountTypeChoice();
                continue;
            }

            AccountType type = (selection == 1)
                    ? AccountType.CHECKING
                    : AccountType.SAVING;

            BaseMenu curMenu = (selection == 1)
                    ? this.accountMenuRegister.get(1)
                    : this.accountMenuRegister.get(2);

            Map<Integer, Runnable> actions =
                    buildActionsForMenu(menu, type, this::runAccountTypeLoop);

            menu.show(actions);
        }
    }

    protected Map<Integer, Runnable> buildActionsForMenu(BaseMenu menu,
                                                         AccountType type,
                                                         Runnable back) {
        Map<Integer, Runnable> actions = new HashMap<>();

        actions.put(1, () -> {
            Money balance = accountController.getBalance(account, type);
            menu.showBalance(balance.toString());
        });

        actions.put(2, () -> {
            double amount = menu.promptWithdraw();
            try {
                accountController.withdraw(account, type, Money.of(amount));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        });

        actions.put(3, () -> {
            double amount = menu.promptDeposit();
            try {
                accountController.deposit(account, type, Money.of(amount));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        });

        actions.put(4, back);

        return actions;
    }

    public void registerMenu(int id, BaseMenu menu) {
        if (accountMenuRegister.containsKey(id)) {
            throw new IllegalArgumentException("Menu id " + id + " is already registered!");
        }
        accountMenuRegister.put(id, menu);
    }
}