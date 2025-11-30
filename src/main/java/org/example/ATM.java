package org.example;

import org.example.Controller.ATMController;
import org.example.Model.Account;
import org.example.View.BaseMenu;
import org.example.View.CheckingMenu;
import org.example.View.OptionMenu;
import org.example.View.SavingMenu;

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