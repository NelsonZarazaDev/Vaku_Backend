package com.Vaku.Vaku.apiRest.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "inventories_vaccinnes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoriesEmployeesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inem_id;

    @ManyToOne
    @JoinColumn(name = "empl_id")
    private EmployeesEntity employees;

    @ManyToOne
    @JoinColumn(name = "inve_id")
    private InventoriesEntity inventories;
}
