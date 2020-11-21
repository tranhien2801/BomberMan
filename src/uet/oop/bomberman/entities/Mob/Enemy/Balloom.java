package uet.oop.bomberman.entities.Mob.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Mob.Enemy.AI.balloomAI;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {


    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        ai = new balloomAI();
        direction = ai.calculateDirection();
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.balloom_left1.getFxImage();
                if(moving) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();
                }
                break;
            case 1:
                img = Sprite.balloom_left1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 20).getFxImage();
                }
                break;
            case 2:
                img = Sprite.balloom_right1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();
                }
                break;
            case 3:
                img = Sprite.balloom_right1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 20).getFxImage();
                }
                break;
            default:
                img = Sprite.balloom_left1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();
                }
                break;
        }
    }

}
