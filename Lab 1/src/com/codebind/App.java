package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    private JButton truckCreateButton;
    private JPanel MainPanel;
    private JPanel containPanel;
    private JPanel UserCreation;
    private JRadioButton client;
    private JRadioButton admin;
    private JRadioButton driver;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField birthdateField;
    private JTextField phoneNumberFIeld;
    private JTabbedPane tabbedPane1;
    private JList truckList;
    private JPanel TruckCreation;
    private JTextField truckNumberValue;
    private JTextField brandValue;
    private JList cargoList;
    private JPanel cargoCreation;
    private JLabel cargoIDLabel;
    private JButton createCargo;
    private JTextField cargoItems;
    private JPanel tripCreation;
    private JTextField stopValue;
    private JTextField fromValue;
    private JTextField toValue;
    private JButton tripCreateButton;
    private JList tripsList;
    private JButton createButton;
    private JPanel mainJPanel;
    private JList usersList;
    private JPanel chooseOfUser;
    private JPanel adminPanel;
    private JButton pickUser;
    private JPanel driverPanel;
    private JPanel clientPanel;
    private JList listOfNonAdmins;
    private JButton removeButton;
    private JButton backButton;
    private JList availableTrips;
    private JButton backButton2;
    private JButton backButton3;
    private JButton chooseTripButton;
    private JTextArea currentClientInfo;
    private JButton changeButton;
    private JTextField newNameField;
    private JTextField newSurnameField;
    private JTextField newContactInfoField;
    private JTextField newBirthdateField;
    private JTextField loginTextField;
    private JTextField passwordField;
    private JList listOfAllStops;
    private JList listOfCurrentStops;
    private JButton stopAddButton;
    private JButton deleteStopFromListButton;
    private JButton deleteStopFromCurrentButton;
    private JTextField logIntoAccTextField;
    private JTextField passwordIntoAccTextField;
    private JButton loginButton;
    private JTextField newLoginField;
    private JTextField newPasswordField;
    private JButton editTrucksButton;
    private JPanel createTruckPanel;
    private JPanel chooseTruckForEditPanel;
    private JList truckListForEdition;
    private JButton truckBackButton;
    private JButton truckDeleteButton;
    private JButton truckEditButton;
    private JPanel createCargoPanel;
    private JPanel editCargoPanel;
    private JButton goToCargoEditionButton;
    private JButton backToCargoCreationButton;
    private JButton deleteCargoButton;
    private JButton editCargoButton;
    private JList cargoListForEdition;
    private JPanel creatingTrip;
    private JButton editStopButton;
    private JPanel editingStop;
    private JTextField currentStopValueField;
    private JTextField newStopValueField;
    private JButton backToTripCreationButton;
    private JButton confirmStopEditionButton;
    private JButton editTripsButton;
    private JPanel editingTripPanel;
    private JList tripListForEdition;
    private JButton backToTripCreationButton2;
    private JButton deleteTripButton;
    private JButton editTripButton;
    private JButton editCurrentAdminButton;
    private JButton editDriverButton;
    private JButton managedTripsButton;
    private JComboBox userFilter;
    private JComboBox tripFilter;
    private JPanel statisticsPanel;
    private JButton showTripGraph;
    private JButton showRatioStatisticsButton;
    private JCheckBox checkBox1;
    private CardLayout usersCardLayout = (CardLayout) mainJPanel.getLayout();
    private CardLayout truckCardLayout = (CardLayout) TruckCreation.getLayout();
    private CardLayout cargoCardLayout = (CardLayout) cargoCreation.getLayout();
    private CardLayout tripCardLayout = (CardLayout) tripCreation.getLayout();

    //private ArrayList<Cargo> cargos = new ArrayList<>();
    //private ArrayList<Truck> trucks = new ArrayList<>();
    //private ArrayList<Trip> trips = new ArrayList<>();
    private ArrayList<String> tempCargo = new ArrayList<>();
    //private ArrayList<User> users = new ArrayList<>();
    static DefaultListModel<Cargo> defaultCargoList= new DefaultListModel<>();
    static DefaultListModel<Truck> defaultTruckList= new DefaultListModel<>();
    static DefaultListModel<Trip> defaultTripsList = new DefaultListModel<>();
    static DefaultListModel<String> defaultAllStopsList = new DefaultListModel<>();
    static DefaultListModel<String> defaultCurrentStopsList = new DefaultListModel<>();
    static DefaultListModel<User> defaultUsersList = new DefaultListModel<>();
    static String path = "jdbc:mysql://localhost:3306/delivercompany";
    static String username = "Admin";
    static String password = "Admin";
    static User loggedUser = new User();

    static Connection connection;
    private static ArrayList<Stops> retrieveStopsForTrip(int tripId) {
        ArrayList<Stops> stops = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(path, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT stops.* FROM stops INNER JOIN tripstops ON stops.ID = tripstops.StopID WHERE tripstops.TripID = " + tripId)) {
            while (rs.next()) {
                int stopId = rs.getInt("ID");
                String stopName = rs.getString("Stop");
                Stops stop = new Stops(stopName);
                stops.add(stop);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return stops;
    }
    public static void LoadData()
    {
        try {
            connection = DriverManager.getConnection(path, username, password);
        }
        catch (SQLException e)
        {
            System.out.println("SQLException occured:" + e.getMessage());
        }
        Statement statement;
        ResultSet driverResults;
        ResultSet adminResults;
        ResultSet clientResults;
        //Client data
        try {
            statement = connection.createStatement();
            clientResults = statement.executeQuery("SELECT * FROM client");
            try {
                while (clientResults.next()) {
                    String name = clientResults.getString("name");
                    String surname = clientResults.getString("surname");
                    String contactInfo = clientResults.getString("ContactInfo");
                    String dateOfBirth = clientResults.getString("DateOfBirth");
                    String login = clientResults.getString("Login");
                    String password = clientResults.getString("Password");

                    Client client = new Client(name, surname, contactInfo, dateOfBirth,login,password);
                    defaultUsersList.addElement(client);
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            clientResults.close();
            //Admin data
            adminResults = statement.executeQuery("SELECT * FROM admin");
            try {
                while (adminResults.next()) {
                    String name = adminResults.getString("name");
                    String surname = adminResults.getString("surname");
                    String contactInfo = adminResults.getString("ContactInfo");
                    String dateOfBirth = adminResults.getString("DateOfBirth");
                    String login = adminResults.getString("Login");
                    String password = adminResults.getString("Password");

                    Admin admin = new Admin(name, surname, contactInfo, dateOfBirth,login,password);
                    defaultUsersList.addElement(admin);
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            adminResults.close();
            //Cargo data
            String sql = "SELECT c.id, l.StringValue FROM cargo c JOIN listofstrings l ON c.ListOfStringsID = l.id";
            try
            {
                ResultSet rs = statement.executeQuery(sql);
                int tempId = 1;
                List<String> items = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String stringValue = rs.getString("StringValue");
                    if(id == tempId)
                    {
                        items.add(stringValue);
                    }
                    else
                    {
                        Cargo cargo = new Cargo(items);
                        defaultCargoList.addElement(cargo);
                        items = new ArrayList<>();
                        tempId = id;
                        var splittedStringValue = stringValue.split(",");
                        items.addAll(Arrays.stream(splittedStringValue).toList());
                    }
                }
                defaultCargoList.addElement(new Cargo(items));
                rs.close();
            }
            catch (Exception e)
            {

            }

            String query = "SELECT Stop FROM stops";
            ResultSet stopsResults = statement.executeQuery(query);
            //all Stops
            try {
                while (stopsResults.next()) {
                    String stopName = stopsResults.getString("Stop");
                    defaultAllStopsList.addElement(stopName);
                }
            }
            catch (Exception e)
            {

            }
            stopsResults.close();
            //Trips data
            ResultSet tripsResult = statement.executeQuery("SELECT * FROM trip");
            try {
                while (tripsResult.next()) {
                    int tripId = tripsResult.getInt("ID");
                    String from = tripsResult.getString("FromLocation");
                    String to = tripsResult.getString("ToLocation");
                    ArrayList<Stops> stops = retrieveStopsForTrip(tripId);
                    Trip trip = new Trip(from, to, stops);
                    trip.SetId(tripId);
                    defaultTripsList.addElement(trip);
                }
            }
            catch (Exception e)
            {

            }
            tripsResult.close();
            //Trucks data
            ResultSet truckResults = statement.executeQuery("Select * from truck");

            try {
                while (truckResults.next()) {
                    String truckNumber = truckResults.getString("TruckNumber");
                    String brand = truckResults.getString("Brand");
                    int cargoID = truckResults.getInt("CargoID");

                    Cargo cargo = new Cargo();
                    String sql2 = "SELECT * FROM cargo JOIN listofstrings ON cargo.listofstringsid = listofstrings.id WHERE cargo.id = ?";
                    PreparedStatement stmt = connection.prepareStatement(sql2);
                    stmt.setInt(1, cargoID);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        List<String> list = new ArrayList<>();
                        list.addAll(Arrays.stream(rs.getString("listofstrings.stringvalue").split(",")).toList());
                        cargo = new Cargo(list);
                    }

                    Truck truck = new Truck(truckNumber, brand, cargo);
                    defaultTruckList.addElement(truck);
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            truckResults.close();
            //Driver data
            driverResults = statement.executeQuery("SELECT * FROM driver");
            try
            {
                while(driverResults.next())
                {
                    String name = driverResults.getString("name");
                    String surname = driverResults.getString("surname");
                    String contactInfo = driverResults.getString("ContactInfo");
                    String dateOfBirth = driverResults.getString("DateOfBirth");
                    String truckNumber = driverResults.getString("Truck");
                    String trip = driverResults.getString("Trip");
                    String login = driverResults.getString("Login");
                    String password = driverResults.getString("Password");

                    Trip trip1 = new Trip();
                    for(int i=0;i<defaultTripsList.size();i++)
                    {
                        if(defaultTripsList.get(i).GetID() == Integer.parseInt(trip))
                        {
                            trip1 = defaultTripsList.get(i);
                            break;
                        }
                    }
                    Truck truck = new Truck();
                    for(int i=0;i<defaultTruckList.size();i++)
                    {
                        if(((Truck)defaultTruckList.get(i)).GetNumber().equals(truckNumber))
                        {
                            truck = defaultTruckList.get(i);
                            break;
                        }
                    }
                    List<Trip> unassignedTrips = new ArrayList<>();
                    for (int i=0;i<defaultTripsList.size();i++)
                    {
                         if(defaultTripsList.get(i) != trip1)
                         {
                             unassignedTrips.add(defaultTripsList.get(i));
                         }
                    }
                    defaultUsersList.addElement(new Driver(name,surname,contactInfo,dateOfBirth,truck,trip1,unassignedTrips,login,password));
                }
            }
            catch (Exception e)
            {

            }
        driverResults.close();

        }
        catch(Exception e)
        {
            System.out.println("Exception");
        }
    }
    public App() {
        cargoList.setModel(defaultCargoList);
        truckList.setModel(defaultTruckList);
        tripsList.setModel(defaultTripsList);
        listOfAllStops.setModel(defaultAllStopsList);
        listOfCurrentStops.setModel(defaultCurrentStopsList);
        truckListForEdition.setModel(defaultTruckList);
        cargoListForEdition.setModel(defaultCargoList);
        tripListForEdition.setModel(defaultTripsList);

        cargoListForEdition.setCellRenderer(new DefaultListCellRenderer() {
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
        createCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargoIDLabel.setText("ID:" + Cargo.GetID());
            }
        });
        tabbedPane1.addComponentListener(new ComponentAdapter() {
        });
        cargoItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cargoItems.getText() != "") {
                    tempCargo.add(cargoItems.getText());
                    cargoItems.setText("");
                }
            }
        });
        createCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tempCargo.size() != 0) {
                    defaultCargoList.addElement(new Cargo((tempCargo)));
                    try
                    {
                        String sql = "insert into listofstrings(stringvalue) values(?)";
                        PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        pstmt.setString(1,String.join(",",tempCargo));
                        int listId = 0;
                        int rowsAffected = pstmt.executeUpdate();
                        if(rowsAffected == 1) {
                            ResultSet rs = pstmt.getGeneratedKeys();
                            if (rs.next()) {
                                listId = rs.getInt(1);
                            }
                            rs.close();
                        }
                        pstmt = connection.prepareStatement("insert into cargo(ListOfStringsID) values(?)");
                        pstmt.setInt(1,listId);
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    tempCargo.clear();
                }
            }
        });
        truckCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(brandValue.getText() != "" && truckNumberValue.getText() != "")
                {
                    if(cargoList.isSelectionEmpty())
                    {
                        JFrame frame = new JFrame("Error");
                        JOptionPane.showMessageDialog(frame, "Please, choose cargo ID!",
                                "Swing Tester", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        String tempTruckNumber;
                        String tempBrand;

                        tempTruckNumber = truckNumberValue.getText();
                        tempBrand = brandValue.getText();
                        Boolean hasTruckNumber = false;
                        for(int i=0;i<defaultTruckList.size();i++)
                        {
                            if(defaultTruckList.get(i).GetNumber().equals(tempTruckNumber))
                            {
                                hasTruckNumber = true;
                                break;
                            }
                        }
                        if(hasTruckNumber)
                        {
                            JFrame frame = new JFrame("Error");
                            JOptionPane.showMessageDialog(frame, "Please, choose another truck number! This one is already taken!",
                                    "Swing Tester", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            Truck truck = new Truck(tempTruckNumber, tempBrand, (Cargo)cargoList.getSelectedValue());
                            try
                            {
                                String sql = "SELECT c.id FROM cargo c JOIN listofstrings l ON c.listofstringsid = l.id WHERE l.stringvalue = ?";
                                PreparedStatement pstmt = connection.prepareStatement(sql);
                                pstmt.setString(1,((Cargo) cargoList.getSelectedValue()).GetListOfStrings());
                                ResultSet rs = pstmt.executeQuery();
                                int cargoID = 0;
                                if (rs.next())
                                {
                                    cargoID = rs.getInt("ID");
                                }
                                rs.close();

                                sql = "insert into truck values(?,?,?)";
                                pstmt = connection.prepareStatement(sql);
                                pstmt.setString(1,truck.GetNumber());
                                pstmt.setString(2,tempBrand);
                                pstmt.setInt(3,cargoID);
                                pstmt.executeUpdate();
                                pstmt.close();
                            }
                            catch (Exception ex)
                            {
                                System.out.println(ex.getMessage());
                            }
                            truckNumberValue.setText("");
                            brandValue.setText("");
                            defaultTruckList.addElement(truck);
                            truckList.setModel(defaultTruckList);
                        }
                    }
                }
            }
        });
        stopValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(stopValue.getText() != "")
                {
                    defaultAllStopsList.addElement(stopValue.getText());
                    try {
                        String sql = "insert into stops(stop) values(?)";
                        PreparedStatement pstd = connection.prepareStatement(sql);
                        pstd.setString(1,stopValue.getText());
                        pstd.executeUpdate();
                        pstd.close();
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    stopValue.setText("");
                }
            }
        });
        tripCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(defaultCurrentStopsList.size() != 0 && fromValue.getText() != "" && toValue.getText() != "")
                {
                    ArrayList<Stops> tempStopList = new ArrayList<>();
                    for(int i=0;i<defaultCurrentStopsList.size();i++)
                    {
                        tempStopList.add(new Stops(defaultCurrentStopsList.getElementAt(i)));
                    }
                    defaultTripsList.addElement(new Trip(fromValue.getText(),toValue.getText(),tempStopList));
                    defaultCurrentStopsList.clear();
                    tripsList.setModel(defaultTripsList);
                    List<Integer> stopIds = new ArrayList<>();
                    for (int i=0;i<tempStopList.size();i++) {
                        String stop = tempStopList.get(i).Stop();
                        String sql = "SELECT id FROM stops WHERE stop = ?";
                        try {
                            PreparedStatement pstmt = connection.prepareStatement(sql);
                            pstmt.setString(1, stop);
                            ResultSet rs = pstmt.executeQuery();
                            if (rs.next()) {
                                int stopId = rs.getInt("id");
                                stopIds.add(stopId);
                            }
                            rs.close();
                            pstmt.close();
                        }
                        catch (Exception ex)
                        {
                            System.out.println(ex.getMessage());
                        }
                    }
                    try
                    {
                        String sql = "INSERT INTO trip (fromlocation,tolocation) VALUES (?,?)";
                        PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        pstmt.setString(1,fromValue.getText());
                        pstmt.setString(2,toValue.getText());
                        pstmt.executeUpdate();
                        ResultSet rs = pstmt.getGeneratedKeys();
                        int tripId = 0;
                        if (rs.next()) {
                            tripId = rs.getInt(1);
                        }
                        rs.close();
                        pstmt.close();
                        for (int stopId : stopIds) {
                            sql = "INSERT INTO tripstops (tripid, stopid) VALUES (?, ?)";
                            try {
                                pstmt = connection.prepareStatement(sql);
                                pstmt.setInt(1, tripId);
                                pstmt.setInt(2, stopId);
                                pstmt.executeUpdate();
                                pstmt.close();
                            }
                            catch (Exception ex)
                            {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }

                    fromValue.setText("");
                    toValue.setText("");
                }
            }
        });
        driver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                truckList.setEnabled(true);
                tripsList.setEnabled(true);
            }
        });
        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                truckList.setEnabled(false);
                tripsList.setEnabled(false);
            }
        });
        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                truckList.setEnabled(false);
                tripsList.setEnabled(false);
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean loginTaken = false;
                for(int i=0;i<defaultUsersList.size();i++)
                {
                    if(defaultUsersList.get(i).GetLogin()!=null && defaultUsersList.get(i).GetLogin().equals(loginTextField.getText()))
                    {
                        loginTaken = true;
                        break;
                    }
                }
                if(!loginTaken) {
                    String table = "";
                    String truckNumber = "";
                    int tripId = 0;
                    if (client.isSelected()) {
                        if (nameField.getText() != "" && surnameField.getText() != "" && phoneNumberFIeld.getText() != "" && birthdateField.getText() != "" && loginTextField.getText() != "" && passwordField.getText() != ""
                        && !nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !phoneNumberFIeld.getText().isEmpty() && !birthdateField.getText().isEmpty() && !loginTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                            defaultUsersList.addElement(new Client(nameField.getText(), surnameField.getText(), phoneNumberFIeld.getText(), birthdateField.getText(), loginTextField.getText(), passwordField.getText()));
                            table = "client";
                        }
                    } else if (admin.isSelected()) {
                        if (nameField.getText() != "" && surnameField.getText() != "" && phoneNumberFIeld.getText() != "" && birthdateField.getText() != "" && loginTextField.getText() != "" && passwordField.getText() != ""
                        && !nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !phoneNumberFIeld.getText().isEmpty() && !birthdateField.getText().isEmpty() && !loginTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                            defaultUsersList.addElement(new Admin(nameField.getText(), surnameField.getText(), phoneNumberFIeld.getText(), birthdateField.getText(), loginTextField.getText(), passwordField.getText()));
                            table = "admin";
                        }
                    } else if (driver.isSelected()) {
                        if (nameField.getText() != "" && surnameField.getText() != "" && phoneNumberFIeld.getText() != ""
                                && birthdateField.getText() != "" && !truckList.isSelectionEmpty() && !tripsList.isSelectionEmpty() && loginTextField.getText() != "" && passwordField.getText() != ""
                                && !nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !phoneNumberFIeld.getText().isEmpty() && !birthdateField.getText().isEmpty() && !loginTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                            List<Trip> unassignedTrips = Collections.list(defaultTripsList.elements());
                            List<Trip> filteredList = unassignedTrips.stream()
                                    .filter(trip -> !trip.equals(truckList.getSelectedValue()))
                                    .collect(Collectors.toList());

                            defaultUsersList.addElement(new Driver(nameField.getText(), surnameField.getText(), phoneNumberFIeld.getText(), birthdateField.getText(), (Truck) truckList.getSelectedValue(), (Trip) tripsList.getSelectedValue(), filteredList, loginTextField.getText(), passwordField.getText()));
                            table = "driver";
                        }
                    } else {
                        return;
                    }
                    User user = defaultUsersList.get(defaultUsersList.size()-1);
                    if(table.equals("driver"))
                    {
                       try
                       {
                           String sql = "select id from trip where fromLocation=? and toLocation=?";
                           PreparedStatement pstmt = connection.prepareStatement(sql);
                           pstmt.setString(1,((Driver)user).CurrentTrip().StartingPoint());
                           pstmt.setString(2,((Driver)user).CurrentTrip().Destination());
                           ResultSet rs = pstmt.executeQuery();
                           int tripID = 0;
                           if(rs.next())
                           {
                               tripID = rs.getInt("id");
                           }
                           rs.close();
                           pstmt.close();

                           sql = "Insert into driver values(?,?,?,?,?,?,?,?)";
                           pstmt = connection.prepareStatement(sql);
                           pstmt.setString(1, user.name);
                           pstmt.setString(2, user.surname);
                           pstmt.setString(3, user.contactInfo);
                           pstmt.setString(4, user.dateOfBirth);
                           pstmt.setString(5, user.login);
                           pstmt.setString(6, user.password);
                           pstmt.setString(7, ((Driver)user).GetTruckNumber());
                           pstmt.setInt(8, tripID);
                           pstmt.executeUpdate();
                       }
                       catch (Exception ex)
                       {
                           System.out.println(ex.getMessage());
                       }
                    }
                    else
                    {
                        try
                        {
                            String sql = "Insert into "+table+" values(?,?,?,?,?,?)";
                            PreparedStatement pstmt = connection.prepareStatement(sql);
                            pstmt.setString(1, user.name);
                            pstmt.setString(2, user.surname);
                            pstmt.setString(3, user.contactInfo);
                            pstmt.setString(4, user.dateOfBirth);
                            pstmt.setString(5, user.login);
                            pstmt.setString(6, user.password);
                            pstmt.executeUpdate();
                        }
                        catch (Exception ex)
                        {
                            System.out.println(ex.getMessage());
                        }
                    }
                    nameField.setText("");
                    surnameField.setText("");
                    phoneNumberFIeld.setText("");
                    birthdateField.setText("");
                    passwordField.setText("");
                    loginTextField.setText("");
                }
                else
                {
                    JFrame frame = new JFrame("Error");
                    JOptionPane.showMessageDialog(frame, "This login is already taken",
                            "Swing Tester", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listOfNonAdmins.isSelectionEmpty() && ((User)listOfNonAdmins.getSelectedValue()).GetAccessLevel().toString() != "ADMIN")
                {
                    var loginOfUserToRemove = ((User) listOfNonAdmins.getSelectedValue()).login;
                    String table = ((User)listOfNonAdmins.getSelectedValue()).GetAccessLevel().toString().equals("CLIENT") ? "client" : "driver";
                    defaultUsersList.remove(listOfNonAdmins.getSelectedIndex());
                    try
                    {
                        String sql = "DELETE FROM " + table + " WHERE login = ?";
                        PreparedStatement pstmt = connection.prepareStatement(sql);

                        pstmt.setString(1, loginOfUserToRemove);
                        pstmt.executeUpdate();
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                }
                else
                {
                    JFrame frame = new JFrame("Error");
                    JOptionPane.showMessageDialog(frame, "Either user is Admin, or you haven't chosen any.",
                            "Swing Tester", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersCardLayout.show(mainJPanel, "Card1");
            }
        });
        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersCardLayout.show(mainJPanel, "Card1");
            }
        });
        backButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersCardLayout.show(mainJPanel, "Card1");
            }
        });
        chooseTripButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!availableTrips.isSelectionEmpty())
                {
                    ((Driver) loggedUser).ChangeActiveTrip((Trip)availableTrips.getSelectedValue());
                    for(int i=0;i<defaultUsersList.size();i++)
                    {
                        if(loggedUser.login.equals(defaultUsersList.get(i).login))
                        {
                            defaultUsersList.set(i,loggedUser);
                            //Replace info in database
                            try {
                                String sql = "UPDATE driver SET Trip = ? WHERE login = ?";
                                PreparedStatement pstmt = connection.prepareStatement(sql);
                                pstmt.setInt(1, ((Driver) loggedUser).GetTripID());
                                pstmt.setString(2, loggedUser.login);
                                pstmt.executeUpdate();
                            }
                            catch (Exception ex)
                            {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                }
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newNameField.getText() != "" && newSurnameField.getText() != ""
                && newBirthdateField.getText() != "" && newContactInfoField.getText() != "" && newLoginField.getText()!=""
                && newPasswordField.getText()!="" && newNameField.getText()!=null && newSurnameField.getText()!=null
                && newBirthdateField.getText()!=null && newContactInfoField.getText()!=null && newLoginField.getText()!=null
                && newPasswordField.getText()!=null)
                {
                    var loginBeforeChange = loggedUser.login;
                    loggedUser.ChangeInfo(newNameField.getText(),newSurnameField.getText(),newContactInfoField.getText(),newBirthdateField.getText(),newLoginField.getText(),newPasswordField.getText());
                    newNameField.setText("");
                    newSurnameField.setText("");
                    newContactInfoField.setText("");
                    newBirthdateField.setText("");
                    newLoginField.setText("");
                    newPasswordField.setText("");
                    currentClientInfo.setText(loggedUser.name + " " + loggedUser.surname + "\n" + loggedUser.dateOfBirth + "\n" + loggedUser.contactInfo + "\n" + loggedUser.login + "\n" + loggedUser.password);
                    for(int i=0;i<defaultUsersList.size();i++)
                    {
                        if(loggedUser.login.equals(defaultUsersList.get(i).login))
                        {
                            defaultUsersList.set(i,loggedUser);
                            //Replace info in database
                            try {
                                String sql = "UPDATE client SET name = ?, surname = ?, contactInfo = ?,dateOfBirth = ?, login = ?, password = ? WHERE login = ?";
                                PreparedStatement pstmt = connection.prepareStatement(sql);
                                pstmt.setString(1,loggedUser.name);
                                pstmt.setString(2,loggedUser.surname);
                                pstmt.setString(3,loggedUser.contactInfo);
                                pstmt.setString(4,loggedUser.dateOfBirth);
                                pstmt.setString(5,loggedUser.login);
                                pstmt.setString(6, loggedUser.password);
                                pstmt.setString(7, loginBeforeChange);
                                pstmt.executeUpdate();
                            }
                            catch (Exception ex)
                            {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                }
            }
        });
        stopAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listOfAllStops.isSelectionEmpty())
                {
                    if(!defaultCurrentStopsList.contains(listOfAllStops.getSelectedValue().toString()))
                        defaultCurrentStopsList.addElement(listOfAllStops.getSelectedValue().toString());
                }
            }
        });
        deleteStopFromListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listOfAllStops.isSelectionEmpty()) {
                    Stops stop = new Stops(listOfAllStops.getSelectedValue().toString());
                    if(defaultCurrentStopsList.contains(stop))
                    {
                        defaultCurrentStopsList.removeElement(stop);
                    }
                    try
                    {
                        String sqlGetStopId = "SELECT id FROM stops WHERE stop = ?";
                        PreparedStatement pstmt = connection.prepareStatement(sqlGetStopId);
                        pstmt.setString(1, stop.Stop());
                        ResultSet rs = pstmt.executeQuery();

                        int stopId = 0;
                        if (rs.next()) {
                            stopId = rs.getInt("id");
                        } else {
                            return;
                        }
                        String sqlDeleteFromTripStops = "DELETE FROM tripstops WHERE stopid = ?";
                        pstmt = connection.prepareStatement(sqlDeleteFromTripStops);
                        pstmt.setInt(1, stopId);
                        pstmt.executeUpdate();

                        String sqlDeleteFromStops = "DELETE FROM stops WHERE id = ?";
                        pstmt = connection.prepareStatement(sqlDeleteFromStops);
                        pstmt.setInt(1, stopId);
                        pstmt.executeUpdate();
                        rs.close();
                        pstmt.close();
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    defaultAllStopsList.removeElement(listOfAllStops.getSelectedValue().toString());
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(logIntoAccTextField.getText() != null && logIntoAccTextField.getText() != "" && passwordIntoAccTextField.getText()!= null && passwordIntoAccTextField.getText() != "")
                {
                    Boolean userExists = false;
                    for (int i=0;i<defaultUsersList.size();i++)
                    {
                        if(defaultUsersList.get(i).GetLogin().equals(logIntoAccTextField.getText()) && defaultUsersList.get(i).GetPassword().equals(passwordIntoAccTextField.getText()))
                        {
                            loggedUser = defaultUsersList.get(i);
                            userExists = true;
                            break;
                        }
                    }
                    if(userExists)
                    {
                        if(loggedUser.GetAccessLevel() == "ADMIN") {
                            usersCardLayout.show(mainJPanel, "Card2");
                            listOfNonAdmins.setModel(defaultUsersList);
                        }
                        else if(loggedUser.GetAccessLevel() == "DRIVER") {
                            usersCardLayout.show(mainJPanel, "Card3");
                            availableTrips.setModel(defaultTripsList);
                        }
                        else if(loggedUser.GetAccessLevel() == "CLIENT") {
                            usersCardLayout.show(mainJPanel, "Card4");
                            currentClientInfo.setText(loggedUser.name + " " + loggedUser.surname + "\n" + loggedUser.dateOfBirth + "\n" + loggedUser.contactInfo + "\n" + loggedUser.login + "\n" + loggedUser.password);
                        }
                    }
                    else
                    {
                        JFrame frame = new JFrame("Error");
                        JOptionPane.showMessageDialog(frame, "User with this parameters doesn't exist.",
                                "Swing Tester", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        deleteStopFromCurrentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listOfCurrentStops.isSelectionEmpty())
                {
                    defaultCurrentStopsList.removeElement(listOfCurrentStops.getSelectedValue());
                }
            }
        });
        editTrucksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                truckCardLayout.show(TruckCreation,"Card2");
            }
        });
        truckBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                truckCardLayout.show(TruckCreation,"Card1");
            }
        });
        truckDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!truckListForEdition.isSelectionEmpty())
                {
                    Truck truck = (Truck)truckListForEdition.getSelectedValue();
                    for(int i=0;i<defaultUsersList.size();i++)
                    {
                        if(defaultUsersList.get(i) instanceof Driver)
                        {
                            Driver driver1 = (Driver)defaultUsersList.get(i);
                            if(((Driver)defaultUsersList.get(i)).GetTruckNumber()!=null &&  ((Driver)defaultUsersList.get(i)).GetTruckNumber().equals(truck.GetNumber()))
                            {
                                ((Driver)defaultUsersList.get(i)).DeleteTruck();
                                try
                                {
                                    String sql = "update driver set truck=NULL where login=?";
                                    PreparedStatement pstmt = connection.prepareStatement(sql);
                                    pstmt.setString(1,driver1.login);
                                    pstmt.executeUpdate();
                                    pstmt.close();
                                }
                                catch (Exception ex)
                                {
                                    System.out.println(ex.getMessage());
                                }
                            }
                        }
                        defaultTruckList.removeElement(truck);
                        try
                        {
                            String sql = "delete from truck where TruckNumber=?";
                            PreparedStatement pstmt = connection.prepareStatement(sql);
                            pstmt.setString(1,truck.GetNumber());
                            pstmt.executeUpdate();
                            pstmt.close();
                        }
                        catch (Exception ex)
                        {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        });
        truckEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!truckListForEdition.isSelectionEmpty())
                {
                    EditTruckWindow truckEditing = new EditTruckWindow((Truck)truckListForEdition.getSelectedValue(),defaultCargoList);
                }
            }
        });
        goToCargoEditionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargoCardLayout.show(cargoCreation,"Card2");
            }
        });
        backToCargoCreationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargoCardLayout.show(cargoCreation,"Card1");
            }
        });
        deleteCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!cargoListForEdition.isSelectionEmpty())
                {
                    Cargo cargo = (Cargo)cargoListForEdition.getSelectedValue();
                    try
                    {
                        String sql = "select cargo.id, listofstrings.id from cargo join listofstrings on cargo.listofstringsid = listofstrings.id where listofstrings.stringvalue=?";
                        PreparedStatement pstmt = connection.prepareStatement(sql);
                        pstmt.setString(1,String.join(",",cargo.items));
                        ResultSet rs = pstmt.executeQuery();
                        int cargoID = 0;
                        int listOfStringsID = 0;
                        if(rs.next())
                        {
                            cargoID = rs.getInt(1);
                            listOfStringsID = rs.getInt(2);
                        }
                        rs.close();
                        pstmt.close();
                        sql = "delete from cargo where id=?";
                        pstmt = connection.prepareStatement(sql);
                        pstmt.setInt(1,cargoID);
                        pstmt.executeUpdate();
                        pstmt.close();
                        sql = "delete from listofstrings where id=?";
                        pstmt = connection.prepareStatement(sql);
                        pstmt.setInt(1,listOfStringsID);
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    defaultCargoList.removeElement(cargo);
                }
            }
        });
        editCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!cargoListForEdition.isSelectionEmpty()) {
                    EditCargoWindow editCargoWindow = new EditCargoWindow((Cargo) cargoListForEdition.getSelectedValue());
                }
            }
        });
        editStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listOfAllStops.isSelectionEmpty())
                {
                    tripCardLayout.show(tripCreation,"Card2");
                    currentStopValueField.setText((String)listOfAllStops.getSelectedValue());
                }
            }
        });
        backToTripCreationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripCardLayout.show(tripCreation,"Card1");
            }
        });
        confirmStopEditionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newStopValueField.getText()!=null && newStopValueField.getText()!="")
                {
                    try
                    {
                        String sql = "update stops set stop=? where stop=?";
                        PreparedStatement pstmt = connection.prepareStatement(sql);
                        pstmt.setString(1,newStopValueField.getText());
                        pstmt.setString(2,currentStopValueField.getText());
                        pstmt.executeUpdate();
                        pstmt.close();
                        defaultAllStopsList.removeElement(currentStopValueField.getText());
                        if(defaultCurrentStopsList.contains(currentStopValueField.getText()))
                        {
                            defaultCurrentStopsList.removeElement(currentStopValueField.getText());
                            defaultCurrentStopsList.addElement(newStopValueField.getText());
                        }
                        defaultAllStopsList.addElement(newStopValueField.getText());
                        currentStopValueField.setText(newStopValueField.getText());
                        newStopValueField.setText("");
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        backToTripCreationButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripCardLayout.show(tripCreation,"Card1");
            }
        });
        editTripsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripCardLayout.show(tripCreation,"Card3");
            }
        });
        deleteTripButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tripListForEdition.isSelectionEmpty())
                {
                    try
                    {
                        Trip trip = (Trip)tripListForEdition.getSelectedValue();
                        String sql = "select id from trip where fromlocation=? and tolocation=?";
                        PreparedStatement pstmt = connection.prepareStatement(sql);
                        pstmt.setString(1,trip.StartingPoint());
                        pstmt.setString(2,trip.Destination());
                        ResultSet rs = pstmt.executeQuery();
                        int tripID = 0;
                        if(rs.next())
                        {
                            tripID = rs.getInt("id");
                        }
                        rs.close();
                        pstmt.close();
                        sql = "delete from tripstops where tripid=?";
                        pstmt = connection.prepareStatement(sql);
                        pstmt.setInt(1,tripID);
                        pstmt.executeUpdate();
                        pstmt.close();
                        sql = "update driver set trip=NULL where trip=?";
                        pstmt = connection.prepareStatement(sql);
                        pstmt.setInt(1,tripID);
                        pstmt.executeUpdate();
                        pstmt.close();
                        sql = "delete from trip where id=?";
                        pstmt = connection.prepareStatement(sql);
                        pstmt.setInt(1,tripID);
                        pstmt.executeUpdate();
                        pstmt.close();
                        defaultTripsList.removeElement(truckListForEdition.getSelectedValue());
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        editTripButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tripListForEdition.isSelectionEmpty()) {
                    EditTripWindow editTripWindow = new EditTripWindow((Trip)tripListForEdition.getSelectedValue(),defaultAllStopsList);
                }
            }
        });
        editCurrentAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditAdminField editAdminField = new EditAdminField((Admin)loggedUser);
            }
        });
        editDriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditDriverWindow editDriverWindow = new EditDriverWindow((Driver)loggedUser,defaultTripsList,defaultTruckList);
            }
        });
        managedTripsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<Trip> managedTrips = new DefaultListModel<>();
                try
                {
                    String sql = "select trip_id from trip_manager where admin_login = ?";
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1,loggedUser.login);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next())
                    {
                        for(int i=0;i<defaultTripsList.size();i++)
                        {
                            if(defaultTripsList.get(i).GetID()== rs.getInt(1))
                            {
                                managedTrips.addElement(defaultTripsList.get(i));
                                break;
                            }
                        }
                    }
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
                DefaultListModel<Trip> availableTrips = new DefaultListModel<>();
                for(int i=0;i<defaultTripsList.size();i++)
                {
                    if(!managedTrips.contains(defaultTripsList.get(i)))
                    {
                        availableTrips.addElement(defaultTripsList.get(i));
                    }
                }
                TripManagement tripManagement = new TripManagement(availableTrips,managedTrips);
            }
        });
        userFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userFilter.getSelectedIndex() == 0)
                {
                    listOfNonAdmins.setModel(defaultUsersList);
                }
                else if(userFilter.getSelectedIndex() == 1)
                {
                    DefaultListModel<User> defaultFilteredUsersList = new DefaultListModel<>();
                    for(int i=0;i<defaultUsersList.size();i++)
                    {
                        if(defaultUsersList.get(i).access == User.accessLevel.CLIENT)
                        {
                            defaultFilteredUsersList.addElement(defaultUsersList.get(i));
                        }
                    }
                    listOfNonAdmins.setModel(defaultFilteredUsersList);
                }
                else if(userFilter.getSelectedIndex() == 2)
                {
                    DefaultListModel<User> defaultFilteredUsersList = new DefaultListModel<>();
                    for(int i=0;i<defaultUsersList.size();i++)
                    {
                        if(defaultUsersList.get(i).access == User.accessLevel.DRIVER)
                        {
                            defaultFilteredUsersList.addElement(defaultUsersList.get(i));
                        }
                    }
                    listOfNonAdmins.setModel(defaultFilteredUsersList);
                }
            }
        });
        tripFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tripFilter.getSelectedIndex())
                {
                    case 0:
                    {
                        availableTrips.setModel(defaultTripsList);
                        break;
                    }
                    case 1:
                    {
                        DefaultListModel<Trip> defaultFilteredTripList = new DefaultListModel<>();
                        for(int i=0;i<defaultTripsList.size();i++)
                        {
                            var trip = defaultTripsList.get(i);
                            Boolean used = false;
                            for(int j=0;j<defaultUsersList.size();j++)
                            {
                                if(defaultUsersList.get(j).access == User.accessLevel.DRIVER && ((Driver)defaultUsersList.get(j)).GetTripID() == trip.GetID())
                                {
                                    used = true;
                                    break;
                                }
                            }
                            if(used)
                            {
                                defaultFilteredTripList.addElement(trip);
                            }
                        }
                        availableTrips.setModel(defaultFilteredTripList);
                        break;
                    }
                    case 2:
                    {
                        DefaultListModel<Trip> defaultFilteredTripList = new DefaultListModel<>();
                        for(int i=0;i<defaultTripsList.size();i++)
                        {
                            var trip = defaultTripsList.get(i);
                            Boolean used = false;
                            for(int j=0;j<defaultUsersList.size();j++)
                            {
                                if(defaultUsersList.get(j).access == User.accessLevel.DRIVER && ((Driver)defaultUsersList.get(j)).GetTripID() == trip.GetID())
                                {
                                    used = true;
                                    break;
                                }
                            }
                            if(!used)
                            {
                                defaultFilteredTripList.addElement(trip);
                            }
                        }
                        availableTrips.setModel(defaultFilteredTripList);
                        break;
                    }
                }
            }
        });
        showTripGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StopsInTripsGraph frame = new StopsInTripsGraph(defaultTripsList);
            }
        });
        showRatioStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client_Driver_Admin_ratio frame = new Client_Driver_Admin_ratio(defaultUsersList);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        LoadData();
    }
    public static void ChangeTruckAfterEditing(Truck truckToDelete,Truck truckToAdd)
    {
        defaultTruckList.removeElement(truckToDelete);
        defaultTruckList.addElement(truckToAdd);
        try
        {
            String sql = "select cargo.id from cargo join listofstrings on listofstrings.id=cargo.listofstringsid where listofstrings.stringvalue=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,String.join(",",truckToAdd.TruckCargo().items));
            ResultSet rs = pstmt.executeQuery();
            int cargoID = 0;
            if(rs.next())
            {
                cargoID = rs.getInt("id");
            }
            rs.close();
            pstmt.close();
            sql = "update truck set truckNumber=?,brand=?,cargoId=? where truckNumber=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,truckToAdd.GetNumber());
            pstmt.setString(2,truckToAdd.GetBrand());
            pstmt.setInt(3,cargoID);
            pstmt.setString(4,truckToDelete.GetNumber());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void ChangeCargoAfterEditing(Cargo oldCargo,Cargo newCargo)
    {
        try
        {
            String sql = "select listofstrings.id from cargo join listofstrings on cargo.listofstringsid = listofstrings.id where listofstrings.stringvalue=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,String.join(",",oldCargo.items));
            ResultSet rs = pstmt.executeQuery();
            int listOfStringsID = 0;
            if(rs.next())
            {
                listOfStringsID=rs.getInt("id");
            }
            rs.close();
            pstmt.close();
            sql = "update listofstrings set stringvalue=? where id=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,String.join(",",newCargo.items));
            pstmt.setInt(2,listOfStringsID);
            pstmt.executeUpdate();
            pstmt.close();
            defaultCargoList.removeElement(oldCargo);
            defaultCargoList.addElement(newCargo);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void ChangeTripAfterEditing(Trip oldTrip, Trip newTrip)
    {
        try
        {
            String sql = "select id from trip where fromlocation=? and tolocation=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,oldTrip.StartingPoint());
            pstmt.setString(2,oldTrip.Destination());
            ResultSet rs = pstmt.executeQuery();
            int tripID = 0;
            if(rs.next())
            {
                tripID = rs.getInt("id");
            }
            rs.close();
            sql = "delete from tripstops where tripid=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,tripID);
            pstmt.executeUpdate();
            sql = "update trip set fromlocation=?,tolocation=? where id=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,newTrip.StartingPoint());
            pstmt.setString(2,newTrip.Destination());
            pstmt.setInt(3,tripID);
            pstmt.executeUpdate();
            for(var stop : newTrip.stops)
            {
                sql = "select id from stops where stop=?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,stop.Stop());
                rs = pstmt.executeQuery();
                int stopID = 0;
                if(rs.next())
                {
                    stopID = rs.getInt("id");
                }
                sql = "insert into tripstops values(?,?)";
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,tripID);
                pstmt.setInt(2,stopID);
                pstmt.executeUpdate();
                rs.close();
                pstmt.close();
            }
            defaultTripsList.removeElement(oldTrip);
            defaultTripsList.addElement(newTrip);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void ChangeAdminAfterEdition(Admin oldAdmin, Admin newAdmin)
    {
        try
        {
            String sql = "update admin set name=?,surname=?,contactInfo=?,dateOfBirth=?,login=?,password=? where login=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,newAdmin.name);
            pstmt.setString(2,newAdmin.surname);
            pstmt.setString(3,newAdmin.contactInfo);
            pstmt.setString(4,newAdmin.dateOfBirth);
            pstmt.setString(5,newAdmin.login);
            pstmt.setString(6,newAdmin.password);
            pstmt.setString(7,oldAdmin.login);
            pstmt.executeUpdate();
            pstmt.close();
            defaultUsersList.removeElement(oldAdmin);
            defaultUsersList.addElement(newAdmin);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void ChangeDriverAfterEditing(Driver oldDriver,Driver newDriver)
    {
        try
        {
            String sql = "update driver set name=?,surname=?,contactInfo=?,dateOfBirth=?,login=?,password=?,truck=?,trip=? where login=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,newDriver.name);
            pstmt.setString(2,newDriver.surname);
            pstmt.setString(3,newDriver.contactInfo);
            pstmt.setString(4,newDriver.dateOfBirth);
            pstmt.setString(5,newDriver.login);
            pstmt.setString(6,newDriver.password);
            pstmt.setString(7,newDriver.GetTruckNumber());
            pstmt.setInt(8,newDriver.GetTripID());
            pstmt.setString(9,oldDriver.login);
            pstmt.executeUpdate();
            pstmt.close();
            defaultUsersList.removeElement(oldDriver);
            defaultUsersList.addElement(newDriver);
            loggedUser = null;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void ChangeTripManagement(List<Trip> toManage, List<Trip> toDelete)
    {
        try {
            String sql = "INSERT INTO trip_manager (trip_id,admin_login) VALUES (?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            for (Trip trip : toManage)
            {
                pstmt.setInt(1,trip.GetID());
                pstmt.setString(2,loggedUser.login);
                pstmt.executeUpdate();
            }
            sql = "delete from trip_manager where trip_id=? and admin_login=?";
            pstmt = connection.prepareStatement(sql);
            for(Trip trip : toDelete)
            {
                pstmt.setInt(1,trip.GetID());
                pstmt.setString(2,loggedUser.login);
                pstmt.executeUpdate();
            }
            pstmt.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        cargoIDLabel = new JLabel("ID:" + Cargo.GetID());
    }
}
