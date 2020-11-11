package uet.oop.bomberman.entities.Tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends AnimatedEntity {


    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1,
                Sprite.brick_exploded2, animate, 50).getFxImage();
    }


}

