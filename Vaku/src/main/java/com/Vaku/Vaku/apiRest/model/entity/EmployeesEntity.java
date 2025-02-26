package com.Vaku.Vaku.apiRest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @OneToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private Set<InventoriesEmployeesEntity> inventories_employees;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private Set<VaccinesAppliedEntity> vaccines_applied;
}
