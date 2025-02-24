package com.Vaku.Vaku.apiRest.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentsEntity {
    private Long pareId;
    private String pareEmail;
    private String parePhone;
    private String pareToken;
}
