package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.PersonsResponse;
import org.springframework.data.repository.CrudRepository;

public interface PersonsRepository extends CrudRepository<PersonsEntity,Long> {
}
