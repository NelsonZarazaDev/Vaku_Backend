package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.InventoriesEntity;
import com.Vaku.Vaku.apiRest.model.entity.VaccinesAppliedEntity;
import com.Vaku.Vaku.apiRest.model.entity.VaccinnesEntity;
import com.Vaku.Vaku.apiRest.repository.InventoriesRepository;
import com.Vaku.Vaku.apiRest.repository.VaccinesAppliedRepository;
import com.Vaku.Vaku.utils.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service

public class VaccinesAppliedService {

    @Autowired
    private VaccinesAppliedRepository vaccinesAppliedRepository;

    @Autowired
    private InventoriesRepository inventoriesRepository;

    @Autowired
    private GenerateToken generateToken;

    public VaccinesAppliedEntity createVaccinesAppliedEntity(VaccinesAppliedEntity vaccinesAppliedResponse) {
        return vaccinesAppliedRepository.save(vaccinesAppliedResponse);

    }
}
