package com.bettinganalysis.bet.domain.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class BetSelection{
    protected BetSelectionResult result;
    protected double odd;
    protected String title;
}