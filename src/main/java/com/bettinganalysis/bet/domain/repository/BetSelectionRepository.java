package com.bettinganalysis.bet.domain.repository;

import com.bettinganalysis.bet.domain.model.BetSelection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BetSelectionRepository extends JpaRepository<BetSelection, Long> {
    Optional<BetSelection> findById(Long id);
}
