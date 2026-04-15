package it.unibo.aurea;

import it.unibo.aurea.controller.GameControllerImpl;
import it.unibo.aurea.controller.api.GameController;
import it.unibo.aurea.model.Deck;
import it.unibo.aurea.model.GameConfigImpl;
import it.unibo.aurea.model.GameEngineImpl;
import it.unibo.aurea.model.api.GameConfig;
import it.unibo.aurea.model.api.GameEngine;
import it.unibo.aurea.view.GameViewJavaFXImpl;
import it.unibo.aurea.view.api.GameView;

/**
 * This class is external from the MVC and is used only to start everything and creating the object model.
 * It isn't a static method inside the controller for respect the SRP principle.
 */
public final class Main {

    private Main() {
    }

    /**
     * Starts the application by creating and connecting the model, view, and controller.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        try {
            // Creation of elements for the MVC objects
            final GameConfig config = GameConfigImpl.createStandard();
            final Deck deck = new Deck();

            // Creation MVC objects
            final GameEngine engine = new GameEngineImpl(config, deck);
            final GameView view = new GameViewJavaFXImpl();
            final GameController controller = new GameControllerImpl(view, engine);

            view.setController(controller);

            controller.startGame();
            
        } catch (final IllegalStateException e) { 
            System.err.println("Errors in configuration of the environment"); //NOPMD
        }
    }
}
