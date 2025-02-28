package com.Vaku.Vaku.apiRest.controller;

import com.Vaku.Vaku.apiRest.model.entity.ChildrensEntity;
import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.response.ChildrensResponse;
import com.Vaku.Vaku.apiRest.service.ChildrensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "children")
public class ChildrensController {

    @Autowired
    private ChildrensService childrensService;

    @GetMapping(path = "{id}")
    public ResponseEntity<Set<ChildrensResponse>> findByChildrenId(@PathVariable Long id){
        var response= childrensService.findByChildrenId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "{token}")
    public ResponseEntity<PersonsEntity> put(@RequestBody PersonsEntity personRequest, @PathVariable String token){
        return ResponseEntity.ok(childrensService.updateChildren(personRequest,token));
    }
}
