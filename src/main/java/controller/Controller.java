package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.Game;
import model.Point;

import java.util.List;

/**
 * Created by Виктор on 23.10.2016.
 */
public class Controller {

    private final int GAME_SCREEN_SIZE = 512;
    private final int GAME_BOARD_CELL_SIZE = 32;

    private Game game;
    private GraphicsContext gc;
    private Canvas canvas;

    @FXML
    private AnchorPane gamePart;

    @FXML
    private AnchorPane controlPart;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    public AnchorPane getGamePart() {
        return gamePart;
    }

    public AnchorPane getControlPart() {
        return controlPart;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    @FXML
    protected void startButtonClicked(){

    }

    @FXML
    protected void stopButtonClicked(){

    }

    @FXML
    public void initialize(){
        game = new Game();
        canvas = new Canvas(GAME_SCREEN_SIZE,GAME_SCREEN_SIZE);
        gc = canvas.getGraphicsContext2D();
        gamePart.getChildren().add(canvas);

        renderSnake();
    }

    public void renderSnake(){
        List<Point> snakeParts = game.getSnakeParts();
        for(Point snakePart : snakeParts){
            gc.drawImage(snakePart.getImage(),
                    snakePart.getPositionX() * GAME_BOARD_CELL_SIZE, snakePart.getPositionY() * GAME_BOARD_CELL_SIZE,
                    GAME_BOARD_CELL_SIZE, GAME_BOARD_CELL_SIZE
            );
        }
    }

}
