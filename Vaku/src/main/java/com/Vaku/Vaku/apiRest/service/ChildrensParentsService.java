package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.ChildrensParentsEntity;
import com.Vaku.Vaku.apiRest.model.entity.ParentsEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.repository.ChildrensParentsRepository;
import com.Vaku.Vaku.apiRest.repository.ChildrensRepository;
import com.Vaku.Vaku.apiRest.repository.ParentsRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.utils.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChildrensParentsService {
    @Autowired
    private ChildrensParentsRepository childrensParentsRepository;

    @Autowired
    private ChildrensRepository childrensRepository;

    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private ParentsRepository parentsRepository;

    Long childId;
    Long parentId;

    public ChildrensEntity CreateChildren (Long personChildren){
        Optional<PersonsEntity> personsEntityOptional = personsRepository.findById(personChildren);

        ChildrensEntity children = new ChildrensEntity();
        children.setChilToken(generateToken.getTOKEN());
        children.setPersons(personsEntityOptional.get());
        var data= childrensRepository.save(children);
        childId= data.getChilId();
        ChildrensParentsEntity childrensParentsEntity = CreateChildrenParentChildren(childId,parentId);
        return data;
    }

    public ParentsEntity CreateParent (Long personParent){
        Optional<PersonsEntity> personsEntityOptional = personsRepository.findById(personParent);

        ParentsEntity parent = new ParentsEntity();
        parent.setPareToken(generateToken.getTOKEN());
        parent.setPersons(personsEntityOptional.get());
        var data= parentsRepository.save(parent);
        parentId= data.getPareId();
        return data;
    }


    public ChildrensParentsEntity CreateChildrenParentChildren (Long childId, Long parentId){
        Optional<ChildrensEntity> personsChildrenEntityOptional = childrensRepository.findById(childId);
        Optional<ParentsEntity> personsParentEntityOptional = parentsRepository.findById(parentId);

        ChildrensParentsEntity childrensParents = new ChildrensParentsEntity();
        childrensParents.setChildrens(personsChildrenEntityOptional.get());
        childrensParents.setParents(personsParentEntityOptional.get());
        return childrensParentsRepository.save(childrensParents);
    }
}
