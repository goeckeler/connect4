package ui;

import java.util.Optional;

import game.Board;
import game.Player;

public class ConsoleBoardUI
{
  private static final char EMPTY_SLOT = '.';

  public void draw(final Board board) {
    System.out.println(toString(board));
  }

  public String toString(final Board board) {
    final StringBuilder string = new StringBuilder();
    final int numberOfColumns = board.getNumberOfColumns();
    final int numberOfRows = board.getNumberOfRows();
    for (int row = 0; row < numberOfRows; row++) {
      for (int column = 0; column < numberOfColumns; column++) {
        final Optional<Player> owner = board.slotOwner(row, column);
        if (!owner.isPresent()) {
          string.append(EMPTY_SLOT);
        } else {
          string.append(ChipColor.colorFor(owner.get()));
        }

      }
      string.append('\n');
    }
    return string.toString();
  }
}
