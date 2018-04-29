package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Calculator;
import model.Player;

import java.util.ArrayList;

public class Controller {

    private final int CARDS_PER_ROW = 70;
    private ArrayList<Canvas> canvases;

    private ArrayList<Player> players;

    private Main main;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label doublesLabel;

    @FXML
    private Button calcButton;

    @FXML
    private Button calcStep;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize() {
        canvases = new ArrayList<>();
        int canvasHeight = 200;

        for (int i = 0;i < Calculator.getInstance().NUMBER_OF_PLAYER;i++) {
            Canvas newCanvas = new Canvas();
            newCanvas.setHeight(canvasHeight);
            newCanvas.setWidth(1800);
            newCanvas.getGraphicsContext2D().strokeRect(10, 10,1800,canvasHeight);
            gridPane.addRow(i,newCanvas);
            canvases.add(newCanvas);
        }
        progressBar.setProgress(0);
    }

    public void reDraw() {

        for (int i = 0;i < Calculator.getInstance().NUMBER_OF_PLAYER;i++) {
            Player player = Calculator.getInstance().getPlayer(i);
            GraphicsContext gc = canvases.get(i).getGraphicsContext2D();
            gc.clearRect(0,0,200,1800);
            for (int j = 0; j < Calculator.getInstance().NUMBER_OF_CARDS; j++) {

                gc.setFill(getColor(player.hasCard(j)));
                int row = j / CARDS_PER_ROW;
                int y = row * 25+ 10;
                int x = ((j-(row * CARDS_PER_ROW)) *25) + 10;
                gc.fillOval(x,y, 15,15);
            }
        }
        progressBar.setProgress(Calculator.getInstance().GIVEN_CARDS*1.0/(Calculator.getInstance().NUMBER_OF_CARDS*Calculator.getInstance().NUMBER_OF_PLAYER));
        doublesLabel.setText("Doubles drawn: " + Calculator.getInstance().DOUBLES );

    }

    private Color getColor(boolean found) {
        if (found) {
            return Color.GREEN;
        } else {
            return Color.RED;
        }
    }

    @FXML
    public void handleCalcClick() {
        int MAX_CARDS=800000;
        /*do {
            Calculator.getInstance().buyRandomCard();
            reDraw();
        } while (Calculator.getInstance().CARDS() < MAX_CARDS && !Calculator.getInstance().allPlayersComplete());
        */
        AnimationTimer loop = new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
                    Calculator.getInstance().buyRandomCard();
                    reDraw();
                if (Calculator.getInstance().CARDS() > MAX_CARDS || Calculator.getInstance().allPlayersComplete()) {
                    this.stop();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }
        };
        loop.start();
    }

    @FXML
    public void handleStepClick() {
        Calculator.getInstance().buyRandomCard();
        reDraw();
    }

}
