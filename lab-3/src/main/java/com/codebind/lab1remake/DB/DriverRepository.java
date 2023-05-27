package com.codebind.lab1remake.DB;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface DriverRepository extends CrudRepository<Driver,String> {
    default List<Driver> getAllDrivers() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @Modifying
    @Query("update Driver d set d.name = ?1, d.surname = ?2, d.contactInfo = ?3, d.dateOfBirth = ?4, d.login = ?5," +
            "d.password = ?6, d.truck = ?7, d.trip = ?8 where d.login = ?9")
    void setDriverInfoByLogin(String name, String surname, String contactInfo, String dateOfBirth,String loginToSet, String passsword, String truck, Long trip, String loginToChange);
}
