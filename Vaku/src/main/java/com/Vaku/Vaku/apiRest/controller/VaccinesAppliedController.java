package com.Vaku.Vaku.apiRest.controller;

import com.Vaku.Vaku.apiRest.model.entity.PersonsEntity;
import com.Vaku.Vaku.apiRest.model.entity.VaccinesAppliedEntity;
import com.Vaku.Vaku.apiRest.repository.VaccinesAppliedRepository;
import com.Vaku.Vaku.apiRest.service.PersonsService;
import com.Vaku.Vaku.apiRest.service.VaccinesAppliedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "vaccineApplied")
public class VaccinesAppliedController {

    @Autowired
    private VaccinesAppliedService vaccinesAppliedService;

    @PostMapping
    public ResponseEntity<VaccinesAppliedEntity> createVaccinesAppliedEntity(@RequestBody VaccinesAppliedEntity vaccinesApplied){
        return ResponseEntity.ok(vaccinesAppliedService.createVaccinesAppliedEntity(vaccinesApplied));
    }
}
