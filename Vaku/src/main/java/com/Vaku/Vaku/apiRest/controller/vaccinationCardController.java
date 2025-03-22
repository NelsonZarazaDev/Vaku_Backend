package com.Vaku.Vaku.apiRest.controller;

import com.Vaku.Vaku.apiRest.model.response.VaccinationCardResponse;
import com.Vaku.Vaku.apiRest.service.VaccinationCardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "vaccinesCard")
public class vaccinationCardController {

    @Autowired
    private VaccinationCardService vaccinationCardService;

    @Operation(summary = "Bring up the vaccine information by id")
    @GetMapping(path = "{document}")
    public ResponseEntity<Set<VaccinationCardResponse>> getVaccinationCard(@PathVariable String document){
        return ResponseEntity.ok(vaccinationCardService.getVaccinationCard(document));
    }
}
