package model;

import javafx.scene.image.Image;

/**
 * Created by Виктор on 11.11.2016.
 */
public class Point {
    private Image image;
    private int positionX;
    private int positionY;

    public Point(int positionY, int positionX, Image image) {
        this.positionY = positionY;
        this.image = image;
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
}
