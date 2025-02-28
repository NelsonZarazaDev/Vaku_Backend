package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonsRepository extends JpaRepository<PersonsEntity,Long> {

}
