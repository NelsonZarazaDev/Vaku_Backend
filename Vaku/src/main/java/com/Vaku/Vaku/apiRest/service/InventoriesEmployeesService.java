package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.EmployeesEntity;
import com.Vaku.Vaku.apiRest.model.entity.InventoriesEmployeesEntity;
import com.Vaku.Vaku.apiRest.model.entity.InventoriesEntity;
import com.Vaku.Vaku.apiRest.repository.EmployeesRepository;
import com.Vaku.Vaku.apiRest.repository.InventoriesEmployeesRepository;
import com.Vaku.Vaku.apiRest.repository.InventoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class InventoriesEmployeesService {

    @Autowired
    private InventoriesEmployeesRepository inventoriesEmployeesRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private InventoriesRepository inventoriesRepository;

    public InventoriesEmployeesEntity createInventarie(Long emplId, Long inveId){
        Date date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

        InventoriesEmployeesEntity inventoriesEmployees = new InventoriesEmployeesEntity();
        EmployeesEntity employees = employeesRepository.findById(emplId).get();
        InventoriesEntity inventories = inventoriesRepository.findById(emplId).get();


        inventoriesEmployees.setEmployees(employees);
        inventoriesEmployees.setInventories(inventories);
        inventoriesEmployees.setInemDate(LocalDate.parse(dateFormat.format(date)));

        return inventoriesEmployeesRepository.save(inventoriesEmployees);
    }
}
