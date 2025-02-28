package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.response.VaccinnesResponse;
import com.Vaku.Vaku.apiRest.repository.VaccinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service

public class VaccinesService {

    @Autowired
    private VaccinesRepository vaccinesRepository;

    public Set<VaccinnesResponse> getVaccines(Long id) {
        return vaccinesRepository.findByVaccinnes(id);
    }
}
