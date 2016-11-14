package model;

import javafx.scene.image.Image;

//класс лягушки - ничего интересного и сложного. Позиция на игровом поле и картинка

public class Frog {

    private int x, y;
    private Image image;

    public Frog(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = new Image("images/star.png");
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
