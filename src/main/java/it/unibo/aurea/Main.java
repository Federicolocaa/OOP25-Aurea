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
 * this class is external from the MVC and si used only to start everything and creating the object model.
 * it isn't a static method inside the controller for respect the SRP principle.
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
            // creation of elements for the MVC objects
            final GameConfig config = GameConfigImpl.createStandard();
            final Deck deck = new Deck(); // Se qui fallisce, il catch sotto lo intercetta

            //creation MVC objects
            final GameEngine engine = new GameEngineImpl(config, deck);

            final GameView view = new GameViewJavaFXImpl(); //here you can choose the implementaion of the view

            final GameController controller = new GameControllerImpl(view, engine);

            controller.startGame();
        } catch (final IllegalStateException e) { 
            System.err.println("errors in configuration of the enviroment"); //NOPMD
        }
    }
}
