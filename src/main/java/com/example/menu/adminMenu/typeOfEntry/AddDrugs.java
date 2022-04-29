package com.example.menu.adminMenu.typeOfEntry;

import com.example.backend.drugmanager.Drug;
import com.example.backend.container.ComponentContainer;
import com.example.menu.adminMenu.WorkingAdmin;

import javax.swing.*;
import java.awt.event.*;

public class AddDrugs extends JDialog {
    private JPanel contentPane;
    private JTextField nameOfDrug;
    private JTextField doseOfDrug;
    private JTextField manufacturedOfDrug;
    private JTextField costOfDrug;
    private JTextField amountOfDrug;
    private JButton addButton;
    private JComboBox typeOfDrug;
    private JButton backButton;
    private JLabel actionOfAdd;


    public AddDrugs() {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(backButton);


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


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Drug drug = new Drug();
                System.out.println(costOfDrug.getText());
                try {

                    drug.setName(nameOfDrug.getText());
                    drug.setType((String) typeOfDrug.getSelectedItem());
                    drug.setDose(doseOfDrug.getText());
                    drug.setManufactured(manufacturedOfDrug.getText());
                    drug.setCost(Integer.valueOf(costOfDrug.getText()));
                    drug.setAmount(Integer.valueOf(amountOfDrug.getText()));
                } catch (Exception exception) {
                    costOfDrug.setText("");
                    amountOfDrug.setText("");
                    drug = null;
                }
                if (drug != null) {
                    ComponentContainer.drugController.addDrugs(drug);
                    actionOfAdd.setText("*** Drug added! ***");
                } else {
                    actionOfAdd.setText("*** There were something wrong ***");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }


    private void onOK() {
        dispose();
    }


    private void onCancel() {
        dispose();
        WorkingAdmin dialog = new WorkingAdmin();
        dialog.pack();
        dialog.setVisible(true);

    }

}
