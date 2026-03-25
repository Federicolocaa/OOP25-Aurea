package it.unibo.aurea.model.api;

public interface GameEngine {
    void start(GameConfig config);
    void nextTurn();
    boolean isGameOver();
    boolean isGameFinished();
}
