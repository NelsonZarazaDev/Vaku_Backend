package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.ChildrensResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface ChildrensRepository extends JpaRepository<ChildrensEntity,Long> {
    @Query(value = """
            SELECT ch.chil_id AS chilId, ch.chil_token AS chilToken, 
                   p.pers_id AS persId, p.pers_names AS persNames, 
                   p.pers_last_names AS persLastNames, p.pers_document AS persDocument, 
                   p.pers_sex AS persSex, p.pers_address AS persAddress, 
                   p.pers_date_birth AS persDateBirth, p.pers_role AS persRole, 
                   p.pers_email AS persEmail, p.pers_phone AS persPhone,
                       c.city_name AS CityName, d.depa_name AS DepaName
            FROM childrens ch
            INNER JOIN persons p ON ch.pers_id = p.pers_id
            INNER JOIN citys c ON p.city_id = c.city_id
            INNER JOIN departments d ON c.depa_id = d.depa_id
            WHERE p.pers_id = :persId
            """, nativeQuery = true)
    Set<ChildrensResponse> findByChildrenId(@Param("persId") Long persId);

    Optional<ChildrensEntity> findByChilToken(String chilToken);


}
