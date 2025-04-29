package com.Vaku.Vaku.pdfVaccinationCard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AplicacionVacunaDTO {

    @JsonProperty("vVacc_id")
    private int vVaccId;

    @JsonProperty("vaap_date_application")
    private String vaapDateApplication;

    @JsonProperty("vaap_next_appointment_date")
    private String vaapNextAppointmentDate;

    // Getters y Setters
    public int getvVaccId() {
        return vVaccId;
    }

    public void setvVaccId(int vVaccId) {
        this.vVaccId = vVaccId;
    }

    public String getVaapDateApplication() {
        return vaapDateApplication;
    }

    public void setVaapDateApplication(String vaapDateApplication) {
        this.vaapDateApplication = vaapDateApplication;
    }

    public String getVaapNextAppointmentDate() {
        return vaapNextAppointmentDate;
    }

    public void setVaapNextAppointmentDate(String vaapNextAppointmentDate) {
        this.vaapNextAppointmentDate = vaapNextAppointmentDate;
    }
}