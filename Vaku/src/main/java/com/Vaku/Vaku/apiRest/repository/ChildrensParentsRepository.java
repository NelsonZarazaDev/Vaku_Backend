package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.ChildrensParentsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChildrensParentsRepository extends CrudRepository<ChildrensParentsEntity,Long> {
}
