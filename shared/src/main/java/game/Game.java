package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int NUMBER_OF_ROWS = 6;
    public static final int NUMBER_OF_COLUMNS = 7;
    private final Board board = new Board(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
    private final List<Player> players = new ArrayList<>();
    private final List<GameObserver> observers = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public void start() {
        currentPlayer().yourTurn();
    }

    public void register(Player player) {
        this.players.add(player);
        this.observers.add(player);
    }

    public void register(GameObserver observer) {
        this.observers.add(observer);
    }

    public void insertChip(Player currentPlayer, int columnNumber) {
        if(currentPlayer != currentPlayer())
            throw new IllegalArgumentException();
        board.insertChip(columnNumber, currentPlayer);
        notifyObservers(currentPlayer, columnNumber, board.lastUnoccupiedRowInColumn(columnNumber) + 1);
        nextTurn();
    }

    private void notifyObservers(Player currentPlayer, int columnNumber, int rowNumber) {
        for(GameObserver observer : observers) {
            observer.onSlotOccupied(currentPlayer, rowNumber, columnNumber);
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
