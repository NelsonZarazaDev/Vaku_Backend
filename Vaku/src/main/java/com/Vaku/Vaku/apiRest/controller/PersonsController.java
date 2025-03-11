package com.Vaku.Vaku.apiRest.controller;

import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.service.PersonsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "persons")
public class PersonsController {

    @Autowired
    private PersonsService personsService;

    @Operation(summary = "Create personal information for nurses, head nurse, parents and children.")
    @PostMapping
    public ResponseEntity<List<PersonsEntity>> post(@Valid @RequestBody List<PersonsEntity> personsRequest){
        return ResponseEntity.ok(personsService.createPersons(personsRequest));
    }
}
