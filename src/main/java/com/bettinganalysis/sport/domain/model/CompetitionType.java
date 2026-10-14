package com.bettinganalysis.sport.domain.model;

import lombok.*;
import jakarta.persistence.*;

@Entity @Table(name = "competition_type")
@Getter @Setter @NoArgsConstructor
public class CompetitionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 500)
    private String logo;
}