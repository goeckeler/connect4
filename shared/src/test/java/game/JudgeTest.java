package game;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class JudgeTest {
    @Test
    public void noWinnerIfNoSlotsAreOccupied() throws Exception {
        Judge judge = new Judge(1);
        assertThat (judge.determineGameWinner()).isEmpty();
    }
    @Test
    public void winnerIfSufficientNumberOfSlotsAreOccupied() throws Exception {
        Judge judge = new Judge(1);
        Player player = mock(Player.class);
        judge.onSlotOccupied(player, 0, 0);
        assertThat (judge.determineGameWinner()).contains(player);
    }

}