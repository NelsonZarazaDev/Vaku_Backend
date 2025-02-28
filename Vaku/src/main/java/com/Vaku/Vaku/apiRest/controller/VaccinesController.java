package com.Vaku.Vaku.apiRest.controller;

import com.Vaku.Vaku.apiRest.model.entity.VaccinesAppliedEntity;
import com.Vaku.Vaku.apiRest.model.entity.VaccinnesEntity;
import com.Vaku.Vaku.apiRest.model.response.VaccinnesResponse;
import com.Vaku.Vaku.apiRest.service.VaccinesAppliedService;
import com.Vaku.Vaku.apiRest.service.VaccinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "vaccinnes")
public class VaccinesController {

    @Autowired
    private VaccinesService vaccinesService;

    @GetMapping(path = "{id}")
    public ResponseEntity<Set<VaccinnesResponse>> getVaccines(@PathVariable Long id){
        return ResponseEntity.ok(vaccinesService.getVaccines(id));
    }
}
