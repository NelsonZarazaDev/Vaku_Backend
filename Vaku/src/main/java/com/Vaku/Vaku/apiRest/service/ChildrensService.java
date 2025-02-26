package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.repository.ChildrensRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.utils.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChildrensService {

    @Autowired
    private ChildrensRepository childrensRepository;

    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private ChildrensParentsService childrensParentsService;

    @Autowired
    private GenerateToken generateToken;

    public ChildrensEntity CreateChildren (Long personChildren){
        Optional<PersonsEntity> personsEntityOptional = personsRepository.findById(personChildren);

        ChildrensEntity children = new ChildrensEntity();
        children.setChilToken(generateToken.getTOKEN());
        children.setPersons(personsEntityOptional.get());
        var data= childrensRepository.save(children);
        return data;
    }
}
