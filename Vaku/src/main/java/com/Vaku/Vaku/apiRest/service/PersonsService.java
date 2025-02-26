package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.PersonsResponse;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PersonsService  {
    @Autowired
    private PersonsRepository personsRepository;

    public PersonsEntity createPersons(PersonsEntity personsRequest){
        return personsRepository.save(personsRequest);
    }

    public PersonsEntity updatePersons(PersonsEntity personsRequest, Long id){
        Optional<PersonsEntity> personsBd = personsRepository.findById(id);
        personsBd.get().setPersNames(personsRequest.getPersNames());
        personsBd.get().setPersLastNames (personsRequest.getPersLastNames());
        personsBd.get().setPersDocument(personsRequest.getPersDocument());

        return personsRepository.save(personsBd.get());
    }
}
