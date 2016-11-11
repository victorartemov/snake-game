package model;

import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Виктор on 11.11.2016.
 */
public class Snake extends GameItem {

    //thread status flags
    private boolean updating;
    private boolean isPaused;

    private int length;
    private Duration currentDuration;
    private List<Point> snake;
    private List<Point> oldCoordinates;

    public Snake(int length, List<Point> snake) {
        this.length = length;
        this.snake = snake;
        this.oldCoordinates = snake;
    }

    public Snake() {
        this.length = 2;
        this.snake = new LinkedList<Point>();
        this.snake.add(new Point(1, 0, new Image("images/head.png")));
        this.snake.add(new Point(0, 0, new Image("images/tail.png")));
    }

    public List<Point> getSnake() {
        return this.snake;
    }

    @Override
    public void run() {
        do {
            if (updating) {
                if (!isPaused) {
                    update();
                }
            } else {
                return;
            }
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {

            }
        }
        while (true);
    }

    public void update(){
        switch (currentDuration){
            case TOP: {
                snake.get(0).setPositionY(snake.get(0).getPositionX()-1);
            }
            case RIGHT:{
                snake.get(0).setPositionX(snake.get(0).getPositionX()+1);
            }
            case BOTTOM:{
                snake.get(0).setPositionY(snake.get(0).getPositionX()+1);
            }
            case LEFT: {
                snake.get(0).setPositionX(snake.get(0).getPositionX()-1);
            }
        }
        for(int i = 1; i < snake.size() - 1; i++){
            snake.get(i).setPositionX(oldCoordinates.get(i-1).getPositionX());
            snake.get(i).setPositionY(oldCoordinates.get(i-1).getPositionY());
        }
        for (int i = 0; i < snake.size() - 1; i++){
            oldCoordinates.set(i, snake.get(i));
        }
    }
}
