package org.example;

import org.example.controller.ATMController;
import org.example.model.Account;
import org.example.view.BaseMenu;
import org.example.view.CheckingMenu;
import org.example.view.OptionMenu;
import org.example.view.SavingMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Account accountModel = new Account();

        OptionMenu optionMenuView = new OptionMenu(input);
        BaseMenu checkingMenuView = new CheckingMenu(input, accountModel);
        BaseMenu savingMenuView = new SavingMenu(input, accountModel);

        Map<Integer, BaseMenu> menus = new HashMap<>();
        menus.put(1, checkingMenuView);
        menus.put(2, savingMenuView);


        ATMController controller = new ATMController(accountModel, optionMenuView, menus);
        controller.start();
    }
}