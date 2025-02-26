package com.Vaku.Vaku.apiRest.controller;

import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.PersonsResponse;
import com.Vaku.Vaku.apiRest.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "persons")
public class PersonsController {

    @Autowired
    private PersonsService personsService;

    @PostMapping
    public ResponseEntity<PersonsEntity> post(@RequestBody PersonsEntity personsRequest){
        return ResponseEntity.ok(personsService.createPersons(personsRequest));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PersonsEntity> put(@RequestBody PersonsEntity personsRequest, @PathVariable Long id){
        return ResponseEntity.ok(personsService.updatePersons(personsRequest,id));
    }
}
