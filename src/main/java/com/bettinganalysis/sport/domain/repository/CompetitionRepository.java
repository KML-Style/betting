package com.bettinganalysis.sport.domain.repository;

import com.bettinganalysis.sport.domain.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
