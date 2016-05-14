package game;

import java.util.Optional;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class Judge {
    private final int numberOfSlotsToWin;

    public Judge(int numberOfSlotsToWin) {

        this.numberOfSlotsToWin = numberOfSlotsToWin;
    }

    public Optional<Player> determineGameWinner(Board board, final int column) {
        final int row = board.lastUnoccupiedRowInColumn(column) + 1;
        Optional<Player> slotOwner = board.slotOwner(row, column);
        return slotOwner.filter(
                player -> hasWon(board, player, row, column)
        );
    }

    private boolean hasWon(Board board, Player player, int row, int column) {
        return false;
    }
}
