package game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GameTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private final Game game = new Game();
    private final Player firstPlayer = mock(Player.class);
    private final Player secondPlayer = mock(Player.class);

    @Before
    public void setupGameWithTwoPlayers() {
        game.register(firstPlayer);
        game.register(secondPlayer);
        game.start();
    }

    @Test
    public void gameShouldTellFirstPlayerToMoveAfterStarting() {
        verify(firstPlayer).yourTurn();
    }

    @Test
    public void gameShouldRejectSecondPlayerMoveBeforeFirstPlayerMove() {
        thrown.expect(IllegalArgumentException.class);
        game.insertChip(secondPlayer, 0);
    }

    @Test
    public void gameShouldTellNextPlayerToMoveAfterPlayerMoves() {
        int columnNumber = 0;
        game.insertChip(firstPlayer, columnNumber);

        verify(secondPlayer).yourTurn();
    }

    @Test
    public void gameShouldTellFirstPlayerToMoveAfterSecondPlayerMoves() {
        game.insertChip(firstPlayer, 0);
        reset(firstPlayer);
        game.insertChip(secondPlayer, 0);

        verify(firstPlayer).yourTurn();
    }

    @Test
    public void gameShouldTellCallingPlayerOccupiedSlot() {
        int columnNumber = 0;
        game.insertChip(firstPlayer, columnNumber);

        int rowNumber = Game.NUMBER_OF_ROWS - 1;
        verify(firstPlayer).onSlotOccupied(firstPlayer, rowNumber, columnNumber);
    }


    @Test
    public void gameShouldTellOtherPlayerTheOccupiedSlot() {
        int columnNumber = 0;
        game.insertChip(firstPlayer, columnNumber);

        int rowNumber = Game.NUMBER_OF_ROWS - 1;
        verify(secondPlayer).onSlotOccupied(firstPlayer, rowNumber, columnNumber);
    }

    @Test
    public void gameShouldTellGameObserverTheOccupiedSlot() {
        GameObserver gameObserver = mock(GameObserver.class);
        game.register(gameObserver);
        int columnNumber = 0;
        game.insertChip(firstPlayer, columnNumber);

        int rowNumber = Game.NUMBER_OF_ROWS - 1;
        verify(gameObserver).onSlotOccupied(firstPlayer, rowNumber, columnNumber);
    }
}
