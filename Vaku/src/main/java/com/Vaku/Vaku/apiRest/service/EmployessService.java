package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.EmployeesEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.repository.ChildrensRepository;
import com.Vaku.Vaku.apiRest.repository.EmployessRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.utils.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class EmployessService {

    @Autowired
    private EmployessRepository employessRepository;

    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private GenerateToken generateToken;

    public EmployeesEntity CreateEmployee (String role,Long personEmployee){
        Optional<PersonsEntity> personsEntityOptional = personsRepository.findById(personEmployee);

        Date date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

        EmployeesEntity employee = new EmployeesEntity();
        employee.setEmplDateAdmission(LocalDate.parse(dateFormat.format(date)));
        employee.setEmplState(true);
        employee.setEmplToken(generateToken.getTOKEN());
        employee.setPersons(personsEntityOptional.get());
        employee.setEmplRole(role);
        return employessRepository.save(employee);
    }
}
