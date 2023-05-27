package com.codebind.lab1remake.DB;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface ClientRepository extends CrudRepository<Client,String> {
    default List<Client> getAllClients() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @Modifying
    @Query("update Client d set d.name = ?1, d.surname = ?2, d.contactInfo = ?3, d.dateOfBirth = ?4, d.login = ?5," +
            "d.password = ?6 where d.login = ?7")
    void setClientInfoByLogin(String name, String surname, String contactInfo, String dateOfBirth,String loginToSet, String passsword,String loginToChange);

}
