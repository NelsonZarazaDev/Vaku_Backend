package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.exception.AlreadyExistsException;
import com.Vaku.Vaku.utils.Constants;
import jakarta.transaction.Transactional;
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

    @Transactional
    public List<PersonsEntity> createPersons(List<PersonsEntity> personsRequest) {
        List<PersonsEntity> savedPersonsList = new ArrayList<>();
        for (PersonsEntity person : personsRequest) {
            Optional<PersonsEntity> personsDataBd = personsRepository.findByPersDocument(person.getPersDocument());
            if (personsDataBd.isPresent()) {
                DataId = personsDataBd.get().getPersId();
                role = personsDataBd.get().getPersRole();
            } else {
                if (person.getPersPassword() != null) {
                    person.setPersPassword(passwordEncoder.encode(person.getPersPassword()));
                }
                PersonsEntity savedPersons = personsRepository.save(person);

                savedPersonsList.add(savedPersons);
                DataId = savedPersons.getPersId();
                role = savedPersons.getPersRole();
            }

            if ("Madre".equals(role) || "Padre".equals(role)) {
                childrensParentsService.CreateParent(DataId);
            } else if ("Niño".equals(role)) {
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
