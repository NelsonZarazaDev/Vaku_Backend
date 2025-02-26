package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.ParentsEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.repository.ChildrensRepository;
import com.Vaku.Vaku.apiRest.repository.ParentsRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.utils.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParentsService {

    @Autowired
    private ParentsRepository parentsRepository;

    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private ChildrensParentsService childrensParentsService;

    @Autowired
    private GenerateToken generateToken;

    public ParentsEntity CreateParent (Long personParent){
        Optional<PersonsEntity> personsEntityOptional = personsRepository.findById(personParent);

        ParentsEntity parent = new ParentsEntity();
        parent.setPareToken(generateToken.getTOKEN());
        parent.setPersons(personsEntityOptional.get());
        var data= parentsRepository.save(parent);
        return data;
    }
}
