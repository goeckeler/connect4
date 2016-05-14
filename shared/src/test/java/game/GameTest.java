package game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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
    private final GameObserver gameObserver = mock(GameObserver.class);
    private final Judge judge = mock(Judge.class);

    @Before
    public void setupGameWithTwoPlayers() {
        game.register(firstPlayer);
        game.register(secondPlayer);
        game.register(gameObserver);
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
        int columnNumber = 0;
        game.insertChip(firstPlayer, columnNumber);

        int rowNumber = Game.NUMBER_OF_ROWS - 1;
        verify(gameObserver).onSlotOccupied(firstPlayer, rowNumber, columnNumber);
    }

    @Test
    public void gameShouldTellGameObserversAboutOccupiedSlotsAboveLastRow() {
        game.insertChip(firstPlayer, 0);
        game.insertChip(secondPlayer, 0);

        verify(gameObserver).onSlotOccupied(secondPlayer, Game.NUMBER_OF_ROWS - 2, 0);
    }
}
