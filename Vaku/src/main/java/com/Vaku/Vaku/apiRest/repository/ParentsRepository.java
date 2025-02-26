package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.ParentsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ParentsRepository extends CrudRepository<ParentsEntity,Long> {
}
