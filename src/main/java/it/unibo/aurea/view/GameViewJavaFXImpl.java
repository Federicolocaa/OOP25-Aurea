package it.unibo.aurea.view;

import java.util.Set;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.ParameterType;
import it.unibo.aurea.view.api.GameView;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * JavaFX implementation of the GameView, designed to mimic the Reigns UI/UX.
 */
public class GameViewJavaFXImpl implements GameView {

    private Stage stage;
    private it.unibo.aurea.controller.api.GameController controller;
    private Card currentCard;

    // Parameter containers and labels
    private VBox financesBox, studentsBox, professorsBox, reputationBox;
    private Label financesLabel, studentsLabel, professorsLabel, reputationLabel;
    
    // Oracle indicator dots
    private Circle finDot, stuDot, proDot, repDot;

    // Card UI components
    private VBox cardVisual;
    private Label cardMainText;
    private Label decisionHintLabel;
    private StackPane characterPlaceholder;

    // Drag and drop logic
    private double startX;
    private static final double DRAG_THRESHOLD = 150.0;

    public GameViewJavaFXImpl() {
        try {
            Platform.startup(() -> { });
        } catch (final IllegalStateException e) {
            // Toolkit already started
        }

        Platform.runLater(() -> {
            this.stage = new Stage();
            this.stage.setTitle("Aurea - University Reign");

            // 1. Initialize labels and oracle dots
            initLabelsAndDots();

            // 2. Setup Parameter Boxes with PNG icons
            this.financesBox = createParameterBox("businessman.png", financesLabel, finDot);
            this.studentsBox = createParameterBox("student.png", studentsLabel, stuDot);
            this.professorsBox = createParameterBox("professor.png", professorsLabel, proDot);
            this.reputationBox = createParameterBox("mum.png", reputationLabel, repDot);

            final HBox topBar = new HBox(45);
            topBar.setAlignment(Pos.CENTER);
            topBar.setPadding(new Insets(40, 0, 20, 0));
            topBar.getChildren().addAll(financesBox, studentsBox, professorsBox, reputationBox);

            // 3. Setup the main game card
            setupCard();

            // 4. Create the root layout with a radial gradient background
            final BorderPane root = new BorderPane();
            
            // Professional background gradient (Dark blue to Black)
            RadialGradient bgGradient = new RadialGradient(
                0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#34495e")),
                new Stop(1, Color.web("#1a1a1a"))
            );
            root.setBackground(new Background(new BackgroundFill(bgGradient, CornerRadii.EMPTY, Insets.EMPTY)));

            root.setTop(topBar);
            root.setCenter(new StackPane(cardVisual));

            final Scene scene = new Scene(root, 900, 800);
            this.stage.setScene(scene);
            this.stage.show();
        });
    }

    private void initLabelsAndDots() {
        this.financesLabel = new Label("50");
        this.studentsLabel = new Label("50");
        this.professorsLabel = new Label("50");
        this.reputationLabel = new Label("50");
        this.cardMainText = new Label("Loading card...");
        this.decisionHintLabel = new Label("");

        // Oracle dots with a golden glow style
        this.finDot = createOracleDot();
        this.stuDot = createOracleDot();
        this.proDot = createOracleDot();
        this.repDot = createOracleDot();
    }

    private Circle createOracleDot() {
        Circle dot = new Circle(6, Color.GOLD);
        dot.setStroke(Color.WHITE);
        dot.setStrokeWidth(1.5);
        dot.setOpacity(0); // Invisible by default
        return dot;
    }

    private void setupCard() {
        // Area for the character illustration
        characterPlaceholder = new StackPane();
        characterPlaceholder.setPrefSize(200, 200);
        characterPlaceholder.setStyle("-fx-background-color: #dcdde1; -fx-background-radius: 10; -fx-border-color: #7f8c8d; -fx-border-width: 1;");
        
        // Main event text
        cardMainText.setWrapText(true);
        cardMainText.setStyle("-fx-font-size: 19px; -fx-font-family: 'Verdana'; -fx-text-alignment: center; -fx-text-fill: #2f3640;");
        cardMainText.setMaxWidth(260);

        // Decision preview text (YES/NO)
        decisionHintLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 26px;");
        
        this.cardVisual = new VBox(25);
        this.cardVisual.setAlignment(Pos.CENTER);
        this.cardVisual.setPadding(new Insets(20));
        this.cardVisual.setStyle("-fx-background-color: #f5f6fa; -fx-background-radius: 25; "
                               + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 20, 0, 0, 10);");
        this.cardVisual.setMaxSize(320, 500);
        this.cardVisual.getChildren().addAll(decisionHintLabel, characterPlaceholder, cardMainText);

        // Mouse event handlers
        cardVisual.setOnMousePressed(e -> startX = e.getSceneX());
        cardVisual.setOnMouseDragged(this::handleDrag);
        cardVisual.setOnMouseReleased(this::handleRelease);
    }

    private VBox createParameterBox(String fileName, Label valueLabel, Circle dot) {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);

        // Stack used to layer the dot ABOVE the icon
        StackPane iconStack = new StackPane();
        
        try {
            Image icon = new Image(getClass().getResourceAsStream("/" + fileName));
            ImageView imageView = new ImageView(icon);
            imageView.setFitWidth(60);
            imageView.setFitHeight(60);
            imageView.setPreserveRatio(true);
            
            // Positioning the dot at the top center of the icon
            StackPane.setAlignment(dot, Pos.TOP_CENTER);
            dot.setTranslateY(-10); // Float slightly above the icon
            
            iconStack.getChildren().addAll(imageView, dot);
        } catch (Exception e) {
            iconStack.getChildren().add(new Label("?"));
        }

        valueLabel.setStyle("-fx-text-fill: #f5f6fa; -fx-font-size: 18px; -fx-font-weight: bold; "
                          + "-fx-effect: dropshadow(one-pass-box, black, 3, 0, 0, 0);");
        container.getChildren().addAll(iconStack, valueLabel);
        return container;
    }

    private void handleDrag(javafx.scene.input.MouseEvent event) {
        double offsetX = event.getSceneX() - startX;
        cardVisual.setTranslateX(offsetX);
        cardVisual.setRotate(offsetX * 0.08); // Rotation effect

        if (Math.abs(offsetX) > 30 && currentCard != null) {
            boolean isRight = offsetX > 0;
            decisionHintLabel.setOpacity(Math.min(Math.abs(offsetX) / DRAG_THRESHOLD, 1.0));
            decisionHintLabel.setText(isRight ? currentCard.getApproval().getAnswer() : currentCard.getRefusal().getAnswer());
            decisionHintLabel.setStyle("-fx-text-fill: " + (isRight ? "#44bd32;" : "#e84118;"));
            highlightParameters(isRight);
        } else {
            decisionHintLabel.setOpacity(0);
            resetHighlights();
        }
    }

    private void handleRelease(javafx.scene.input.MouseEvent event) {
        double offsetX = event.getSceneX() - startX;
        if (Math.abs(offsetX) > DRAG_THRESHOLD && controller != null) {
            controller.makeDecision(offsetX > 0);
        }
        // Snap back to center
        cardVisual.setTranslateX(0);
        cardVisual.setRotate(0);
        decisionHintLabel.setOpacity(0);
        resetHighlights();
    }

    private void highlightParameters(boolean isApproval) {
        if (controller == null) return;
        Set<ParameterType> affected = controller.previewDecision(isApproval);
        resetHighlights();
        affected.forEach(type -> {
            switch (type) {
                case FINANCES -> finDot.setOpacity(1);
                case STUDENTS -> stuDot.setOpacity(1);
                case PROFESSORS -> proDot.setOpacity(1);
                case REPUTATION -> repDot.setOpacity(1);
            }
        });
    }

    private void resetHighlights() {
        if (finDot != null) { 
            finDot.setOpacity(0); 
            stuDot.setOpacity(0); 
            proDot.setOpacity(0); 
            repDot.setOpacity(0); 
        }
    }

    @Override
    public void displayCard(Card card) {
        this.currentCard = card;
        Platform.runLater(() -> {
            if (cardMainText != null && card != null) {
                // Should be replaced with card.getDescription() once available
                cardMainText.setText("Decision:\n" + card.getApproval().getAnswer());
            }
        });
    }

    @Override
    public void updateParameters(int f, int s, int p, int r) {
        Platform.runLater(() -> {
            if (financesLabel != null) {
                financesLabel.setText(String.valueOf(f));
                studentsLabel.setText(String.valueOf(s));
                professorsLabel.setText(String.valueOf(p));
                reputationLabel.setText(String.valueOf(r));
            }
        });
    }

    @Override public void setController(it.unibo.aurea.controller.api.GameController c) { this.controller = c; }
    @Override public void showVictory() { Platform.runLater(() -> cardMainText.setText("VICTORY!")); }
    @Override public void showDefeat() { Platform.runLater(() -> cardMainText.setText("DEFEAT!")); }
    @Override public void showGameOver(String reason) { Platform.runLater(() -> cardMainText.setText("GAME OVER:\n" + reason)); }
}
