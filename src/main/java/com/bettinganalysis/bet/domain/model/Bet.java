package com.bettinganalysis.bet.domain.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class Bet{
    protected BetSelectionResult result;
    protected double stake;
}