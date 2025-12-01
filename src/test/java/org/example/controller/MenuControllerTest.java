package org.example.controller;

import org.example.model.*;
import org.example.view.BaseMenu;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.mockito.Mockito.*;

public class MenuControllerTest {

    @Test
    void testBuildActionsForMenu() {
        AccountController accountController = new AccountController();
        Account account = new Account();
        BaseMenu menu = mock(BaseMenu.class);

        MenuController controller = new MenuController(
                account, accountController, mock(org.example.view.OptionMenu.class), Map.of()
        );

        Map<Integer, Runnable> actions =
                controller.buildActionsForMenu(menu, AccountType.CHECKING, () -> {});

        // Assert actions exist
        assert(actions.containsKey(1));
        assert(actions.containsKey(2));
        assert(actions.containsKey(3));
        assert(actions.containsKey(4));
    }
}
