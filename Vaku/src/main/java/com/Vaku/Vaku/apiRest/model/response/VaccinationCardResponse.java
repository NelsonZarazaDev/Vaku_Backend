package com.Vaku.Vaku.apiRest.model.response;

import java.time.LocalDate;


public interface VaccinationCardResponse {
    Long getchChil_id();

    String getchChild_token();

    Long getchPers_id();

    String getchPers_names();

    String getchPers_last_names();

    String getchPers_document();

    String getchPers_sex();

    String getchPers_address();

    LocalDate getchPers_date_birth();



    String getchCity_name();

    String getchDepa_name();



    Long getppaPare_id();

    String getppaPare_token();

    String getppaPers_names();

    String getppaPers_last_names();

    String getppaPers_document();

    String getppaPers_role();

    String getppaPers_email();

    String getppaPers_phone();



    Long getvaap_id();

    LocalDate getvaap_next_appointment_date();

    LocalDate getvaap_date_application();

    String getvaap_token();

    Long getvaap_empl_id();



    Long getvVacc_id();

    String getvVacc_name();



    String getpePers_names();

    String getpePers_last_names();

}