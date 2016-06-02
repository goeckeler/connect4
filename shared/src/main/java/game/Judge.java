package game;

import java.util.Optional;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class Judge
{
  private final int numberOfSlotsToWin;

  public Judge(final int numberOfSlotsToWin) {
    this.numberOfSlotsToWin = numberOfSlotsToWin;
  }

  public Optional<Player> determineGameWinner(final Board board) {
    // has won if has enough chips in the same colum, row, or vertical moving right or left

    int maxChips = 0;
    Player champion = null;

    // check rows
    for (int row = board.getNumberOfRows() - 1; row >= 0; --row) {
      int chips = 0;
      Player current = null;
      for (int col = 0; col < board.getNumberOfColumns(); ++col) {
        final Optional<Player> owner = board.slotOwner(row, col);
        if (owner.isPresent()) {
          if (current == null || !owner.get().getName().equals(current.getName())) {
            chips = 0;
            current = owner.get();
          }
          ++chips;
        } else {
          if (chips > maxChips) {
            maxChips = chips;
            champion = current;
          }
          chips = 0;
          current = null;
        }
      }
    }

    // check columns
    for (int col = 0; col < board.getNumberOfColumns(); ++col) {
      int chips = 0;
      Player current = null;
      for (int row = board.getNumberOfRows() - 1; row >= 0; --row) {
        final Optional<Player> owner = board.slotOwner(row, col);
        if (owner.isPresent()) {
          if (current == null || !owner.get().getName().equals(current.getName())) {
            chips = 0;
            current = owner.get();
          }
          ++chips;
        } else {
          if (chips > maxChips) {
            maxChips = chips;
            champion = current;
          }
          chips = 0;
          current = null;
        }
      }
    }

    if (maxChips >= numberOfSlotsToWin) { return Optional.of(champion); }
    return Optional.empty();
  }
}
