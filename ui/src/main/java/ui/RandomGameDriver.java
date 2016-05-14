package ui;

import java.util.Optional;

import ai.RandomPlayer;
import game.*;

public class RandomGameDriver
  implements GameObserver
{
  private Game game;
  private Board board;
  private ConsoleBoardUI ui;

  public RandomGameDriver(final Game game, final Board board) {
    this.game = game;
    this.board = board;
    this.ui = new ConsoleBoardUI();

    game.register(this);
  }

  public static void main(final String[] args) {
    final Judge judge = new Judge(4);
    final Board board = new Board(Game.NUMBER_OF_ROWS, Game.NUMBER_OF_COLUMNS);
    final Game game = new Game(board, judge);

    new RandomPlayer(game, "Alice");
    new RandomPlayer(game, "Bob");
    new RandomGameDriver(game, board);

    game.start();
  }

  @Override
  public void onSlotOccupied(final Player player, final int rowNumber, final int columnNumber) {
    System.err.println(String.format("%s drops in #%d\n", player.getName(), columnNumber));
    ui.draw(board);
  }

  @Override
  public void onGameFinished(final Optional<Player> winner) {
    if (winner.isPresent()) {
      System.out.println(String.format("The winner is %s.", winner.get().getName()));
    } else {
      System.out.println("It's a draw.");
    }
  }
}
