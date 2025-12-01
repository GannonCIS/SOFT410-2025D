package org.example.controller;

import org.example.model.Account;
import org.example.view.OptionMenu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginHandlerTest {

    @Test
    void testSuccessfulLogin() {
        Account account = new Account();
        account.setCustomerNumber(123);
        account.setPin(123);

        OptionMenu optionMenu = mock(OptionMenu.class);
        when(optionMenu.promptCustomerNumber()).thenReturn(123);
        when(optionMenu.promptPin()).thenReturn(123);

        LoginHandler handler = new LoginHandler(account, optionMenu);
        assertTrue(handler.login());
    }

    @Test
    void testFailedLogin() {
        Account account = new Account();
        account.setCustomerNumber(123);
        account.setPin(999);

        OptionMenu optionMenu = mock(OptionMenu.class);
        when(optionMenu.promptCustomerNumber()).thenReturn(123);
        when(optionMenu.promptPin()).thenReturn(888);

        LoginHandler handler = new LoginHandler(account, optionMenu);
        assertFalse(handler.login());
    }
}