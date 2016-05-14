package ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import game.Board;
import game.Player;

public class ConsoleUITest
{
  private Board board = new Board(6, 7);
  private ConsoleUI ui = new ConsoleUI();

  // @formatter:off
  private String emptyBoard = ".......\n"
                            + ".......\n"
                            + ".......\n"
                            + ".......\n"
                            + ".......\n"
                            + ".......\n"
                            ;

  private String winningBoard = ".......\n"
                              + ".......\n"
                              + "..O....\n"
                              + "..OX...\n"
                              + "..OX...\n"
                              + "..OXX..\n"
                              ;

  // @formatter:on

  @Test
  public void shouldDrawEmptyBoard() {
    assertThat(ui.toString(board)).asString().isEqualTo(emptyBoard);
  }

  @Test
  @Ignore
  public void shouldDrawWinningBoard() {

    final Player player1 = new VoidPlayer();
    final Player player2 = new VoidPlayer();

    board.insertChip(3, player1);
    board.insertChip(3, player1);
    board.insertChip(3, player1);
    board.insertChip(4, player1);
    board.insertChip(2, player2);
    board.insertChip(2, player2);
    board.insertChip(2, player2);
    board.insertChip(2, player2);

    assertThat(ui.toString(board)).asString().isEqualTo(winningBoard);
  }
}

class VoidPlayer
  implements Player
{

  @Override
  public void yourTurn() {
  }

  @Override
  public void onSlotOccupied(final Player player, final int rowNumber, final int columnNumber) {
  }
}
