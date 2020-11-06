package uet.oop.bomberman;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class AnimatedEntity extends Entity {

    public AnimatedEntity(double x, double y, Image img) {
        super(x, y, img);
    }

    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500;

    public AnimatedEntity() {
        super();
    }

    protected void animate() {
        if(animate < MAX_ANIMATE) animate++;
        else animate = 0;
    }
}
