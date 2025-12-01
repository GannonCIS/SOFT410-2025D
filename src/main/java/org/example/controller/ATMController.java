package org.example.controller;

import org.example.model.Account;
import org.example.model.AccountType;
import org.example.model.Money;
import org.example.view.BaseMenu;
import org.example.view.OptionMenu;

import java.util.Map;
import java.util.Scanner;

public class ATMController {

    private final Account account;
    private final OptionMenu optionMenu;
    private final Map<Integer, BaseMenu> menus;

    private final LoginHandler loginHandler;
    private final MenuController menuController;

    public ATMController(
            Account account,
            OptionMenu optionMenu,
            Map<Integer, BaseMenu> menus
    ) {
        this.account = account;
        this.account.setBalance(AccountType.CHECKING, Money.of(1000));
        this.account.setBalance(AccountType.SAVING, Money.of(500));
        this.optionMenu = optionMenu;
        this.menus = menus;

        AccountController accountController = new AccountController();

        this.loginHandler = new LoginHandler(account, optionMenu);

        this.menuController = new MenuController(
                account,
                accountController,
                optionMenu,
                menus
        );
    }

    public void start() {
        if (loginHandler.login()) {
            menuController.runAccountTypeLoop();
        }
    }
}