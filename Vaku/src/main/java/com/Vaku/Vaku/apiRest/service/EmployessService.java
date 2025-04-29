package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.EmployeesEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.EmployeesResponse;
import com.Vaku.Vaku.apiRest.repository.EmployeesRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.exception.AlreadyExistsException;
import com.Vaku.Vaku.utils.Constants;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeesEntity CreateEmployee (Long personEmployee){
        EmployeesEntity employee = new EmployeesEntity();
        Optional<PersonsEntity> personsEntityOptional = personsRepository.findById(personEmployee);
        Optional<EmployeesEntity> employeePresent = employeesRepository.findByPersons_PersId(personEmployee);

        if(employeePresent.isPresent()){
            throw new AlreadyExistsException(Constants.EMPLOYEE_ALREADY_EXISTS.getMessage());
        }

        PersonsEntity person = personsEntityOptional.get();

        // 🔐 Encriptamos la contraseña si no está encriptada
        String plainPassword = person.getPersPassword();
        if (!plainPassword.startsWith("$2a$")) { // Evitamos volver a encriptar si ya está hasheada
            String encryptedPassword = passwordEncoder.encode(plainPassword);
            person.setPersPassword(encryptedPassword);
        }

        personsRepository.save(person);

        Date date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");


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

        if (personRequest.getPersPassword() != null && !personRequest.getPersPassword().isEmpty()) {
            String encryptedPassword = passwordEncoder.encode(personRequest.getPersPassword());
            personsBd.setPersPassword(encryptedPassword);
        }
        personsBd.setPersRole(personRequest.getPersRole());


        employeesBd.setEmplState(state);

        employeesRepository.save(employeesBd);

        return personsBd;
    }

    public Set<EmployeesResponse> findByJsonEmployeeEmail(String Email){
        return employeesRepository.findByJsonEmployeeEmail(Email);
    }

    public Set<EmployeesResponse> findByAllEmployee(){
        return employeesRepository.findByAllEmployee();
    }
}
