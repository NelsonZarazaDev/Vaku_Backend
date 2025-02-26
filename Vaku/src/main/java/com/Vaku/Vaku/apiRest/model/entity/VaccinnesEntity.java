package com.Vaku.Vaku.apiRest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "vaccinnes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccinnesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaccId;
    private String vaccName;
    private String vaccAgeDose;
    private String vaccDosage;

    @ManyToOne
    @JoinColumn(name = "inve_id")
    private InventoriesEntity inventories;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "vaccinnes", fetch = FetchType.LAZY)
    private Set<VaccinesAppliedEntity> vaccines_applied;
}
