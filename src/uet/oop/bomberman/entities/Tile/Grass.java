package uet.oop.bomberman.entities.Tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Grass extends Entity {

    public int direction;
    public int count;

    public Grass(int x, int y, int direction, int count, Image img) {
        super(x, y, img);
        this.direction = direction;
        this.count = count;
    }

    @Override
    public void update() {

    }

}
