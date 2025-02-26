package com.Vaku.Vaku.apiRest.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccinnesResponse {
    private Long vaccId;
    private String vaccName;
    private String vaccAgeDose;
    private String vaccDosage;
}
