package com.bettinganalysis.bet.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bet_selection")
@Getter @Setter @NoArgsConstructor
public class BetSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bet_id", nullable = false)
    private Bet bet;

    @Column(name = "game_id", nullable = false)
    private Long gameId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, precision = 8, scale = 3)
    private double odd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BetSelectionResult result = BetSelectionResult.IN_PROGRESS;

    @Column(name = "game_date", nullable = false)
    private LocalDate date;
}
