package game;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class GameTest {
    private final Game game = new Game();
    private final Player firstPlayer = mock(Player.class);
    private final Player secondPlayer = mock(Player.class);

    @Test
    public void gameShouldInitiallyBeInStateNotStarted() {
        assertThat(game.getStatus(), is(equalTo(Game.Status.NOT_STARTED)));
    }

    @Test
    public void gameShouldBeInStatePlayingAfterStarting() {
        setupGameWithTwoPlayers();

        assertThat(game.getStatus(), is(equalTo(Game.Status.PLAYING)));
    }

    @Test
    public void gameShouldTellFirstPlayerToMoveAfterStarting() {
        setupGameWithTwoPlayers();

        verify(firstPlayer).yourTurn();
    }

    @Test
    public void gameShouldTellNextPlayerToMoveAfterPlayerMoves() {
        setupGameWithTwoPlayers();

        game.move();

        verify(secondPlayer).yourTurn();
    }

    @Test
    public void gameShouldTellFirstPlayerToMoveAfterSecondPlayerMoves() {
        setupGameWithTwoPlayers();

        game.move();
        reset(firstPlayer);
        game.move();

        verify(firstPlayer).yourTurn();
    }

    private void setupGameWithTwoPlayers() {
        game.register(firstPlayer);
        game.register(secondPlayer);
        game.start();
    }
}
