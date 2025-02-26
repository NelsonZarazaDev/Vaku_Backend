package com.Vaku.Vaku.apiRest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "parents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pareId;
    private String pareEmail;
    private String parePhone;
    private String pareToken;

    @ManyToOne
    @JoinColumn(name = "pers_id")
    private PersonsEntity persons;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "parents", fetch = FetchType.LAZY)
    private Set<ChildrensParentsEntity> childrens_parents;
}
