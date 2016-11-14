package model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Runnable {

    //константы направления движения змейки.
    //Обратите внимание на логику методов поворота и зацените простоту и изящество :)
    private static final int UP = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;

    private List<SnakePart> parts;
    private int direction;

    private boolean isRunning; //бесполезный флаг, по сути он приостанавливает поток змеи,
    // но на игру это не влияет

    public Snake() {
        direction = DOWN;
        isRunning = true;
        parts = new ArrayList<SnakePart>() {{
            add(new SnakePart(0, 2, new Image("images/head.png")));
            add(new SnakePart(0, 1, new Image("images/body.png")));
            add(new SnakePart(0, 0, new Image("images/body.png")));
        }};
    }

    public void turnLeft() {
        direction += 1;
        if (direction > RIGHT)
            direction = UP;
    }

    public void turnRight() {
        direction -= 1;
        if (direction < UP)
            direction = RIGHT;
    }

    //кушаем лягушку и становимся длиннее на один элемент. Добавляем его в конец змейки
    public void eat() {
        SnakePart end = parts.get(parts.size() - 1);
        end.setImage(new Image("images/body.png"));
        parts.add(new SnakePart(end.getX(), end.getY(), new Image("images/body.png")));
    }

    public void move() {

        //Логика движения такова: сначала мы, начиная от последнего, перемещаем все элементы змеи
        //на позицию впереди стоящего элементы

        SnakePart head = parts.get(0);

        for (int i = parts.size() - 1; i > 0; i--) {
            SnakePart before = parts.get(i - 1);
            SnakePart part = parts.get(i);
            part.setX(before.getX());
            part.setY(before.getY());
        }

        //тепер двигает голову в зависимости от направления движения змеи
        switch (direction) {
            case UP:
                head.setY(head.getY() - 1);
                break;
            case LEFT:
                head.setX(head.getX() - 1);
                break;
            case DOWN:
                head.setY(head.getY() + 1);
                break;
            case RIGHT:
                head.setX(head.getX() + 1);
                break;
        }

        //вылазием с противоположной стороны экрана. Экран 16х16
        if (head.getX() < 0)
            head.setX(15);
        if (head.getX() > 15)
            head.setX(0);
        if (head.getY() < 0)
            head.setY(15);
        if (head.getY() > 15)
            head.setY(0);
    }

    //проверяем, не укусила ли змея сама себя
    public boolean checkBitten() {
        SnakePart head = parts.get(0);
        for (int i = 1; i < parts.size(); i++) {
            SnakePart part = parts.get(i);
            if (part.getX() == head.getX() && part.getY() == head.getY())
                return true;
        }
        return false;
    }

    public List<SnakePart> getParts() {
        return parts;
    }

    public void setParts(List<SnakePart> parts) {
        this.parts = parts;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    //вот, собственно, и сам код потока змеи. Он не производит движения! Просто дергается из класса
    //GameWorld, поэтому при остановки этого потока движение змейки непрекратится. Если хотите - переделайте логику
    @Override
    public void run() {
        if (isRunning)
            move();
    }
}
