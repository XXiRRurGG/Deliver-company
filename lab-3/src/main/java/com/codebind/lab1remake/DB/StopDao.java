package com.codebind.lab1remake.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class StopDao {
    @Autowired private StopsRepository repo;

    public List<Stops> getAllStops() throws SQLException {
        return repo.getAllStops();
    }

    public Stops getStopByName(String name) throws SQLException {
        return repo.findByStop(name);
    }

    public void insertStop(String name) throws SQLException {
        Stops stop = new Stops();
        stop.setStop(name);
        repo.save(stop);
    }

    public void updateStop(String name,String newName) throws SQLException {
        Stops stopToUpdate = repo.findByStop(name);
        if (stopToUpdate != null) {
            stopToUpdate.setStop(newName);
            repo.save(stopToUpdate);
        }
    }

    public void deleteStop(String name) throws SQLException {
        Stops stopToDelete = repo.findByStop(name);
        if(stopToDelete!=null)
            repo.delete(stopToDelete);
    }
}
