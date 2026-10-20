package com.bettinganalysis.bet.domain.repository;

import com.bettinganalysis.bet.domain.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
}
