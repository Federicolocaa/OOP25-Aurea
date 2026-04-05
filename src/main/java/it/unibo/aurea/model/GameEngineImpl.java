package it.unibo.aurea.model;

import java.io.IOException;
import java.util.Objects;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.GameClock;
import it.unibo.aurea.model.api.GameConfig;
import it.unibo.aurea.model.api.GameEngine;

/**
 * this is the main implementation of the model.
 */
public final class GameEngineImpl implements GameEngine {

    private Card currentCard;
    private Deck deck;
    private final GameConfig config;
    private final GameClock gameClock;

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
        // TODO(insert the check of the condition fo the parameters) Auto-generated method stub
        return gameClock.isTimeFinished();
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        return false;
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
}
