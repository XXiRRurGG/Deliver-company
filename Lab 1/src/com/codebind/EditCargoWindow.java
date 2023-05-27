package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditCargoWindow {
    private JPanel MainPanel;
    private JTextPane currentItems;
    private JButton editButton;
    private JTextField newItems;
    private List<String> listOfNewItems = new ArrayList<>();
public EditCargoWindow(Cargo oldCargo) {
    JFrame changeFrame = new JFrame("Change Window");
    changeFrame.setContentPane(this.MainPanel);
    changeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    changeFrame.pack();
    changeFrame.setVisible(true);
    currentItems.setText(oldCargo.CargoInfo());

    newItems.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(newItems.getText()!=null && newItems.getText()!="")
            {
                listOfNewItems.add(newItems.getText());
                newItems.setText("");
            }
        }
    });
    editButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(listOfNewItems.size()>0)
            {
                Cargo newCargo = new Cargo(listOfNewItems);
                App.ChangeCargoAfterEditing(oldCargo,newCargo);
                changeFrame.dispose();
            }
        }
    });
}
}
