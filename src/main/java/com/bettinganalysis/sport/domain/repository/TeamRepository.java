package com.bettinganalysis.sport.domain.repository;

import com.bettinganalysis.sport.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
