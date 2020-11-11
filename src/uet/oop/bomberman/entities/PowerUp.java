package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class PowerUp extends Entity {
    private String ms;

    public PowerUp(int x, int y, Image img, String ms) {
        super(x, y, img);
        this.ms = ms;
    }

    @Override
    public void update() {

    }

    public String getMs() {
        return ms;
    }
}
