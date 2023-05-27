package com.codebind.lab1remake.DB;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
    public String name;
    public String surname;
    public String contactInfo;
    public String dateOfBirth;
    @Id
    public String login;
    public String password;
}
