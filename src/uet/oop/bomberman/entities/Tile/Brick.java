package uet.oop.bomberman.entities.Tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Brick extends Entity {
    public Brick(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}

