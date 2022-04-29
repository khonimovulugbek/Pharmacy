package com.example;

import com.example.backend.container.ComponentContainer;
import com.example.menu.mainMenu.ChooseType;

public class Main {
    public static void main(String[] args) {
        ComponentContainer.userService.createUserTable();
        ChooseType dialog = new ChooseType();
        dialog.pack();
        dialog.setVisible(true);
    }
}
