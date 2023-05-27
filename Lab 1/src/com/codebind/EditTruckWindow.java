package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTruckWindow extends JFrame{
    private JButton editButton;
    public JPanel MainPanel;
    private JTextField truckNumberField;
    private JTextField truckBrandField;
    private JList cargoList;
    private JTextArea currentInfo;

    public EditTruckWindow(Truck currentTruck,DefaultListModel<Cargo> cargoDefaultListModel)
    {
        JFrame changeFrame = new JFrame("Change Window");
        changeFrame.setContentPane(this.MainPanel);
        changeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changeFrame.pack();
        changeFrame.setVisible(true);
        currentInfo.setText(currentTruck.TruckInfo());
        cargoList.setModel(cargoDefaultListModel);

        cargoList.setCellRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Cargo) {
                    Cargo cargo = (Cargo) value;
                    String text = "Cargo:" + (cargo.GetCargosID());
                    for(int i=0;i<cargo.items.size();i++)
                    {
                        if(i>2)
                        {
                            text+="...";
                            break;
                        }
                        else
                        {
                            text += " " + cargo.items.get(i);
                        }
                    }
                    setText(text);
                }
                return this;
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String truckNumber = truckNumberField.getText();
                String brand = truckBrandField.getText();
                Cargo cargo = (Cargo)cargoList.getSelectedValue();
                if(truckNumber!=null && truckNumber!="" && brand!=null && brand !="" && !cargoList.isSelectionEmpty())
                {
                    Truck newTruck = new Truck(truckNumber,brand,cargo);
                    App.ChangeTruckAfterEditing(currentTruck,newTruck);
                    changeFrame.dispose();
                }
            }
        });
    }
}
