package com.bettinganalysis.bet.domain.model;

import org.junit.jupiter.api.Test;

import static com.bettinganalysis.bet.domain.model.BetSelectionResult.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class BetTest {

    private static final double EPS = 0.001;

    /** Construit un pari à partir de paires (cote, résultat) : bet(10, 2.0, WON, 1.5, REFUNDED). */
    private static Bet bet(double stake, Object... oddAndResult) {
        Bet bet = new Bet();
        bet.setBook(Book.WINAMAX);
        bet.setStake(stake);
        for (int i = 0; i < oddAndResult.length; i += 2) {
            BetSelection s = new BetSelection();
            s.setGameId(1L);
            s.setTitle("sel");
            s.setOdd((double) oddAndResult[i]);
            s.setResult((BetSelectionResult) oddAndResult[i + 1]);
            bet.addSelection(s);
        }
        bet.setCombinedOdd(bet.computeOddFromSelections());
        bet.refreshResult();
        return bet;
    }

    @Test
    void simpleBetWon() {
        Bet bet = bet(10, 2.0, WON);
        assertThat(bet.getResult()).isEqualTo(WON);
        assertThat(bet.getPayout()).isCloseTo(20.0, within(EPS));
        assertThat(bet.getProfit()).isCloseTo(10.0, within(EPS));
    }

    @Test
    void combinedBetWithRefundedSelectionCountsOddOfOne() {
        Bet bet = bet(10, 2.0, WON, 1.5, REFUNDED);
        assertThat(bet.getResult()).isEqualTo(WON);
        assertThat(bet.getCombinedOdd()).isCloseTo(3.0, within(EPS));
        assertThat(bet.getPayout()).isCloseTo(20.0, within(EPS));
    }

    @Test
    void storedCombinedOddIsUsedForPayoutEvenWhenBoosted() {
        Bet bet = bet(10, 2.0, WON, 1.5, WON);
        bet.setCombinedOdd(3.5);
        assertThat(bet.getPayout()).isCloseTo(35.0, within(EPS));
        assertThat(bet.getProfit()).isCloseTo(25.0, within(EPS));
    }

    @Test
    void payoutAndProfitAreRoundedToTwoDecimals() {
        Bet bet = bet(3.33, 1.77, WON); // 3.33 * 1.77 = 5.8941
        assertThat(bet.getPayout()).isEqualTo(5.89);
        assertThat(bet.getProfit()).isEqualTo(2.56);
    }

    @Test
    void oneLostSelectionLosesTheBetEvenIfOthersAreInProgress() {
        Bet bet = bet(10, 2.0, LOST, 1.5, IN_PROGRESS);
        assertThat(bet.getResult()).isEqualTo(LOST);
        assertThat(bet.getPayout()).isEqualTo(0.0);
        assertThat(bet.getProfit()).isCloseTo(-10.0, within(EPS));
    }

    @Test
    void betStaysInProgressUntilAllSelectionsAreSettled() {
        Bet bet = bet(10, 2.0, WON, 1.5, IN_PROGRESS);
        assertThat(bet.getResult()).isEqualTo(IN_PROGRESS);
        assertThat(bet.isSettled()).isFalse();
        assertThat(bet.getProfit()).isEqualTo(0.0);
    }

    @Test
    void betInProgressIsTreatedAsRefundedUntilItIsLost() {
        Bet bet = bet(10, 2.0, IN_PROGRESS);
        assertThat(bet.getPayout()).isEqualTo(10.0);
        assertThat(bet.getProfit()).isEqualTo(0.0);
    }

    @Test
    void fullyRefundedBetReturnsTheStake() {
        Bet bet = bet(10, 2.0, REFUNDED);
        assertThat(bet.getResult()).isEqualTo(REFUNDED);
        assertThat(bet.getPayout()).isCloseTo(10.0, within(EPS));
        assertThat(bet.getProfit()).isEqualTo(0.0);
    }
}