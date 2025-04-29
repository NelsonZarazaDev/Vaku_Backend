package com.Vaku.Vaku.apiRest.controller;

import com.Vaku.Vaku.apiRest.model.response.OverdueAppointmentResponse;
import com.Vaku.Vaku.apiRest.service.OverdueVaccinationsService;
import com.Vaku.Vaku.emailOverdueVaccinations.EmailOverdueVaccinationsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(path = "overdueVaccinations")
public class OverdueVaccinationsController {

    private final OverdueVaccinationsService overdueVaccinationsService;
    private final EmailOverdueVaccinationsHelper emailOverdueVaccinationsHelper;

    @Autowired
    public OverdueVaccinationsController(
            OverdueVaccinationsService overdueVaccinationsService,
            EmailOverdueVaccinationsHelper emailOverdueVaccinationsHelper
    ) {
        this.overdueVaccinationsService = overdueVaccinationsService;
        this.emailOverdueVaccinationsHelper = emailOverdueVaccinationsHelper;
    }

    @GetMapping()
    public ResponseEntity<Set<OverdueAppointmentResponse>> getOverdueAppointments() {
        return ResponseEntity.ok(overdueVaccinationsService.findByNextAppointmentDateBefore());
    }

    @PostMapping("/send-emails")
    public ResponseEntity<Void> sendOverdueEmails() {
        Set<OverdueAppointmentResponse> overdueAppointments = overdueVaccinationsService.findByNextAppointmentDateBefore();

        for (OverdueAppointmentResponse appointment : overdueAppointments) {
            String emailFather = appointment.getParentEmail();
            if (Objects.nonNull(emailFather)) {
                emailOverdueVaccinationsHelper.sendEmail(List.of(emailFather));
            }
        }

        return ResponseEntity.ok().build();
    }
}
