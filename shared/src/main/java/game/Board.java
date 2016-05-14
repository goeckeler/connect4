package game;

import java.util.Optional;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class Board {
    private int numberOfRows;
    private int numberOfColumns;

    public Board(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public Optional<Player> slotOwner(int row, int column) {
        return Optional.empty();
    }

    public void insertChip(int columnNumber, Player player) {

    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }
}
