package game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class JudgeTest
{

  private Board board = new Board(Game.NUMBER_OF_ROWS, Game.NUMBER_OF_COLUMNS);

  @Test
  public void winnerIfLessThanSufficientNumberOfSlotsAreOccupied()
    throws Exception
  {
    final Judge judge = new Judge(2);
    final Player firstPlayer = mock(Player.class);
    final int column = 0;
    board.insertChip(column, firstPlayer);
    assertThat(judge.determineGameWinner(board)).isEmpty();
  }

  @Test
  public void winnerSlotsAreOccupiedByDifferentPlayers()
    throws Exception
  {
    final Judge judge = new Judge(2);
    final Player firstPlayer = mock(Player.class);
    final Player secondPlayer = mock(Player.class);
    final int column = 0;

    when(firstPlayer.getName()).thenReturn("1");
    when(secondPlayer.getName()).thenReturn("2");

    board.insertChip(column, firstPlayer);
    board.insertChip(column, secondPlayer);
    assertThat(judge.determineGameWinner(board)).isEmpty();
  }
}
