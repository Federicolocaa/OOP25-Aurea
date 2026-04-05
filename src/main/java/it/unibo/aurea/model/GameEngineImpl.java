package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.GameClock;
import it.unibo.aurea.model.api.GameConfig;
import it.unibo.aurea.model.api.GameEngine;

/**
 * this is the main implementation of the model
 */
public class GameEngineImpl implements GameEngine{

    private Card currentCard;
    private Deck deck;
    private final GameConfig config;
    private final GameClock gameClock;

    public GameEngineImpl(final GameConfig config) {
        this.config = config;
        this.gameClock = new GameClockImpl(config);

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
        // // Extracts the first card (for now, the first of the list)
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
