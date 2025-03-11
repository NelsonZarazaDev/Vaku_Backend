package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.EmployeesEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.EmployeesResponse;
import com.Vaku.Vaku.apiRest.repository.EmployeesRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class EmployessService {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private PersonsRepository personsRepository;

    public EmployeesEntity CreateEmployee (Long personEmployee){
        Optional<PersonsEntity> personsEntityOptional = personsRepository.findById(personEmployee);

        Date date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

        EmployeesEntity employee = new EmployeesEntity();
        employee.setEmplDateAdmission(LocalDate.parse(dateFormat.format(date)));
        employee.setEmplState(true);
        employee.setPersons(personsEntityOptional.get());
        return employeesRepository.save(employee);
    }

    public PersonsEntity updateEmployees(PersonsEntity personRequest,String token, boolean state){
        Optional<EmployeesEntity> employeesEntityOptional = employeesRepository.findByEmplToken(token);

        EmployeesEntity employeesBd = employeesEntityOptional.get();
        PersonsEntity personsBd = employeesBd.getPersons();

        personsBd.setPersNames(personRequest.getPersNames());
        personsBd.setPersLastNames(personRequest.getPersLastNames());
        personsBd.setPersEmail(personRequest.getPersEmail());
        personsBd.setPersPhone(personRequest.getPersPhone());
        personsBd.setPersPassword(personRequest.getPersPassword());
        personsBd.setPersRole(personRequest.getPersRole());

        employeesBd.setEmplState(state);

        personsRepository.save(personsBd);
        employeesRepository.save(employeesBd);

        return personsBd;
    }

    public Set<EmployeesResponse> findByJsonEmployeeToken(String token){
        return employeesRepository.findByJsonEmployeeToken(token);
    }

    public Set<EmployeesResponse> findByAllEmployee(){
        return employeesRepository.findByAllEmployee();
    }
}
