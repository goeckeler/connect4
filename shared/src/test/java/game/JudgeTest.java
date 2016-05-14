package game;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class JudgeTest {

    private Board board = new Board(Game.NUMBER_OF_ROWS, Game.NUMBER_OF_COLUMNS);

    @Test
    public void winnerIfLessThanSufficientNumberOfSlotsAreOccupied() throws Exception {
        Judge judge = new Judge(2);
        Player firstPlayer = mock(Player.class);
        int column = 0;
        board.insertChip(column, firstPlayer);
        assertThat (judge.determineGameWinner(board, column)).isEmpty();
    }


    @Test
    public void winnerSlotsAreOccupiedByDifferentPlayers() throws Exception {
        Judge judge = new Judge(2);
        Player firstPlayer = mock(Player.class);
        Player secondPlayer = mock(Player.class);
        int column = 0;
        board.insertChip(column, firstPlayer);
        board.insertChip(column, secondPlayer);
        assertThat (judge.determineGameWinner(board, column)).isEmpty();
    }
}