package ai;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import game.*;

public class RandomPlayer
  implements Player
{
  private Game game;
  private String name;
  private Board board;

  public RandomPlayer(final Game game, final String name) {
    assert name != null : "You need to name your players";
    assert game != null : "No game, no fun";
    this.game = game;
    this.name = name;

    this.board = new Board(Game.NUMBER_OF_ROWS, Game.NUMBER_OF_COLUMNS);
    game.register(this);
  }

  @Override
  public void onSlotOccupied(final Player player, final int rowNumber, final int columnNumber) {
    board.insertChip(columnNumber, player);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void yourTurn() {
    boolean placed = false;
    while (!placed && !board.isBoardFull()) {
      final int column = ThreadLocalRandom.current().nextInt(0, Game.NUMBER_OF_COLUMNS);
      placed = board.canInsert(column);
      if (placed) {
        game.insertChip(this, column);
      }
    }
  }

  @Override
  public void onGameFinished(final Optional<Player> winner) {
  }
}
