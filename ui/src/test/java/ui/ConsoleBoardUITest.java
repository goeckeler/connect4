package ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import game.Board;
import game.Player;

public class ConsoleBoardUITest
{
  private Board board = new Board(6, 7);
  private ConsoleBoardUI ui = new ConsoleBoardUI();

  // @formatter:off
  private String emptyBoard =
      ".......\n" +
      ".......\n" +
      ".......\n" +
      ".......\n" +
      ".......\n" +
      ".......\n"
      ;

  private String firstMoveBoard =
      ".......\n" +
      ".......\n" +
      ".......\n" +
      ".......\n" +
      ".......\n" +
      "...X...\n"
      ;

  private String secondMoveBoard =
      ".......\n" +
      ".......\n" +
      ".......\n" +
      ".......\n" +
      ".......\n" +
      "..OX...\n"
      ;

  private String winningBoard =
      ".......\n" +
      ".......\n" +
      "..O....\n" +
      "..OX...\n" +
      "..OX...\n" +
      "..OXX..\n"
      ;
  // @formatter:on

  @Test
  public void shouldDrawEmptyBoard() {
    assertThat(ui.toString(board)).asString().isEqualTo(emptyBoard);
  }

  @Test
  public void shouldDrawXForFirstPlayer() {
    final Player player1 = mock(Player.class);
    final Player player2 = mock(Player.class);

    when(player1.getName()).thenReturn("player1");
    when(player2.getName()).thenReturn("player2");

    board.insertChip(3, player1);
    assertThat(ui.toString(board)).asString().isEqualTo(firstMoveBoard);
  }

  @Test
  public void shouldDrawOForSecondPlayer() {
    final Player player1 = mock(Player.class);
    final Player player2 = mock(Player.class);

    when(player1.getName()).thenReturn("player1");
    when(player2.getName()).thenReturn("player2");

    board.insertChip(3, player1);
    ui.toString(board);

    board.insertChip(2, player2);

    assertThat(ui.toString(board)).asString().isEqualTo(secondMoveBoard);
  }

  @Test
  public void shouldDrawWinningBoard() {
    final Player player1 = mock(Player.class);
    final Player player2 = mock(Player.class);

    when(player1.getName()).thenReturn("player1");
    when(player2.getName()).thenReturn("player2");

    ChipColor.useColorFor("player1", 'X');
    ChipColor.useColorFor("player2", 'O');

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
