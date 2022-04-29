package com.example.menu.adminMenu;

import com.example.backend.container.ComponentContainer;
import com.example.menu.adminMenu.typeOfEntry.updateUser.RegistrationMenu;
import com.example.menu.mainMenu.ErrorSomething;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class LoginForAdmin extends JDialog {
    private JPanel loginPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField login;
    private JPasswordField password;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private boolean loginCheck;
    private boolean passwordCheck;

    public LoginForAdmin() {
        setContentPane(loginPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean check = ComponentContainer.userController.checkAdmin(login.getText(), String.valueOf(password.getPassword()));
                if (check) {
                    loginCheck = true;
                    passwordCheck = true;
                } else {

                    loginCheck = false;
                    passwordCheck = false;
                }
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });


        // call onCancel() on ESCAPE
        loginPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        loginPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });


    }


    private void onOK() {

        if (loginCheck && passwordCheck) {
            dispose();
            WorkingAdmin dialog = new WorkingAdmin();
            dialog.pack();
            dialog.setVisible(true);

        } else {
            dispose();
            ErrorSomething dialog = new ErrorSomething();
            ErrorSomething.type = "Admin";
            dialog.pack();
            dialog.setVisible(true);

        }
        //System.exit(0);


    }

    private void onCancel() {
        dispose();

    }

}
