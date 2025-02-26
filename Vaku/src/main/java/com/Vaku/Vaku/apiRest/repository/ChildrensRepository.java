package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChildrensRepository extends CrudRepository<ChildrensEntity,Long> {
}
