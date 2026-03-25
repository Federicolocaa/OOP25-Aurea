package it.unibo.aurea.model;

import it.unibo.aurea.model.api.GameConfig;

/**
 * Here I've used a static factory pattern to handle the creation of many type of games.
 * For example in futurre could be intersting to create an apocalypse mode.
 */
public final class GameConfigImpl implements GameConfig{
    private final int semesters;
    private final int cardsPerSemester;

    private GameConfigImpl(final int cardsPerSemester, final int semestersPerGame) {
        this.cardsPerSemester = cardsPerSemester;
        this.semesters = semestersPerGame;
    }
    @Override
    public int getCardsPerSemester() {
        return cardsPerSemester;
    }

    @Override
    public int getSemestersPerGame() {
        return semesters;
    }

    public static GameConfig createStandard() {
        return new GameConfigImpl(6, 6);
    }
    
}
