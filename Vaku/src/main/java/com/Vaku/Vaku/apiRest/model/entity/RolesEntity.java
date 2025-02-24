package com.Vaku.Vaku.apiRest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "roles")
    private Set<EmployeesEntity> employees;
}
