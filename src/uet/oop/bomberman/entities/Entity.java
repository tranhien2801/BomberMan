package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected BombermanGame bombermanGame;
    protected double x;
    protected double y;
    protected Image img;
    protected Sprite sprite;

    public Entity() {

    }

    public Entity(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }


    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }

    public abstract void update();


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
