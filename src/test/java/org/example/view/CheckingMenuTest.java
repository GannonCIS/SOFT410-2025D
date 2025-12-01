package org.example.view;

import org.example.model.Account;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.mockito.Mockito.*;

public class CheckingMenuTest {

    @Test
    void testShowBalance() {
        Scanner mockScanner = mock(Scanner.class);

        Account account = new Account();

        CheckingMenu menu = new CheckingMenu(mockScanner, account);

        menu.showBalance("$100");

    }
}