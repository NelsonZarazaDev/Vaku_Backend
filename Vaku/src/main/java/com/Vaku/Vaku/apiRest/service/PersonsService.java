package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensParentsEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.PersonsResponse;
import com.Vaku.Vaku.apiRest.repository.ChildrensRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.utils.GenerateToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonsService  {
    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private ChildrensParentsService childrensParentsService;


    @Autowired
    private EmployessService employessService;

    public List<PersonsEntity> createPersons(List<PersonsEntity> personsRequest){
        List<PersonsEntity> savedPersonsList = new ArrayList<>();
        for (PersonsEntity person : personsRequest){
            PersonsEntity savedPersons = personsRepository.save(person);
            savedPersonsList.add(savedPersons);

            Long DataId=savedPersons.getPersId();
            String role = savedPersons.getPersRole();


            if (role.equals("Madre") || role.equals("Padre")) {
                childrensParentsService.CreateParent(DataId);
            } else if (role.equals("Niño")){
                childrensParentsService.CreateChildren(DataId);
            }
            else {
                employessService.CreateEmployee(role,DataId);
            }
        }

        return savedPersonsList;
    }

    public PersonsEntity updatePersons(PersonsEntity personsRequest, Long id){
        Optional<PersonsEntity> personsBd = personsRepository.findById(id);
        personsBd.get().setPersNames(personsRequest.getPersNames());
        personsBd.get().setPersLastNames (personsRequest.getPersLastNames());
        personsBd.get().setPersDocument(personsRequest.getPersDocument());

        return personsRepository.save(personsBd.get());
    }
}
