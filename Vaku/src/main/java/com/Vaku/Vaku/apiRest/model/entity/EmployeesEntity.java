package com.Vaku.Vaku.apiRest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "employees")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emplId;
    private String emplEmail;
    private LocalDate emplDateAdmission;
    private boolean emplState;
    private String emplToken;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RolesEntity roles;

    @ManyToOne
    @JoinColumn(name = "pers_id")
    private PersonsEntity persons;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "employees")
    private Set<InventoriesEmployeesEntity> inventories_employees;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "employees")
    private Set<VaccinesAppliedEntity> vaccines_applied;
}
