package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public enum Status {
        NOT_STARTED, PLAYING
    }

    private Status status = Status.NOT_STARTED;
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public Status getStatus() {
        return status;
    }

    public void start() {
        this.status = Status.PLAYING;
        this.players.get(currentPlayerIndex).yourTurn();
    }

    public void register(Player player) {
        this.players.add(player);
    }

    public void move() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        this.players.get(currentPlayerIndex).yourTurn();
    }
}
