package com.Vaku.Vaku.apiRest.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonsEntity {
    private Long persId;
    private String persNames;
    private String persLastNames;
    private String persDocument;
    private String persSex;
    private String persAddress;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate persDateBirth;
    private String persToken;
}
