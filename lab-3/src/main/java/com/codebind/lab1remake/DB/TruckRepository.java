package com.codebind.lab1remake.DB;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface TruckRepository extends CrudRepository<Truck,String> {
    default List<Truck> getAllTrucks() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    default Truck getTruck(String  id) {
        Optional<Truck> optionalTruck = findById(id);
        Truck truck = optionalTruck.orElseThrow(() -> new RuntimeException("Truck not found"));
        return truck;
    }
    @Transactional
    @Modifying
    @Query("update Truck t set t.truckNumber = ?1, t.brand = ?2, t.cargoId = ?3 where t.truckNumber = ?4")
    void setTruckInfoByNumber(String truckNumberToSet, String brand, Long cargoId, String truckNumberToChange);
}
