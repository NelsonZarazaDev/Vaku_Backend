package com.Vaku.Vaku.apiRest.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesEntity {
    private Long emplId;
    private String emplEmail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate emplDateAdmission;
    private boolean emplState;
    private String emplToken;
}
