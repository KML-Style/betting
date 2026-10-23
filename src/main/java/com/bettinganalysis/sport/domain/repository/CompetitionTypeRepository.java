package com.bettinganalysis.sport.domain.repository;

import com.bettinganalysis.sport.domain.model.CompetitionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionTypeRepository extends JpaRepository<CompetitionType, Long> {
}
