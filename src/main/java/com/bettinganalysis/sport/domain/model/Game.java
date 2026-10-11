package com.bettinganalysis.sport.domain.model;

import lombok.*;
import com.bettinganalysis.sport.domain.model.*;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class Game {
    protected Date date;
    protected Competition competition;
    protected Integer home_score;
    protected Integer away_score;
    protected Team home_team;
    protected Team away_team;
}