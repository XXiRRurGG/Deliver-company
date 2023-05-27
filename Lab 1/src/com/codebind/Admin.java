package com.codebind;

public class Admin extends User {
    //IDK what to implement to admin yet
    public void Info()
    {
        PrintInfo();
    }
    public Admin()
    {
        access = accessLevel.ADMIN;
    }
    public Admin(String name, String surname, String contactInfo, String dateOfBirth)
    {
        super(name,surname,contactInfo,dateOfBirth);
        access = accessLevel.ADMIN;
    }
    public Admin(String name, String surname, String contactInfo, String dateOfBirth, String login, String password)
    {
        super(name,surname,contactInfo,dateOfBirth);
        access = accessLevel.ADMIN;
        this.login = login;
        this.password = password;
    }
    public String AdminInfo()
    {
        String result ="Name:" + name + "\nSurname:" + surname + "\nContact info:" + contactInfo + "\nBirthdate:" + dateOfBirth + "\nLogin:" + login + "\nPassword:" + password;
        return result;
    }
}
