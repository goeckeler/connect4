package ui;

import game.Board;

public class ConsoleUI
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
        string.append(EMPTY_SLOT);
      }
      string.append('\n');
    }
    return string.toString();
  }
}
