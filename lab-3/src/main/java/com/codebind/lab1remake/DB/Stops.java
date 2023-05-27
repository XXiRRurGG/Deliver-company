package com.codebind.lab1remake.DB;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stops")
public class Stops{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Stop")
    private String stop;

    @OneToMany(mappedBy = "stop", cascade = CascadeType.ALL)
    private List<TripStop> tripStops = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getStop() {
        return stop;
    }
}
