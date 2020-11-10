package uet.oop.bomberman;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class AnimatedEntity extends Entity {

    public AnimatedEntity(int x, int y, Image image) {
        super(x, y, image);
    }

    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500;

    public AnimatedEntity() {
        super();
    }

    protected void animate() {
        if (animate < MAX_ANIMATE) animate++;
        else animate = 0;
    }
}
