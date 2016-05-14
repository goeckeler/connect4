package game;

import java.util.Optional;

/**
 * Created by Dimitry on 14.05.2016.
 */
public interface GameObserver {

    void onSlotOccupied(Player player, int rowNumber, int columnNumber);

    void onGameFinished(Optional<Player> winner);
}
