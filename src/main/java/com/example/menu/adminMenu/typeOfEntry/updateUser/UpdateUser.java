package com.example.menu.adminMenu.typeOfEntry.updateUser;

import com.example.menu.adminMenu.WorkingAdmin;

import javax.swing.*;
import java.awt.event.*;

public class UpdateUser extends JDialog {
    private JPanel contentPane;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton backButton;

    public UpdateUser() {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonAdd);

        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onDelete();
            }
        });

        // call onDelete() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onDelete() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onDelete();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        });
    }

    private void onBack() {
        dispose();
        WorkingAdmin dialog = new WorkingAdmin();
        dialog.pack();
        dialog.setVisible(true);

    }

    private void onAdd() {
        dispose();
        RegistrationMenu dialog = new RegistrationMenu();
        dialog.pack();
        dialog.setVisible(true);

    }

    private void onDelete() {
        // add your code here if necessary
        dispose();
        RemoveUser dialog = new RemoveUser();
        dialog.pack();
        dialog.setVisible(true);
    }

}
