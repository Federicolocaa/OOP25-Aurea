package it.unibo.aurea.view;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.view.api.GameView;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX implementation of the GameView.
 */
public class GameViewJavaFXImpl implements GameView {

    private Stage stage;
    private it.unibo.aurea.controller.api.GameController controller; // Il riferimento al Controller
    
    // UI Elements for Parameters
    private Label financesLabel;
    private Label studentsLabel;
    private Label professorsLabel;
    private Label reputationLabel;

    // UI Elements for the Card
    private Label cardTextLabel;

    /**
     * Constructor for the JavaFX View.
     */
    public GameViewJavaFXImpl() {
        // 1. Accendiamo il motore di JavaFX
        try {
            Platform.startup(() -> { });
        } catch (final IllegalStateException e) {
            // Ignoriamo se è già acceso
        }

        // 2. Costruiamo la grafica
        Platform.runLater(() -> {
            this.stage = new Stage();
            this.stage.setTitle("Aurea - University Reign");

            this.financesLabel = new Label("Finances: 50");
            this.studentsLabel = new Label("Students: 50");
            this.professorsLabel = new Label("Professors: 50");
            this.reputationLabel = new Label("Reputation: 50");
            this.cardTextLabel = new Label("Card text will appear here");

            final BorderPane root = new BorderPane();

            // --- TOP: Parameters ---
            final HBox parametersBox = new HBox(20);
            parametersBox.setAlignment(Pos.CENTER);
            parametersBox.getChildren().addAll(financesLabel, studentsLabel, professorsLabel, reputationLabel);
            root.setTop(parametersBox);

            // --- CENTER: The Card ---
            final StackPane cardArea = new StackPane();
            final VBox cardVisual = new VBox(20);
            cardVisual.setAlignment(Pos.CENTER);
            cardVisual.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: lightgray; -fx-padding: 50px;");
            cardVisual.setMaxSize(300, 400); // Temporary card size
            cardVisual.getChildren().add(cardTextLabel);
            
            cardArea.getChildren().add(cardVisual);
            root.setCenter(cardArea);

            // --- BOTTOM: Buttons ---
            final HBox buttonBox = new HBox(50);
            buttonBox.setAlignment(Pos.CENTER);
            
            // Action for SI
            final Button yesButton = new Button("SÌ (Approve)");
            yesButton.setOnAction(e -> {
                if (this.controller != null) {
                    this.controller.makeDecision(true);
                }
            });

            // Action for NO
            final Button noButton = new Button("NO (Refuse)");
            noButton.setOnAction(e -> {
                if (this.controller != null) {
                    this.controller.makeDecision(false);
                }
            });

            buttonBox.getChildren().addAll(noButton, yesButton);
            root.setBottom(buttonBox);

            // Setup the Scene
            final Scene scene = new Scene(root, 800, 600);
            this.stage.setScene(scene);
            this.stage.show();
        });
    }

    @Override
    public void setController(final it.unibo.aurea.controller.api.GameController controller) {
        this.controller = controller;
    }

    @Override
    public void displayCard(final Card card) {
        Platform.runLater(() -> {
            if (card != null && card.getApproval() != null) {
                cardTextLabel.setText("Decision: \n" + card.getApproval().getAnswer()); 
            }
        });
    }

    @Override
    public void updateParameters(final int finances, final int students, final int professors, final int reputation) {
        Platform.runLater(() -> {
            if (financesLabel != null) { 
                financesLabel.setText("Finances: " + finances);
                studentsLabel.setText("Students: " + students);
                professorsLabel.setText("Professors: " + professors);
                reputationLabel.setText("Reputation: " + reputation);
            }
        });
    }

    @Override
    public void showVictory() {
        Platform.runLater(() -> {
            if (cardTextLabel != null) {
                cardTextLabel.setText("VICTORY!");
            }
        });
    }

    @Override
    public void showDefeat() {
        Platform.runLater(() -> {
            if (cardTextLabel != null) {
                cardTextLabel.setText("DEFEAT!");
            }
        });
    }

    @Override
    public void showGameOver(final String reason) {
        Platform.runLater(() -> {
            if (cardTextLabel != null) {
                cardTextLabel.setText("GAME OVER: " + reason);
            }
        });
    }
}
