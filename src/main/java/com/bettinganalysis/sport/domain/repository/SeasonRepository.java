package com.bettinganalysis.sport.domain.repository;

import com.bettinganalysis.sport.domain.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
