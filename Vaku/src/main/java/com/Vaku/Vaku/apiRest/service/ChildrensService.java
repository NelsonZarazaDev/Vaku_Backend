package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.ChildrensResponse;
import com.Vaku.Vaku.apiRest.repository.ChildrensRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
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

    public Set<ChildrensResponse> findByChildrenId(Long id){
        return childrensRepository.findByChildrenId(id);
    }

    public PersonsEntity updateChildren(PersonsEntity personRequest,String token){
        Optional<ChildrensEntity> childrensEntityOptional = childrensRepository.findByChilToken(token);
        Optional<PersonsEntity> personsBd = personsRepository.findById(childrensEntityOptional.get().getPersons().getPersId());
        personsBd.get().setPersNames(personRequest.getPersNames());
        personsBd.get().setPersLastNames (personRequest.getPersLastNames());
        personsBd.get().setPersDocument(personRequest.getPersDocument());
        personsBd.get().setDocuments_type(personRequest.getDocuments_type());

        return personsRepository.save(personsBd.get());
    }

}
