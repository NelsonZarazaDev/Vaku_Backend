package com.Vaku.Vaku.apiRest.controller;

import com.Vaku.Vaku.apiRest.model.entity.InventoriesEntity;
import com.Vaku.Vaku.apiRest.model.response.InventoriesResponse;
import com.Vaku.Vaku.apiRest.model.response.VaccinnesResponse;
import com.Vaku.Vaku.apiRest.service.InventoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "inventori")
public class InventoriesController {

    @Autowired
    private InventoriesService inventoriesService;

    @PutMapping(path = "{token}/{emplId}")
    public ResponseEntity <InventoriesEntity> updateInventarie(@RequestBody InventoriesEntity inventoriesRequest,@PathVariable String token, @PathVariable Long emplId){
        return ResponseEntity.ok(inventoriesService.updateInventarie(inventoriesRequest,token,emplId));
    }

    @GetMapping(path = "{token}")
    public ResponseEntity<Set<InventoriesResponse>> getInventoriByToken(@PathVariable String token){
        return ResponseEntity.ok(inventoriesService.getInventoriByToken(token));
    }

    @GetMapping
    public ResponseEntity<Set<InventoriesResponse>> getInventoriAll(){
        return ResponseEntity.ok(inventoriesService.getInventoriAll());
    }
}
