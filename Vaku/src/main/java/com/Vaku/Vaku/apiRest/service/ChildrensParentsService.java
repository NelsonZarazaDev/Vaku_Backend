package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.ChildrensParentsEntity;
import com.Vaku.Vaku.apiRest.model.entity.ParentsEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.repository.ChildrensParentsRepository;
import com.Vaku.Vaku.apiRest.repository.ChildrensRepository;
import com.Vaku.Vaku.apiRest.repository.ParentsRepository;
import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.exception.AlreadyExistsException;
import com.Vaku.Vaku.utils.Constants;
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
    private ParentsRepository parentsRepository;

    Long childId;
    Long parentId;

    public ChildrensEntity CreateChildren (Long personChildren){
        ChildrensEntity children = new ChildrensEntity();
        Optional<PersonsEntity> personsEntityOptional = personsRepository.findById(personChildren);
        Optional<ChildrensEntity> childrensPresent = childrensRepository.findByPersons_PersId(personChildren);
        if(childrensPresent.isPresent()){
            throw new AlreadyExistsException(Constants.CHILD_ALREADY_EXISTS.getMessage());
        }
        children.setPersons(personsEntityOptional.get());
        var data= childrensRepository.save(children);
        childId= data.getChilId();
        ChildrensParentsEntity childrensParentsEntity = CreateChildrenParentChildren(childId,parentId);
        return data;
    }

    public ParentsEntity CreateParent (Long personParent){
        ParentsEntity parent = new ParentsEntity();
        Optional<PersonsEntity> personsEntityOptional = personsRepository.findById(personParent);
        Optional<ParentsEntity> parentPresent = parentsRepository.findByPersons_PersId(personParent);
        if(parentPresent.isPresent()){
            throw new AlreadyExistsException(Constants.PARENT_ALREADY_EXISTS.getMessage());
        }
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
