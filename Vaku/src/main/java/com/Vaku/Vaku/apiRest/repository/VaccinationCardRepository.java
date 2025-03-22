package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.response.VaccinationCardResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface VaccinationCardRepository extends JpaRepository<ChildrensEntity, Long> {
    @Query(value = """
            SELECT
                ch.chil_id AS chChil_id,
                ch.chil_token AS chChild_token,
                p.pers_id AS chPers_id,
                p.pers_names AS chPers_names,
                p.pers_last_names AS chPers_last_names,
                p.pers_document AS chPers_document,
                p.pers_sex AS chPers_sex,
                p.pers_address AS chPers_address,
                p.pers_date_birth AS chPers_date_birth,
            
                c.city_name AS chCity_name,
                d.depa_name AS chDepa_name,
            
                ppa.pers_id AS ppaPare_id,
                ppa.pers_names AS ppaPers_names,
                ppa.pers_last_names AS ppaPers_last_names,
                ppa.pers_document AS ppaPers_document,
                ppa.pers_role AS ppaPers_role,
                ppa.pers_email AS ppaPers_email,
                ppa.pers_phone AS ppaPers_phone,
            
                vp.vaap_id AS vaap_id,
                vp.vaap_next_appointment_date AS vaap_next_appointment_date,
                vp.vaap_date_application AS vaap_date_application,
                vp.empl_id AS vaap_empl_id,
                vp.vaap_token AS vaap_token,
            
                v.vacc_id AS vVacc_id,
                v.vacc_name AS vVacc_name,
            
                pe.pers_names AS pePers_names,
                pe.pers_last_names AS pePers_last_names
            FROM childrens ch
            INNER JOIN persons p ON ch.pers_id = p.pers_id
            INNER JOIN childrens_parents cp ON ch.chil_id = cp.chil_id
            INNER JOIN parents pa ON cp.pare_id = pa.pare_id
            INNER JOIN persons ppa ON pa.pers_id = ppa.pers_id -- Person de Parents
            INNER JOIN vaccines_applied vp ON vp.chil_id = ch.chil_id
            INNER JOIN vaccines v ON v.vacc_id = vp.vacc_id
            INNER JOIN employees e ON vp.empl_id = e.empl_id
            INNER JOIN persons pe ON e.pers_id = pe.pers_id -- Person de Employees
            INNER JOIN citys c ON p.city_id = c.city_id
            INNER JOIN departments d ON c.depa_id = d.depa_id
            WHERE ch.chil_id = :childId
            ORDER BY vp.vaap_date_application ASC, vp.vaap_time_application ASC
            """, nativeQuery = true)
    Set<VaccinationCardResponse> getVaccinationCard(Long childId);
}
