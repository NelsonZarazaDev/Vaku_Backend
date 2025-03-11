package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.response.CitysResponse;
import com.Vaku.Vaku.apiRest.repository.CitysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CitysService {

    @Autowired
    private CitysRepository citysRepository;

    public Set<CitysResponse> findByCityDepaId(String id) {
        return citysRepository.findByCityDepaId(id);
    }
}
