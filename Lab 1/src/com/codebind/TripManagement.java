package com.codebind;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TripManagement {
    private JPanel MainPanel;
    private JList listOfManagedTrips;
    private JList listOfAvailableTrips;
    private JButton confirmButton;
    private static List<Trip> tripsToManage = new ArrayList<>();
    private static List<Trip> tripsToDelete = new ArrayList<>();

    public TripManagement(DefaultListModel<Trip> defaultAvailableTrips,DefaultListModel<Trip> defaultManagedTrips)
    {
        JFrame changeFrame = new JFrame("Change Window");
        changeFrame.setContentPane(this.MainPanel);
        changeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changeFrame.pack();
        changeFrame.setVisible(true);
        listOfManagedTrips.setModel(defaultManagedTrips);
        listOfAvailableTrips.setModel(defaultAvailableTrips);
        listOfManagedTrips.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                var element = listOfManagedTrips.getSelectedValue();
                if(element!=null) {
                defaultManagedTrips.removeElement(element);
                defaultAvailableTrips.addElement((Trip)element);
                    tripsToDelete.add((Trip) element);
                    if (tripsToManage.contains(element))
                        tripsToManage.remove(element);
                }
            }
        });
        listOfAvailableTrips.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                var element = listOfAvailableTrips.getSelectedValue();
                if(element!=null) {
                defaultAvailableTrips.removeElement(element);
                defaultManagedTrips.addElement((Trip)element);
                    tripsToManage.add((Trip) element);
                    if (tripsToDelete.contains(element))
                        tripsToDelete.remove(element);
                }
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.ChangeTripManagement(tripsToManage,tripsToDelete);
                changeFrame.dispose();
            }
        });
    }
}
