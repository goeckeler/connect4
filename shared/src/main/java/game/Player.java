package game;

public interface Player extends GameObserver{
    String getName();
    void yourTurn();
}
