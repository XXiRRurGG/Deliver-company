package com.codebind.lab1remake.DB;

import jakarta.persistence.*;

@Entity
@Table(name = "Tripstops")
public class TripStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tripid")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "stopid")
    private Stops stop;

    // constructors, getters, setters
}