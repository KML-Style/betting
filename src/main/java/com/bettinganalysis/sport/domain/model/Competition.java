package com.bettinganalysis.sport.domain.model;

import lombok.*;
import com.bettinganalysis.sport.domain.model.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Competition {
    protected CompetitionType type;
    protected Season season;
    protected List<Team> teams;
}