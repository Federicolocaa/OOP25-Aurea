package it.unibo.aurea.model;

import java.util.Objects;
import java.util.concurrent.locks.Condition;

import it.unibo.aurea.model.api.GameClock;
import it.unibo.aurea.model.api.GameConfig;

/**
 * imlmentation of the GameClock.
 */
public final class GameClockImpl implements GameClock {
    private final GameConfig gameConfiguration;
    //we start from 0 with the counter.
    private int currentTurn = 0;
    private int currentSemester = 0;
    private boolean timeFinished = false;

    public GameClockImpl(GameConfig gameConfiguration) {
        this.gameConfiguration = Objects.requireNonNull(gameConfiguration);
    }


    @Override
    public void nextTurn() {
        if (timeFinished) {
            throw new IllegalStateException("Game already finished");
        }

        if (hasNextTurnInSemester()) {
            currentTurn++;
        } else if (hasNextSemester()) {
            currentSemester++;
            currentTurn = 0;
        } else {
            timeFinished = true;
        }
    }

    private boolean hasNextSemester() {
        return this.currentTurn +1 < this.gameConfiguration.getCardsPerSemester();
    }

    private boolean hasNextTurnInSemester() {
        return this.currentSemester +1 < this.gameConfiguration.getSemestersPerGame();
    }

    //GETTERS
    public boolean isTimeFinished() {
        return timeFinished;
    }

    @Override
    public int getCurrentSemester() {
        return currentSemester;
    }

    @Override
    public int getCurrentTurn() {
        return currentTurn;
    }
}