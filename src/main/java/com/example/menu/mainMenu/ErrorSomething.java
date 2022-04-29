package com.example.menu.mainMenu;

import com.example.menu.adminMenu.LoginForAdmin;
import com.example.menu.userMenu.LoginMenu;

import javax.swing.*;
import java.awt.event.*;

public class ErrorSomething extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    public static String type;


    public ErrorSomething() {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

    }

    private void onOK() {
        // add your code here
        dispose();
        if (type.equals("Admin")) {
            LoginForAdmin dialog = new LoginForAdmin();
            dialog.pack();
            dialog.setVisible(true);
        } else {
            LoginMenu dialog = new LoginMenu();
            dialog.pack();
            dialog.setVisible(true);
        }

    }

}
