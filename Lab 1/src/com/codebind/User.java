package com.codebind;

public class User {
    protected String name;
    protected String surname;
    protected String contactInfo;
    protected String dateOfBirth;
    protected String login;
    protected String password;
    protected enum accessLevel
    {
      CLIENT,
      DRIVER,
      ADMIN;
    }
    protected accessLevel access;

    protected void PrintInfo()
    {
        System.out.println(name+" "+surname);
        System.out.println(dateOfBirth);
        System.out.println(contactInfo);
    }
    public User()
    {

    }
    public String GetAccessLevel()
    {
        return access.toString();
    }
    public User(String name, String surname,String contactInfo,String dateOfBirth)
    {
        this.name = name;
        this.surname = surname;
        this.contactInfo = contactInfo;
        this.dateOfBirth = dateOfBirth;
    }
    public String GetFullName()
    {
        return name + " " + surname;
    }
    public void ChangeInfo(String name, String surname,String contactInfo,String dateOfBirth,String login, String password)
    {
        this.name = name;
        this.surname = surname;
        this.contactInfo = contactInfo;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.password = password;
    }

    public User(String name, String surname, String contactInfo, String dateOfBirth, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.contactInfo = contactInfo;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.password = password;
    }
    public String GetLogin()
    {
        return login;
    }
    public String GetPassword()
    {
        return password;
    }
    public String toString() {
        return name + " " + surname + " " + access.toString();
    }
}
