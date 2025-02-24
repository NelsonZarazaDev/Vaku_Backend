package com.Vaku.Vaku.apiRest.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoriesEntity {
    private Long inveId;
    private String inveLaboratory;
    private String inveLot;
    private String inveQuantity;
    private String inveToken;
}
