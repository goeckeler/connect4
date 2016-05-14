package game;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class BoardTest {

    private Board board = new Board(6, 7);

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
        Player player = mock(Player.class);

        board.insertChip(0, player);

        board.slotOwner(5, 0);
    }
}
