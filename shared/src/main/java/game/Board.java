package game;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Optional;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class Board
{
  private Player startingPlayer;
  private final int numberOfRows;
  private final int numberOfColumns;
  private Player[][] occupiers;

  public Board(final int numberOfRows, final int numberOfColumns) {
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    occupiers = new Player[numberOfRows][numberOfColumns];
  }

  public Optional<Player> slotOwner(final int row, final int column) {
    if (occupiers[row][column] == null) {
      return Optional.empty();
    } else {
      return Optional.of(occupiers[row][column]);
    }
  }

  public void insertChip(final int columnNumber, final Player player) {
    int index = numberOfRows - 1;
    while (index >= 0 && occupiers[index][columnNumber] != null) {
      index--;
    }
    checkArgument(index >= 0);
    occupiers[index][columnNumber] = player;
  }

  public int getNumberOfRows() {
    return numberOfRows;
  }

  public int getNumberOfColumns() {
    return numberOfColumns;
  }

  public void setStartingPlayer(final Player player) {
    this.startingPlayer = player;
  }

  public Player getStartingPlayer() {
    return startingPlayer;
  }
}
