package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.ChildrensResponse;
import com.Vaku.Vaku.apiRest.repository.ChildrensRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.exception.AlreadyExistsException;
import com.Vaku.Vaku.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ChildrensService {

    @Autowired
    private ChildrensRepository childrensRepository;

    @Autowired
    private PersonsRepository personsRepository;

    public PersonsEntity updateChildren(PersonsEntity personRequest,String token){
        Optional<ChildrensEntity> childrensEntityOptional = childrensRepository.findByChilToken(token);
        if(childrensEntityOptional.isPresent()){
            throw new AlreadyExistsException(Constants.CHILD_NOT_EXISTS.getMessage());
        }
        Optional<PersonsEntity> personsBd = personsRepository.findById(childrensEntityOptional.get().getPersons().getPersId());
        personsBd.get().setPersNames(personRequest.getPersNames());
        personsBd.get().setPersLastNames (personRequest.getPersLastNames());
        personsBd.get().setPersDocument(personRequest.getPersDocument());

        return personsRepository.save(personsBd.get());
    }

}
