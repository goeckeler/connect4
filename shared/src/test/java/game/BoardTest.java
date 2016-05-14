package game;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class BoardTest {

    private static final int NUMBER_OF_ROWS = 6;

    private Board board = new Board(NUMBER_OF_ROWS, 7);
    private final Player player = mock(Player.class);

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void boardShouldHaveCorrectDimensions() {
        assertThat(board.getNumberOfRows()).isEqualTo(6);
        assertThat(board.getNumberOfColumns()).isEqualTo(7);
    }

    @Test
    public void boardIsEmptyAtTheBeginning() {
        int row = 0;
        int column = 0;

        Optional<Player> owner = board.slotOwner(row, column);

        assertThat(owner).isEmpty();
    }

    @Test
    public void slotShouldBeOccupiedAfterPlacing() {
        board.insertChip(0, player);

        Optional<Player> occupier = board.slotOwner(5, 0);
        assertThat(occupier).contains(player);
    }

    @Test
    public void slotInTheWrongRowShouldNotBeOccupiedAfterPlacing() {
        board.insertChip(0, player);

        assertThat(board.slotOwner(4, 0)).isEmpty();
    }

    @Test
    public void slotInTheSecondToLastRowShouldBeOccupiedAfterTwoPlacements() {
        board.insertChip(0, player);
        board.insertChip(0, player);

        assertThat(board.slotOwner(4, 0)).contains(player);
    }

    @Test
    public void slotInTheWrongColumnShouldNotBeOccupiedAfterPlacing() {
        board.insertChip(0, player);

        assertThat(board.slotOwner(5, 1)).isEmpty();
    }

    @Test
    public void insertChipShouldThrowExceptionIfColumnIsFull() {
        for (int i = 0; i < NUMBER_OF_ROWS; ++i) {
            board.insertChip(0, player);
        }

        thrown.expect(IllegalArgumentException.class);

        board.insertChip(0, player);
    }
}
