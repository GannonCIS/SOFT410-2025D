package org.example.fix;

import java.util.Map;
import java.util.Scanner;

public interface AccountTypeMenu {
    void printInitialOption();
    Map<Integer, Runnable> registerActions(MenuContext context, Runnable back);
    void show(MenuContext context, Runnable back );
    double promptAmount(Scanner input, String prompt);

}
