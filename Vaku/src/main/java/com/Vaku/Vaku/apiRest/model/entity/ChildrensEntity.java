package com.Vaku.Vaku.apiRest.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "childrens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChildrensEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;
    private String childToken;

    @ManyToOne
    @JoinColumn(name = "pers_id")
    private PersonsEntity persons;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "childrens")
    private Set<VaccinesAppliedEntity> vaccines_applied;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "childrens")
    private Set<ChildrensParentsEntity> childrens_parents;
}
