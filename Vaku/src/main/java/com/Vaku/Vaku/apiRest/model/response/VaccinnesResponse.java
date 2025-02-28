package com.Vaku.Vaku.apiRest.model.response;

import lombok.*;


public interface VaccinnesResponse {
    String getVaccName();
    String getVaccAgeDose();
    String getVaccDosage();
    String getInveLaboratory();
    String getInveLot();
    String getInveQuantity();
}
