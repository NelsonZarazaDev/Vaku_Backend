package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.response.ChildrensResponse;
import com.Vaku.Vaku.apiRest.model.response.OverdueAppointmentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface ChildrensRepository extends JpaRepository<ChildrensEntity,Long> {
    @Query(value = """
            SELECT ch.chil_id AS chilId, ch.chil_token AS chilToken,
                   p.pers_id AS persId, p.pers_names AS persNames,
                   p.pers_document AS persDocument,
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

    Optional<ChildrensEntity> findByPersons_PersId(Long persId);

    @Query(value = """
                SELECT DISTINCT
                    -- Datos del niño
                    ch.chil_id,
                    ch.chil_token,
                    p_child.pers_names AS child_names,
                    p_child.pers_last_names AS child_last_names,
                    p_child.pers_document AS childDocument,
                    p_child.pers_date_birth AS child_birth_date,
            
                    -- Datos del padre
                    p_parent.pers_names AS parent_names,
                    p_parent.pers_last_names AS parent_last_names,
                    p_parent.pers_email AS parent_email,
                    p_parent.pers_phone AS parent_phone,
            
                    -- Datos de la vacuna aplicada
                    va.vaap_id,
                    va.vaap_next_appointment_date,
                    va.vaap_applied,
                    va.vaap_date_application,
                    va.vaap_time_application,
                    va.vaap_token
            
                FROM vaccines_applied va
                INNER JOIN childrens ch ON va.chil_id = ch.chil_id
                INNER JOIN persons p_child ON ch.pers_id = p_child.pers_id
            
                -- Relación con padres
                INNER JOIN childrens_parents cp ON ch.chil_id = cp.chil_id
                INNER JOIN parents pa ON cp.pare_id = pa.pare_id
                INNER JOIN persons p_parent ON pa.pers_id = p_parent.pers_id
            
                -- Citas vencidas
                WHERE va.vaap_next_appointment_date < CURRENT_DATE;
            """, nativeQuery = true)
    Set<OverdueAppointmentResponse> findByNextAppointmentDateBefore();
}
