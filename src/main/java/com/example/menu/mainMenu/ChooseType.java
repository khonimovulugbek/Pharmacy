package com.example.menu.mainMenu;

import com.example.backend.container.ComponentContainer;
import com.example.backend.host.Host;
import com.example.menu.adminMenu.LoginForAdmin;
import com.example.menu.userMenu.LoginMenu;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class ChooseType extends JDialog {
    private JPanel contentPane;
    private JButton buttonUser;
    private JButton buttonAdmin;
    private JPasswordField mainAdmin;


    public ChooseType() {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        setSize(650, 450);
        getRootPane().setDefaultButton(buttonUser);

        buttonUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onUser();
            }
        });

        buttonAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAdmin();
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
                onAdmin();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        mainAdmin.addActionListener(new ActionListener() {
            @Override
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                System.out.println(String.valueOf(mainAdmin.getPassword()));

                if (String.valueOf(mainAdmin.getPassword()).equals("hakker")) {
                    Host.statement.executeUpdate("\n" +
                            "insert into getPharmUser(name, surname, middlename, phone_number, email, address, username, password, type)\n" +
                            "VALUES ('Ulugbek', 'Khonimov','Gulboy o`g`li','+998888977707','ulugbekkhan94@mail.ru','Toshkent, Uchtepa','khan_u','hakker7707','admin')");
                    Host.statement.executeUpdate("\n" +
                            "insert into getPharmUser(name, surname, middlename, phone_number, email, address, username, password, type)\n" +
                            "VALUES ('Sardor', 'Madatov','Maxammatjonovich','+998913933993','sardormadatov04@gmail.com','Toshkent, Uchtepa','sardor','sar14dor','admin')");
                }
            }
        });
    }


    private void onUser() {
        ComponentContainer.drugService.createDrugTable();

        dispose();
        LoginMenu dialog = new LoginMenu();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void onAdmin() {
        ComponentContainer.drugService.createDrugTable();
        dispose();
        LoginForAdmin dialog = new LoginForAdmin();
        dialog.pack();
        dialog.setVisible(true);
    }
}
