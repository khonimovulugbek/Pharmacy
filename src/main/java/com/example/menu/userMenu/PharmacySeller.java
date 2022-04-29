package com.example.menu.userMenu;

import com.example.backend.container.ComponentContainer;
import com.example.backend.drugmanager.Drug;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class PharmacySeller extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton printButton;
    private JTextArea print;
    private JTextField nameOfDrug;
    private JSpinner countSpinner;
    private JRadioButton cardRadioButton;
    private JLabel infoDrugs;
    private JRadioButton cashRadio;
    private JButton AddButton;
    private String check;
    private List<Integer> add;
    private List<Drug> drugs;


    public PharmacySeller() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);

        buttonOK.addActionListener(new ActionListener() {
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        cardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardRadioButton.setSelected(true);
                cashRadio.setSelected(false);

            }
        });


        cashRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cashRadio.setSelected(true);
                cardRadioButton.setSelected(false);

                if (Integer.parseInt(String.valueOf(countSpinner.getValue())) < 0) {
                    int num = Integer.parseInt(String.valueOf(countSpinner.getValue()));
                }
            }
        });


        countSpinner.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println(Integer.parseInt(countSpinner.getValue().toString()));
                super.componentResized(e);

            }
        });


        nameOfDrug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = ComponentContainer.drugController.drugInfo(nameOfDrug.getText());
                infoDrugs.setText(text);

            }
        });


        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num;
                if (Integer.parseInt(String.valueOf(countSpinner.getValue())) < 0) {
                    num = Integer.parseInt(String.valueOf(countSpinner.getValue()));
                    num *= -1;
                } else {
                    num = Integer.parseInt(String.valueOf(countSpinner.getValue()));
                }

                ComponentContainer.drugController.addedDrugList(nameOfDrug.getText());
                ComponentContainer.drugController.addedCount(num);

            }
        });


        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComponentContainer.drugService.printcheque(drugs, add);

            }
        });
    }


    private void onOK() {
        add = ComponentContainer.drugController.getSum();
        drugs = ComponentContainer.drugController.getDrugList();
        check = String.valueOf(ComponentContainer.drugService.tostringbuilder(drugs, add));
        print.setText(check);
    }

    private void onCancel() {
        dispose();
        LoginMenu dialog = new LoginMenu();
        dialog.pack();
        dialog.setVisible(true);
    }


}
