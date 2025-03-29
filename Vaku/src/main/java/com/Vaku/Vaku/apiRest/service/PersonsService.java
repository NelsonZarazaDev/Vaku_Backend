package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.exception.AlreadyExistsException;
import com.Vaku.Vaku.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonsService {
    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private ChildrensParentsService childrensParentsService;


    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployessService employessService;

    Long DataId;
    String role;

    public List<PersonsEntity> createPersons(List<PersonsEntity> personsRequest) {
        List<PersonsEntity> savedPersonsList = new ArrayList<>();
        for (PersonsEntity person : personsRequest) {
            Optional<PersonsEntity> personsDataBd = personsRepository.findByPersDocument(person.getPersDocument());
            if (personsDataBd.isPresent()) {
                DataId = personsDataBd.get().getPersId();
                role = personsDataBd.get().getPersRole();
            }
            else{
                PersonsEntity savedPersons = personsRepository.save(person);
                if (person.getPassword().equals(null)) {
                    person.setPersPassword(passwordEncoder.encode(person.getPersPassword()));
                    savedPersonsList.add(savedPersons);
                    DataId = savedPersons.getPersId();
                    role = savedPersons.getPersRole();
                } else {
                    savedPersonsList.add(savedPersons);
                    DataId = savedPersons.getPersId();
                    role = savedPersons.getPersRole();
                }
            }

            if (role.equals("Madre") || role.equals("Padre")) {
                childrensParentsService.CreateParent(DataId);
            } else if (role.equals("Niño")) {
                childrensParentsService.CreateChildren(DataId);
            } else {
                employessService.CreateEmployee(DataId);
            }
        }
        return savedPersonsList;
    }

    public PersonsEntity updatePersons(PersonsEntity personsRequest, Long id) {
        Optional<PersonsEntity> personsBd = personsRepository.findById(id);
        personsBd.get().setPersNames(personsRequest.getPersNames());
        personsBd.get().setPersLastNames(personsRequest.getPersLastNames());
        personsBd.get().setPersDocument(personsRequest.getPersDocument());

        return personsRepository.save(personsBd.get());
    }
}
