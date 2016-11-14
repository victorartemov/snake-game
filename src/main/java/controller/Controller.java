package controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.Frog;
import model.GameWorld;
import model.Snake;
import model.SnakePart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виктор on 23.10.2016.
 */
public class Controller {

    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final String PAUSE = "Pause";
    private static final String RESUME = "Resume";

    private final int GAME_SCREEN_SIZE = 512;
    private final int GAME_BOARD_CELL_SIZE = 32;

    //три возможных скорости игры
    private final float SLOW = 0.02f;
    private final float MEDIUM = 0.03f;
    private final float FAST = 0.04f;

    private GraphicsContext gc;
    private Canvas canvas;
    private AnimationTimer timer;

    private Image gameBackground;

    private GameWorld game;
    private int score;
    private Snake snake;

    @FXML
    private AnchorPane gamePart;

    @FXML
    private AnchorPane controlPart;

    @FXML
    private Button startButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Label scoreLabel;

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public AnchorPane getGamePart() {
        return gamePart;
    }

    public AnchorPane getControlPart() {
        return controlPart;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getPauseButton() {
        return pauseButton;
    }

    @FXML
    protected void startButtonClicked() {

        if (startButton.getText().equals(START)) {
            game = new GameWorld();
            snake = game.getSnake();
            game.placeFrog();
            score = game.getScore();
            timer.start();
            startButton.setText(STOP);
            pauseButton.setDisable(false);
        }
        //stopping the game
        else {
            game.setGameOver(true);
            timer.stop();
            initializeStartScreen();
            startButton.setText(START);
            pauseButton.setDisable(true);
            pauseButton.setText(PAUSE);
        }
    }

    @FXML
    protected void pauseButtonClicked() {
        if(pauseButton.getText().equals(PAUSE)){
            snake.setRunning(false); //вообще это излишне, но чисто формально мы приостановили поток
            game.setRunning(false); // а по-настоящему игра приостанавливается тут
            pauseButton.setText(RESUME);
        } else {
            snake.setRunning(true);
            game.setRunning(true);
            pauseButton.setText(PAUSE);
        }
    }


    @FXML
    public void initialize() {

        pauseButton.setDisable(true);
        canvas = new Canvas(GAME_SCREEN_SIZE, GAME_SCREEN_SIZE);
        gc = canvas.getGraphicsContext2D();
        gamePart.getChildren().add(canvas);
        gameBackground = new Image("images/gameBackground.png");
        initializeStartScreen();

        //не вешается на канву, надо на сцену
        canvas.setOnKeyPressed(event -> keyPressed(event.getCode().toString()));

        canvas.setOnMouseClicked(event -> mouseClicked(event.getButton().toString()));

        //магический класс, который всегда выдает 60 фэпэсов, но у нас все равно змейка живет по тикам
        //описанным в гласе GameWorld
        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, GAME_SCREEN_SIZE, GAME_SCREEN_SIZE);
                gc.drawImage(gameBackground, 0, 0, GAME_SCREEN_SIZE, GAME_SCREEN_SIZE);
                renderSnake();
                renderFrog(game.getFrog());
                updateScoreLabel();
                game.update(FAST);

                //змейка скушала себя
                if(game.isGameOver()){
                    timer.stop();
                    initializeStartScreen();
                    startButton.setText(START);
                    pauseButton.setText(PAUSE);
                    pauseButton.setDisable(true);
                }
            }
        };
    }

    public void renderSnake() {
        for (SnakePart snakePart : snake.getParts()) {
            gc.drawImage(snakePart.getImage(),
                    snakePart.getX() * GAME_BOARD_CELL_SIZE, snakePart.getY() * GAME_BOARD_CELL_SIZE,
                    GAME_BOARD_CELL_SIZE, GAME_BOARD_CELL_SIZE);
        }
    }

    public void renderFrog(Frog frog) {
        gc.drawImage(frog.getImage(),
                frog.getX() * GAME_BOARD_CELL_SIZE, frog.getY() * GAME_BOARD_CELL_SIZE,
                GAME_BOARD_CELL_SIZE, GAME_BOARD_CELL_SIZE);
    }

    public void keyPressed(String code) {
        if (code.equals("LEFT")) {
            snake.turnLeft();
            System.out.println(code);
        }
        if (code.equals("RIGHT")) {
            snake.turnRight();
            System.out.println(code);
        }
    }

    public void mouseClicked(String button) {
        if (button.equals("PRIMARY")) {
            snake.turnLeft();
        }
        if (button.equals("SECONDARY")) {
            snake.turnRight();
        }
    }

    public void initializeStartScreen() {
        gc.drawImage(new Image("images/startBackground.png"), 0, 0, GAME_SCREEN_SIZE, GAME_SCREEN_SIZE);
    }

    public void updateScoreLabel() {
        int newScore = game.getScore();
        if (newScore > score) score = newScore;
        scoreLabel.setText("Score: " + Integer.toString(score));
    }

}
