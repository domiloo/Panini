package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Player;

import java.util.ArrayList;

public class Controller {

    private ArrayList<Canvas> canvases;

    private ArrayList<Player> players;

    public void setPlayers(ArrayList<Player> player) {
        this.players = player;
    }

    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize() {
        canvases = new ArrayList<>();
        int canvasHeight = 200;
        for (int i = 0;i < Main.NUMBER_OF_PLAYER;i++) {
            Canvas newCanvas = new Canvas();
            newCanvas.setHeight(canvasHeight);
            newCanvas.setWidth(1800);
            newCanvas.getGraphicsContext2D().strokeRect(0+10,0+10,1800,canvasHeight);
            gridPane.add(newCanvas,0,i);
            canvases.add(newCanvas);
        }

    }

    public void reDraw() {
        for (int i = 0;i < Main.NUMBER_OF_PLAYER;i++) {
            Player player = players.get(i);
            GraphicsContext gc = canvases.get(i).getGraphicsContext2D();

            for (int j = 0; j < Main.NUMBER_OF_CARDS; j++) {
                gc.setFill(Color.GREEN);
                gc.setLineWidth(1);
                gc.fillOval(j*10,5,8,8);
            }
        }
    }

}
