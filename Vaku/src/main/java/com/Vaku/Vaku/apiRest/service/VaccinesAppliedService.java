package com.Vaku.Vaku.apiRest.service;

import com.Vaku.Vaku.apiRest.model.entity.VaccinesAppliedEntity;
import com.Vaku.Vaku.apiRest.repository.InventoriesRepository;
import com.Vaku.Vaku.apiRest.repository.VaccinesAppliedRepository;
import com.Vaku.Vaku.email.EmailHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.SimpleTimeZone;

@Service
@AllArgsConstructor
public class VaccinesAppliedService {

    @Autowired
    private VaccinesAppliedRepository vaccinesAppliedRepository;

    private final EmailHelper emailHelper;

    public VaccinesAppliedEntity createVaccinesAppliedEntity(VaccinesAppliedEntity vaccinesAppliedResponse, String emailFather) {
        Date date = new Date();
        LocalTime time = LocalTime.now();

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");


        vaccinesAppliedResponse.setVaapApplied(true);
        vaccinesAppliedResponse.setVaapDateApplication(LocalDate.parse(dateFormat.format(date)));
        vaccinesAppliedResponse.setVaapTimeApplication(LocalTime.parse(time.format(timeFormatter)));
        if(Objects.nonNull(emailFather)) this.emailHelper.sendEmail(emailFather);
        return vaccinesAppliedRepository.save(vaccinesAppliedResponse);
    }
}
