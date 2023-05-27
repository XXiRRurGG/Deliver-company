package com.codebind.lab1remake.DB;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface StopsRepository extends CrudRepository<Stops,Integer> {
    default List<Stops> getAllStops() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    Stops findByStop(String stop);
    @Query("SELECT s FROM Stops s INNER JOIN TripStop ts ON s.id = ts.stop.id WHERE ts.trip.id = :tripId")
    List<Stops> findAllByTripId(@Param("tripId") Long tripId);

    default List<Stops> findAvailableStopsForTrip(Long tripId) {
        // Get all stops
        List<Stops> allStops = getAllStops();

        // Get stops that are already in the trip
        List<Stops> stopsInTrip = findAllByTripId(tripId);

        // Filter available stops
        return allStops.stream().filter(stop -> !stopsInTrip.contains(stop)).collect(Collectors.toList());
    }

}
