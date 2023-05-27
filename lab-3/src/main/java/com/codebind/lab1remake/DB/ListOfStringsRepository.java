package com.codebind.lab1remake.DB;

import org.springframework.data.repository.CrudRepository;

public interface ListOfStringsRepository extends CrudRepository<ListOfStringsTableElement,Long> {
}
