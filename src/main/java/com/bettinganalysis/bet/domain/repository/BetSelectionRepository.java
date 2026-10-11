package com.bettinganalysis.bet.domain.repository;

import com.bettinganalysis.bet.domain.model.BetSelection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetSelectionRepository extends JpaRepository<BetSelection, Long> {
}
