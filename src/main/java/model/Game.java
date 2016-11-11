package model;

import java.util.List;

/**
 * Created by Виктор on 11.11.2016.
 */
public class Game {
    private GameBoard gameBoard;
    private Snake snake;
    private List<Frog> frogs;

    public Game(){
        this.gameBoard = new GameBoard();
        this.snake = new Snake();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public List<Point> getSnakeParts(){
        return this.snake.getSnake();
    }
}
