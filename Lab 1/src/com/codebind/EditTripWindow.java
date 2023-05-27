package com.codebind;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditTripWindow {
    private JTextPane currentTripInfoField;
    private JList listOfStopsAtTripEditing;
    private JTextField fromFieldAtTripEditing;
    private JTextField toFieldAtTripEditing;
    private JButton confirmTripEditionButton;
    private JPanel MainPanel;
    private JPanel editTripPanel;

    public EditTripWindow(Trip oldTrip,DefaultListModel<String> stops)
    {
        JFrame changeFrame = new JFrame("Change Window");
        changeFrame.setContentPane(this.MainPanel);
        changeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changeFrame.pack();
        changeFrame.setVisible(true);
        listOfStopsAtTripEditing.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listOfStopsAtTripEditing.setModel(stops);
        currentTripInfoField.setText(oldTrip.TripInfo());
        confirmTripEditionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listOfStopsAtTripEditing.isSelectionEmpty() && fromFieldAtTripEditing.getText()!=null
                && fromFieldAtTripEditing.getText()!="" && toFieldAtTripEditing.getText()!=null && toFieldAtTripEditing.getText()!="")
                {
                    List<String> listOfStrings = listOfStopsAtTripEditing.getSelectedValuesList();
                    ArrayList<Stops> listOfStops = new ArrayList<>();
                    for(var string : listOfStrings)
                    {
                        listOfStops.add(new Stops(string));
                    }
                    Trip newTrip = new Trip(fromFieldAtTripEditing.getText(),toFieldAtTripEditing.getText(),listOfStops);
                    App.ChangeTripAfterEditing(oldTrip,newTrip);
                    changeFrame.dispose();
                }
            }
        });
    }
}
