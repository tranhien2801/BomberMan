package main.java.uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Image getImage() {
        return img;
    }
}
