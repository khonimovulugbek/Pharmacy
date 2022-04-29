package com.example.menu.adminMenu.typeOfEntry.updateUser;

import com.example.backend.container.ComponentContainer;
import com.example.backend.usermanager.User;

import javax.swing.*;
import java.awt.event.*;

public class RemoveUser extends JDialog {
    private JPanel contentPane;
    private JButton buttonRemove;
    private JButton buttonCancel;
    private JComboBox userBox;
    private JLabel userInfoPane;


    public RemoveUser() {
        for (User user : ComponentContainer.userService.getUserList()) {
            if (!user.getType().equals("admin")) {
                userBox.addItem(user.getUserName());
            }

        }
/*        userBox.addItem("Ali");
        userBox.addItem("Vali");
        userBox.addItem("Gani");*/

        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonRemove);

        buttonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        userBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (User user : ComponentContainer.userService.getUserList()) {
                    if (user.getUserName().equals(String.valueOf(userBox.getSelectedItem()))) {
                        userInfoPane.setText(user.getName() + " " + user.getSurname() + " " + user.getPhone_number());
                    }
                }

            }
        });
    }

    private void onOK() {
        String userName = String.valueOf(userBox.getSelectedItem());
        ComponentContainer.userController.removeUser(userName);


    }

    private void onCancel() {
        dispose();
        UpdateUser dialog = new UpdateUser();
        dialog.pack();
        dialog.setVisible(true);

    }

}
