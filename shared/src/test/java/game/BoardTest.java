package game;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Dimitry on 14.05.2016.
 */
public class BoardTest {
    @Test
    public void boardIsEmptyAtTheBeginning() {
        Board board = new Board();
        int row = 0;
        int column = 0;

        Optional<Player> owner = board.slotOwner(row, column);

        assertThat(owner).isEmpty();
    }
}
