package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAdminField {
    private JPanel MainPanel;
    private JPanel adminEditPanel;
    private JTextArea currentAdminInfo;
    private JButton editButton;
    private JTextField newNameField;
    private JTextField newSurnameField;
    private JTextField newBirthdateField;
    private JTextField newContactInfoField;
    private JTextField newLoginField;
    private JTextField newPasswordField;
public EditAdminField(Admin oldAdmin) {
    JFrame changeFrame = new JFrame("Change Window");
    changeFrame.setContentPane(this.MainPanel);
    changeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    changeFrame.pack();
    changeFrame.setVisible(true);
    currentAdminInfo.setText(oldAdmin.AdminInfo());
    editButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(newNameField.getText() != "" && newSurnameField.getText() != ""
                    && newBirthdateField.getText() != "" && newContactInfoField.getText() != "" && newLoginField.getText()!=""
                    && newPasswordField.getText()!="" && newNameField.getText()!=null && newSurnameField.getText()!=null
                    && newBirthdateField.getText()!=null && newContactInfoField.getText()!=null && newLoginField.getText()!=null
                    && newPasswordField.getText()!=null) {
                Admin newAdmin = new Admin(newNameField.getText(),newSurnameField.getText(),newContactInfoField.getText(),newBirthdateField.getText(),newLoginField.getText(),newPasswordField.getText());
                App.ChangeAdminAfterEdition(oldAdmin,newAdmin);
                changeFrame.dispose();
            }
        }
    });
}
}
