package com.Vaku.Vaku.apiRest.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChildrensResponse {
    private Long childId;
    private String childToken;
}
