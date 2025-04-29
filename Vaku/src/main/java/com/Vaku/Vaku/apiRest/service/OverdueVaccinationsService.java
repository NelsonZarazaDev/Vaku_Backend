package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.response.OverdueAppointmentResponse;
import com.Vaku.Vaku.apiRest.repository.ChildrensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OverdueVaccinationsService {
    @Autowired
    private ChildrensRepository childrensRepository;

    public Set<OverdueAppointmentResponse> findByNextAppointmentDateBefore() {
        return childrensRepository.findByNextAppointmentDateBefore();
    }
}
