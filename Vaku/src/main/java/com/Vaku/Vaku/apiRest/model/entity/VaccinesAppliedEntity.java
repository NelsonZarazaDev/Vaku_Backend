package com.Vaku.Vaku.apiRest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "vaccines_applied")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccinesAppliedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaapId;
    private LocalDate vaapNextAppointmentDate;
    private String vaapToken;

    @ManyToOne
    @JoinColumn(name = "vacc_id")
    private VaccinnesEntity vaccinnes;

    @ManyToOne
    @JoinColumn(name = "empl_id")
    private EmployeesEntity employees;

    @ManyToOne
    @JoinColumn(name = "chil_id")
    private ChildrensEntity childrens;
}
