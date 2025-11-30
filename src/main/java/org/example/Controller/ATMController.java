package org.example.Controller;

import org.example.Model.Account;
import org.example.Model.AccountType;
import org.example.Model.Money;
import org.example.View.BaseMenu;
import org.example.View.OptionMenu;

import java.text.DecimalFormat;
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