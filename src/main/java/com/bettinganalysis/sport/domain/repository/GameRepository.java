package com.bettinganalysis.sport.domain.repository;

import com.bettinganalysis.sport.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
