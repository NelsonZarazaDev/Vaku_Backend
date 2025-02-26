package com.Vaku.Vaku.apiRest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "inventories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inveId;
    private String inveLaboratory;
    private String inveLot;
    private String inveQuantity;
    private String inveToken;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "inventories", fetch = FetchType.LAZY)
    private Set<InventoriesEmployeesEntity> inventories_employees;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "inventories", fetch = FetchType.LAZY)
    private Set<VaccinnesEntity> vaccinnes;
}
