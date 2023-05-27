package com.codebind;

public class Client extends User {
    public void Info()
    {
        PrintInfo();
    }
    public Client()
    {
        access = accessLevel.CLIENT;
    }
    public Client(String name, String surname, String contactInfo, String dateOfBirth)
    {
        super(name,surname,contactInfo,dateOfBirth);
        access = accessLevel.CLIENT;
    }
    public Client(String name, String surname, String contactInfo, String dateOfBirth,String login, String password)
    {
        super(name,surname,contactInfo,dateOfBirth);
        access = accessLevel.CLIENT;
        this.login = login;
        this.password = password;
    }
}
