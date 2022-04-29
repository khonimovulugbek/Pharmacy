package com.example.menu.adminMenu.typeOfEntry.updateUser;

import com.example.backend.container.ComponentContainer;
import com.example.backend.usermanager.User;

import javax.swing.*;
import java.awt.event.*;

public class RegistrationMenu extends JDialog {
    private JPanel contentPane;
    private JButton buttonAdd;
    private JButton buttonCancel;
    private JTextField email;
    private JTextField surname;
    private JTextField middlename;
    private JTextField phone_number;
    private JTextField address;
    private JTextField username;
    private JTextField password;
    private JPanel nameOfuser;
    private JLabel Jname;
    private JLabel Jsurname;
    private JLabel emailUser;
    private JTextField nameOfUser;
    private JLabel infoAdd;

    public RegistrationMenu() {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonAdd);

        buttonAdd.addActionListener(new ActionListener() {
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
    }

    private void onOK() {
        User user = new User();
        try {
            user.setName(nameOfUser.getText());
            user.setSurname(surname.getText());
            user.setMiddlename(middlename.getText());
            user.setAddress(address.getText());
            user.setEmail(email.getText());
            user.setPhone_number(phone_number.getText());
            user.setUserName(username.getText());
            user.setPassword(password.getText());

        } catch (Exception e) {
            user = null;
        }
        if (user != null) {
            ComponentContainer.userController.addUser(user);
            infoAdd.setText("User added");
        } else {
            infoAdd.setText("There were something wrong");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
        UpdateUser dialog = new UpdateUser();
        dialog.pack();
        dialog.setVisible(true);
    }

}
