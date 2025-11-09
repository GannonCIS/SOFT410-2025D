package org.example.fix;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MenuContext {
    public final Scanner input;
    public final DecimalFormat format;
    public final Account account;

    public MenuContext(Scanner input, DecimalFormat format, Account account) {
        this.input = input;
        this.format = format;
        this.account = account;
    }
}