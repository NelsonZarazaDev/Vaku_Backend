package com.Vaku.Vaku.apiRest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "persons")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long persId;
    private String persNames;
    private String persLastNames;
    private String persDocument;
    private String persSex;
    private String persAddress;
    private LocalDate persDateBirth;
    private String persToken;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "persons")
    private Set<EmployeesEntity> employees;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "persons")
    private Set<ParentsEntity> parents;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "persons")
    private Set<ChildrensEntity> childrens;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CitysEntity citys;

    @ManyToOne
    @JoinColumn(name = "doty_id")
    private DocumentsTypeEntity documents_type;
}
