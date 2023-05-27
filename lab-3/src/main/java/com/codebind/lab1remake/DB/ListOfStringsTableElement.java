package com.codebind.lab1remake.DB;

import jakarta.persistence.*;

@Entity
@Table(name = "listofstrings")
public class ListOfStringsTableElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "StringValue")
    public String stringValue;

    @OneToOne(mappedBy = "listofstrings",cascade = CascadeType.ALL)
    private Cargo cargo;

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
