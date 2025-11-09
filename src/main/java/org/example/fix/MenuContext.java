package org.example.fix;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MenuContext {
    private final Scanner input;
    private final DecimalFormat format;
    private final Account account;

    public MenuContext(Scanner input, DecimalFormat format, Account account) {
        this.input = input;
        this.format = format;
        this.account = account;
    }

    public Scanner getInput() {
        return input;
    }

    public DecimalFormat getDecimalFormat() {
        return format;
    }

    public Account getAccount() {
        return account;
    }

}