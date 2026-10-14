package com.bettinganalysis.sport.domain.model;

import lombok.*;
import jakarta.persistence.*;

@Entity @Table(name = "season")
@Getter @Setter @NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "start_year", nullable = false)
    private Integer start_year;

    @Column(name = "end_year", nullable = false)
    private Integer end_year;
}