package game;

import com.google.common.base.Preconditions;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class Board {
    private final int numberOfRows;
    private final int numberOfColumns;
    private Player[][] occupiers;

    public Board(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        occupiers = new Player[numberOfRows][numberOfColumns];
    }

    public Optional<Player> slotOwner(int row, int column) {
        if(occupiers[row][column] == null) {
            return Optional.empty();
        } else {
            return Optional.of(occupiers[row][column]);
        }
    }

    public void insertChip(int columnNumber, Player player) {
        int index = lastUnoccupiedRowInColumn(columnNumber);
        checkArgument(index >= 0);
        occupiers[index][columnNumber] = player;
    }

    public int lastUnoccupiedRowInColumn(int columnNumber) {
        int index = numberOfRows - 1;
        while(index >= 0 && occupiers[index][columnNumber] != null) {
            index--;
        }
        return index;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }
}
