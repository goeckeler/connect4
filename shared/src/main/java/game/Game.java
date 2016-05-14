package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int NUMBER_OF_ROWS = 6;
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public void start() {
        currentPlayer().yourTurn();
    }

    public void register(Player player) {
        this.players.add(player);
    }

    public void register(GameObserver observer) {
    }

    public void insertChip(Player currentPlayer, int columnNumber) {
        if(currentPlayer != currentPlayer())
            throw new IllegalArgumentException();
        notifyPlayers(currentPlayer, columnNumber, NUMBER_OF_ROWS - 1);
        nextTurn();
    }

    private void notifyPlayers(Player currentPlayer, int columnNumber, int rowNumber) {
        for(Player player : players) {
            player.onSlotOccupied(currentPlayer, rowNumber, columnNumber);
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
