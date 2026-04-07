package it.unibo.aurea.model;

import java.util.List;
import java.util.Objects;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.GameClock;
import it.unibo.aurea.model.api.GameConfig;
import it.unibo.aurea.model.api.GameEngine;
import it.unibo.aurea.model.api.ParameterType;

/**
 * this is the main implementation of the model.
 */
public final class GameEngineImpl implements GameEngine {

    private Card currentCard;
    private final Deck deck;
    private final GameConfig config;
    private final GameClock gameClock;
    private final List<ParameterImpl> parameters = List.of(
        new ParameterImpl(ParameterType.FINANCES),
        new ParameterImpl(ParameterType.STUDENTS),
        new ParameterImpl(ParameterType.PROFESSORS),
        new ParameterImpl(ParameterType.REPUTATION)
    );

    /**
     * @param config is an object of the @code GameConfiguration.java .
     * @param deck contains the deck of card that wiil be used in future.
     */
    public GameEngineImpl(final GameConfig config, final Deck deck) {
        this.config = config;
        this.gameClock = new GameClockImpl(config);
        this.deck = Objects.requireNonNull(deck, "Deck cannot be null");
    }

    @Override
    public GameConfig getGameConfig() {
        return config;
    }

    @Override
    public boolean isGameFinished() {
        return gameClock.isTimeFinished();
    }

    @Override
    public boolean isGameOver() {
        return this.isGameFinished() || !this.areAllParametersAlive();
    }

    @Override
    public void start() {
        // Extracts the first card (for now, the first of the list)
        if (!deck.getAllCards().isEmpty()) {
            this.currentCard = deck.getAllCards().get(0);
            //view.displayCard(this.currentCard);
        }
    }

    @Override
    public Card getCurrentCard() {
        return currentCard;
    }

    @Override
    public List<ParameterImpl> getParameters() {
        return this.parameters;
    }

    @Override
    public List<ParameterImpl> getCopyOfParameters() {
        return List.copyOf(parameters);
    }

    /**
     * @return true if all the parameters respect the condition <100 && >0.
     */
    private boolean areAllParametersAlive() {
        return parameters.stream()
                .allMatch(ParameterImpl::isAlive);
    }
}
