package com.Vaku.Vaku.apiRest.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccinesAppliedResponse {
    private Long vaapId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate vaapNextAppointmentDate;
    private String vaapToken;
}
