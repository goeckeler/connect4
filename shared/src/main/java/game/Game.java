package game;

import java.util.*;

public class Game
{
  public static final int NUMBER_OF_ROWS = 6;
  public static final int NUMBER_OF_COLUMNS = 7;

  private final Judge judge;
  private final Board board;
  private final List<Player> players = new ArrayList<>();
  private final List<GameObserver> observers = new ArrayList<>();
  private int currentPlayerIndex = 0;

  public Game(final Board board, final Judge judge) {
    this.board = board;
    this.judge = judge;
  }

  public void start() {
    currentPlayer().yourTurn();
  }

  public void register(final Player player) {
    this.players.add(player);
    this.observers.add(player);
  }

  public void register(final GameObserver observer) {
    this.observers.add(observer);
  }

  public void insertChip(final Player currentPlayer, final int columnNumber) {
    if (currentPlayer != currentPlayer()) throw new IllegalArgumentException();
    board.insertChip(columnNumber, currentPlayer);
    final int rowNumber = board.lastUnoccupiedRowInColumn(columnNumber) + 1;
    notifyObserversOfSlotOccupied(currentPlayer, columnNumber, rowNumber);
    final Optional<Player> winner = judge.determineGameWinner(board);
    if (winner.isPresent()) {
      notifyObserversOfGameFinished(winner);
    } else if (board.isBoardFull()) {
      notifyObserversOfGameFinished(Optional.empty());
    } else {
      nextTurn();
    }
  }

  private void notifyObserversOfSlotOccupied(final Player currentPlayer, final int columnNumber, final int rowNumber) {
    for (final GameObserver observer : observers) {
      observer.onSlotOccupied(currentPlayer, rowNumber, columnNumber);
    }
  }

  private void notifyObserversOfGameFinished(final Optional<Player> winner) {
    for (final GameObserver observer : observers) {
      observer.onGameFinished(winner);
    }
  }

  private void nextTurn() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    currentPlayer().yourTurn();
  }

  private Player currentPlayer() {
    return this.players.get(currentPlayerIndex);
  }
}
