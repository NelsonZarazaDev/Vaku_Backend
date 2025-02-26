package com.Vaku.Vaku.apiRest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long chilId;
    private String chilToken;

    @ManyToOne
    @JoinColumn(name = "pers_id")
    private PersonsEntity persons;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "childrens", fetch = FetchType.LAZY)
    private Set<VaccinesAppliedEntity> vaccines_applied;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "childrens", fetch = FetchType.LAZY)
    private Set<ChildrensParentsEntity> childrens_parents;

}
