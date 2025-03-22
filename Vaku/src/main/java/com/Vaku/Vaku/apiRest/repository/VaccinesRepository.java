package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.VaccinesEntity;
import com.Vaku.Vaku.apiRest.model.response.VaccinesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface VaccinesRepository extends JpaRepository<VaccinesEntity,Long> {
    @Query(value = """
            SELECT v.vacc_name AS vaccName,v.vacc_age_dose AS vaccAgeDose,v.vacc_dosage AS vaccDosage,
                               i.inve_laboratory AS inveLaboratory,i.inve_lot AS inveLot,i.inve_quantity AS inveQuantity
            FROM vaccines v
            INNER JOIN inventories i ON v.inve_id = i.inve_id
            WHERE v.vacc_id = :vaccId
            """, nativeQuery = true)
    Set<VaccinesResponse> findByVaccines(Long vaccId);
}
