package com.Vaku.Vaku.apiRest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

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
    private Integer inveQuantity;
    private LocalDate inveDate;
    private String inveToken = (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 100) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 100));
    ;

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
