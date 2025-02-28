package com.Vaku.Vaku.apiRest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

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
    private String vaapToken= (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 100) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 100));;

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
