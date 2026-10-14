package com.bettinganalysis.bet.domain.repository;

import com.bettinganalysis.bet.domain.model.Bet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BetRepository extends JpaRepository<Bet, Long> {
    Bet save(Bet bet);

    Optional<Bet> findById(Long id);

    void delete(Bet bet);

    Page<Bet> search(BetFilter filter, Pageable pageable);

    List<Bet> search(BetFilter filter, Sort sort);
}
