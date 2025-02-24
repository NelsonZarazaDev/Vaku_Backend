package com.Vaku.Vaku.apiRest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "citys")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    private String cityCode;
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "depa_id")
    private DepartmentsEntity departments;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "citys")
    private Set<PersonsEntity> persons;

}
