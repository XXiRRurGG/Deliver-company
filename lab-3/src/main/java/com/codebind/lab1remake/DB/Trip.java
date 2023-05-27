package com.codebind.lab1remake.DB;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String fromLocation;
    public String toLocation;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<TripStop> tripStops = new ArrayList<>();
}