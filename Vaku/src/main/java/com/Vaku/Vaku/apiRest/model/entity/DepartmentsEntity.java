package com.Vaku.Vaku.apiRest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "departments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String depaId;
    private String depaName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "departments")
    private Set<CitysEntity> citys;
}
