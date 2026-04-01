package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.GameConfig;
import it.unibo.aurea.model.api.GameEngine;

public class GameEngineImpl implements GameEngine{

    private Card currentCard;
    private Deck deck;
    private final GameConfig config;

    public GameEngineImpl(GameConfig config) {
        this.config = config;

    }

    @Override
    public GameConfig getGameConfig() {
        return config;
    }

    @Override
    public boolean isGameFinished() {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentCard'");
    }
    
}
