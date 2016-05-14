package game;

import java.util.Optional;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class Judge implements GameObserver{
    private final int numberOfSlotsToWin;
    private Player winner;

    public Judge(int numberOfSlotsToWin) {

        this.numberOfSlotsToWin = numberOfSlotsToWin;
    }

    public Optional<Player> determineGameWinner() {
        return Optional.ofNullable(winner);
    }

    @Override
    public void onSlotOccupied(Player player, int rowNumber, int columnNumber) {
        winner = player;
    }

    @Override
    public void onGameFinished(Optional<Player> winner) {

    }
}
