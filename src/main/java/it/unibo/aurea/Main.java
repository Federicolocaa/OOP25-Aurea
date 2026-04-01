package it.unibo.aurea;

import it.unibo.aurea.controller.GameControllerImpl;
import it.unibo.aurea.controller.api.GameController;
import it.unibo.aurea.model.GameConfigImpl;
import it.unibo.aurea.model.GameEngineImpl;
import it.unibo.aurea.model.api.GameConfig;

import it.unibo.aurea.view.GameViewImpl;

import it.unibo.aurea.view.api.GameView;

public final class Main {

    private Main() {
    }

    /**
     * Avvia l'applicazione creando e collegando model, view e controller.
     *
     * @param args argomenti da linea di comando
     */
    public static void main(final String[] args) {
        final GameConfig config = GameConfigImpl.createStandard();
        final GameView view = new GameViewImpl(); //this sn't created already.
        final GameController controller = new GameControllerImpl(view, new GameEngineImpl(config));
        controller.startGame();
    }
}