package it.unibo.aurea.view;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.view.api.GameView;
import javafx.*;

/**
 * Implementation of GameView with the usage of JavaFX.
 */
public final class GameViewJavaFXImpl implements GameView {

    /**
     * Builds a new GameViewImpl.
     */
    public GameViewJavaFXImpl() {
    }

    @Override
    public void displayCard(final Card card) {
        // TODO Auto-generated method stub
    }

    @Override
    public void showGameOver(final String message) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateParameters(
        final int finances,
        final int students,
        final int professors,
        final int reputation
    ) {
        // TODO Auto-generated method stub
    }
}
