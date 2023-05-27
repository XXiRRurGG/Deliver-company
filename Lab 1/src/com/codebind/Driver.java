package com.codebind;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

public class Driver extends User {
    private Truck truck;
    //private accessLevel access = accessLevel.DRIVER;
    private Trip currentTrip;
    private List<Trip> unassignedTrips = new ArrayList<Trip>();
    public ArrayList<Trip> GetUnassignedTrips()
    {
        return (ArrayList<Trip>) unassignedTrips;
    }
    public Driver(String name, String surname, String contactInfo, String dateOfBirth)
    {
        super(name,surname,contactInfo,dateOfBirth);
        access = accessLevel.DRIVER;
    }
    public Driver(String name, String surname, String contactInfo, String dateOfBirth,Truck truck,Trip currentTrip,List<Trip> unassignedTrips)
    {
        this(name,surname,contactInfo,dateOfBirth);
        this.truck = truck;
        this.currentTrip = currentTrip;
        this.unassignedTrips.addAll(unassignedTrips);
    }
    public Driver(String name, String surname, String contactInfo, String dateOfBirth,Truck truck,Trip currentTrip,List<Trip> unassignedTrips, String login, String password)
    {
        this(name,surname,contactInfo,dateOfBirth);
        this.truck = truck;
        this.currentTrip = currentTrip;
        this.unassignedTrips.addAll(unassignedTrips);
        this.login = login;
        this.password = password;
    }
    public void ChangeActiveTrip(Trip trip)
    {
        unassignedTrips.remove(trip);
        var temp = currentTrip;
        currentTrip = trip;
        unassignedTrips.add(temp);
    }
    public void Info()
    {
        PrintInfo();
        truck.Info();
        System.out.println("Current destination:" + currentTrip.Destination());
        System.out.println("Unassigned trips:");
        for(var item : unassignedTrips)
        {
            item.Info();
        }
    }
    public int GetTripID()
    {
        return currentTrip.GetID();
    }
    public String GetTruckNumber()
    {
        return  truck.GetNumber();
    }
    public void DeleteTruck()
    {
        truck = null;
    }
    public String DriverInfo()
    {
        String result ="Name:" + name + "\nSurname:" + surname + "\nContact info:" + contactInfo + "\nBirthdate:" + dateOfBirth + "\nLogin:" + login + "\nPassword:" + password + "\n";
        result += truck.TruckInfo() + "\n";
        result += currentTrip.TripInfo();
        return result;
    }
    public Trip CurrentTrip()
    {
        return currentTrip;
    }
}
