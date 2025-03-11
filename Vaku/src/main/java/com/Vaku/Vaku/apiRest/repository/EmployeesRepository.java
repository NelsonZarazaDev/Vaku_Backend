package com.Vaku.Vaku.apiRest.repository;

import com.Vaku.Vaku.apiRest.model.entity.EmployeesEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.EmployeesResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface EmployeesRepository extends CrudRepository<EmployeesEntity,Long> {
    Optional<EmployeesEntity> findByEmplToken(String emplToken);

    @Query(value = """
            SELECT e.empl_date_admission AS emplDateAdmission,e.empl_state AS emplState,
                   p.pers_id AS persId, p.pers_names AS persNames, 
                   p.pers_last_names AS persLastNames, p.pers_document AS persDocument, 
                   p.pers_sex AS persSex, p.pers_address AS persAddress, 
                   p.pers_date_birth AS persDateBirth, p.pers_role AS persRole, 
                   p.pers_email AS persEmail, p.pers_phone AS persPhone,
                       c.city_name AS CityName, d.depa_name AS DepaName
            FROM employees e
            INNER JOIN persons p ON e.pers_id = p.pers_id
            INNER JOIN citys c ON p.city_id = c.city_id
            INNER JOIN departments d ON c.depa_id = d.depa_id
            WHERE e.empl_token = :emplToken
            """, nativeQuery = true)
    Set<EmployeesResponse> findByJsonEmployeeToken(String emplToken);


    @Query(value = """
            SELECT e.empl_token AS emplToken,e.empl_state AS emplState,
                               p.pers_names AS persNames,
                               p.pers_last_names AS persLastNames, p.pers_role AS persRole
            FROM employees e
                            INNER JOIN persons p ON e.pers_id = p.pers_id
                            INNER JOIN citys c ON p.city_id = c.city_id
                            INNER JOIN departments d ON c.depa_id = d.depa_id
            WHERE p.pers_role='Jefe de enfermería' OR p.pers_role='Enfermera'
            """, nativeQuery = true)
    Set<EmployeesResponse> findByAllEmployee();

//    Optional<EmployeesEntity> findByPersId(Long persId);
      Optional<EmployeesEntity> findByPersons(PersonsEntity persons);


}
