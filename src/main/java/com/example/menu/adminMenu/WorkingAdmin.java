package com.example.menu.adminMenu;

import com.example.menu.adminMenu.typeOfEntry.AddDrugs;
import com.example.menu.adminMenu.typeOfEntry.updateUser.UpdateUser;
import com.example.menu.mainMenu.ChooseType;

import javax.swing.*;
import java.awt.event.*;

public class WorkingAdmin extends JDialog {
    private JPanel contentPane;
    private JButton addDrugsButton;
    private JButton updateUserButton;
    private JButton backToMainMenuButton;

    public WorkingAdmin() {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(backToMainMenuButton);



        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        addDrugsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDrugs();
            }
        });

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }




    private void addDrugs(){
        dispose();
        AddDrugs dialog = new AddDrugs();
        dialog.pack();
        dialog.setVisible(true);
    }



    private void updateUser(){
        dispose();
        UpdateUser dialog = new UpdateUser();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void onCancel(){
        dispose();

        ChooseType dialog = new ChooseType();
        dialog.pack();
        dialog.setVisible(true);

    }

}
