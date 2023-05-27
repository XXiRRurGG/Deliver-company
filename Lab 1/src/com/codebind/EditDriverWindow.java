package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EditDriverWindow {
    private JPanel MainPanel;
    private JPanel UserCreation;
    private JButton editDriverButton;
    private JTextField nameField;
    private JTextField birthdateField;
    private JTextField loginTextField;
    private JTextField passwordField;
    private JList truckList;
    private JList tripsList;
    private JTextField phoneNumberFIeld;
    private JTextField surnameField;
    private JTextPane oldDriverInfoField;

    public EditDriverWindow(Driver oldDriver,DefaultListModel<Trip> defaultTripsList,DefaultListModel<Truck> defaultTruckList)
    {
        JFrame changeFrame = new JFrame("Change Window");
        changeFrame.setContentPane(this.MainPanel);
        changeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changeFrame.pack();
        changeFrame.setVisible(true);
        truckList.setModel(defaultTruckList);
        tripsList.setModel(defaultTripsList);
        oldDriverInfoField.setText(oldDriver.DriverInfo());
        editDriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText() != "" && surnameField.getText() != "" && phoneNumberFIeld.getText() != ""
                        && birthdateField.getText() != "" && !truckList.isSelectionEmpty() && !tripsList.isSelectionEmpty() && loginTextField.getText() != "" && passwordField.getText() != ""
                        && !nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !phoneNumberFIeld.getText().isEmpty() && !birthdateField.getText().isEmpty() && !loginTextField.getText().isEmpty() && !passwordField.getText().isEmpty())
                {
                    List<Trip> unassignedTrips = Collections.list(defaultTripsList.elements());
                    List<Trip> filteredList = unassignedTrips.stream()
                            .filter(trip -> !trip.equals(truckList.getSelectedValue()))
                            .collect(Collectors.toList());
                    Driver newDriver = new Driver(nameField.getText(), surnameField.getText(), phoneNumberFIeld.getText(), birthdateField.getText(), (Truck) truckList.getSelectedValue(), (Trip) tripsList.getSelectedValue(), filteredList, loginTextField.getText(), passwordField.getText());
                    App.ChangeDriverAfterEditing(oldDriver,newDriver);
                    changeFrame.dispose();
                }
            }
        });
    }
}
