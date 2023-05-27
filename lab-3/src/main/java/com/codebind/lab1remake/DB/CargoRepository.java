package com.codebind.lab1remake.DB;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CargoRepository extends CrudRepository<Cargo,Long> {
    @Query("SELECT c.id,l.stringValue FROM Cargo c JOIN ListOfStringsTableElement l ON c.listofstrings.id = l.id")
    List<String> getAllCargoStringValues();

    /*
    default String getAllCargosAsStringValue() {
        List<String> cargoStringValues = getAllCargoStringValues();
        StringBuilder sb = new StringBuilder();
        for (String stringValue : cargoStringValues) {
            sb.append(stringValue).append("\n");
        }
        return sb.toString();
    }
     */
}
