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
        OptionMenu optionMenu = mock(OptionMenu.class);

        when(optionMenu.promptCustomerNumber()).thenReturn(123);
        when(optionMenu.promptPin()).thenReturn(123);

        LoginHandler handler = new LoginHandler(account, optionMenu);

        boolean result = handler.login();

        assertTrue(result);
        assertEquals(123, account.getCustomerNumber());
        assertEquals(123, account.getPin());

        // Should only need one attempt on success
        verify(optionMenu, times(1)).promptCustomerNumber();
        verify(optionMenu, times(1)).promptPin();
        verify(optionMenu, times(1)).showLoginSuccess();
        verify(optionMenu, never()).showLoginFailure();
    }

    @Test
    void testFailedLogin() {
        Account account = new Account();
        OptionMenu optionMenu = mock(OptionMenu.class);

        when(optionMenu.promptCustomerNumber()).thenReturn(123);
        when(optionMenu.promptPin()).thenReturn(999);

        LoginHandler handler = new LoginHandler(account, optionMenu);

        boolean result = handler.login();

        assertFalse(result);

        verify(optionMenu, times(3)).promptCustomerNumber();
        verify(optionMenu, times(3)).promptPin();
        verify(optionMenu, never()).showLoginSuccess();
        verify(optionMenu, atLeastOnce()).showLoginFailure();
    }
}