package com.codebind.lab1remake.DB;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface TripRepository extends CrudRepository<Trip,Long> {
    default List<Trip> getAllTrips() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
