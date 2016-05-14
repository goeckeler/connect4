package game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

public class GameTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private final Board board = new Board(Game.NUMBER_OF_ROWS, Game.NUMBER_OF_COLUMNS);
    private final Judge judge = mock(Judge.class);

    private final Game game = new Game(board, judge);
    private final Player firstPlayer = mock(Player.class);
    private final Player secondPlayer = mock(Player.class);
    private final GameObserver gameObserver = mock(GameObserver.class);

    @Before
    public void setupGameWithTwoPlayers() {
        when(judge.determineGameWinner()).thenReturn(Optional.empty());
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

    @Test
    public void gameShouldTellGameObserversWhenGameIsWon() {
        when(judge.determineGameWinner()).thenReturn(Optional.of(firstPlayer));

        game.insertChip(firstPlayer, 0);

        verify(gameObserver).onGameFinished(Optional.of(firstPlayer));
    }

    @Test
    public void nextTurnShouldNotBeCalledAfterGameWasWon() {
        when(judge.determineGameWinner()).thenReturn(Optional.of(firstPlayer));

        game.insertChip(firstPlayer, 0);

        verify(secondPlayer, never()).yourTurn();
    }

    @Test
    public void gameShouldNotTellGameObserverWhenGameIsNotWon() {
        game.insertChip(firstPlayer, 0);

        verify(gameObserver, never()).onGameFinished(Optional.of(firstPlayer));
    }

    @Test
    public void gameShouldFinishWhenThereAreNoEmptySlots() {
        Board board = mock(Board.class);
        when(board.isBoardFull()).thenReturn(true);
        Game game = new Game(board, judge);
        game.register(firstPlayer);
        game.register(gameObserver);

        game.insertChip(firstPlayer, 0);

        verify(gameObserver).onGameFinished(Optional.empty());
    }
}
