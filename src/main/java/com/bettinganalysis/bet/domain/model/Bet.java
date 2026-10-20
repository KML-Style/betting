package com.bettinganalysis.bet.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.*;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "bet")
@Getter @Setter @NoArgsConstructor
public class Bet{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BetSelectionResult result = BetSelectionResult.IN_PROGRESS;

    @Column(nullable = false)
    private double stake;

    @Column(name = "combined_odd", nullable = false)
    private double combinedOdd;

    @OneToMany(mappedBy = "bet", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    @BatchSize(size = 50)
    @Setter(AccessLevel.NONE)
    private List<BetSelection> selections = new ArrayList<>();

    public void addSelection(BetSelection selection) {
        selections.add(selection);
    }

    public boolean isSettled() {
        return result != BetSelectionResult.IN_PROGRESS;
    }

    public double computeOddFromSelections() {
        double odd = 1.0;
        for (BetSelection s : selections) {
            odd *= s.getOdd();
        }
        return round(odd, 3);
    }

    public double getPayout() {
        return switch (result) {
            case LOST -> 0.0;
            case REFUNDED, IN_PROGRESS -> stake;
            case WON -> {
                double refundedOdd = 1.0;
                for (BetSelection s : selections) {
                    if (s.getResult() == BetSelectionResult.REFUNDED) {
                        refundedOdd *= s.getOdd();
                    }
                }
                yield round(stake * combinedOdd / refundedOdd, 2);
            }
        };
    }

    public double getProfit() {
        return isSettled() ? round(getPayout() - stake, 2) : 0.0;
    }

    public void refreshResult() {
        if (selections.isEmpty()) {
            result = BetSelectionResult.IN_PROGRESS;
        } else if (selections.stream().anyMatch(s -> s.getResult() == BetSelectionResult.LOST)) {
            result = BetSelectionResult.LOST;
        } else if (selections.stream().anyMatch(s -> s.getResult() == BetSelectionResult.IN_PROGRESS)) {
            result = BetSelectionResult.IN_PROGRESS;
        } else if (selections.stream().allMatch(s -> s.getResult() == BetSelectionResult.REFUNDED)) {
            result = BetSelectionResult.REFUNDED;
        } else {
            result = BetSelectionResult.WON;
        }
    }

    public LocalDate getDate() {
        return selections.stream().map(BetSelection::getDate).max(Comparator.naturalOrder()).orElse(null);
    }

    private static double round(double value, int decimals) {
        double factor = Math.pow(10, decimals);
        return Math.round(value * factor) / factor;
    }
}