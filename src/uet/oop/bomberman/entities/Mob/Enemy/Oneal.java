package uet.oop.bomberman.entities.Mob.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Mob.Enemy.AI.onealAI;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.graphics.Sprite;


public class Oneal extends Enemy {

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        ai = new onealAI();
        direction = ai.calculateDirection();
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.oneal_left1.getFxImage();
                if(moving) {
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 20).getFxImage();
                }
                break;
            case 1:
                img = Sprite.oneal_left1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 20).getFxImage();
                }
                break;
            case 2:
                img = Sprite.oneal_right1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 20).getFxImage();
                }
                break;
            case 3:
                img = Sprite.oneal_right1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 20).getFxImage();
                }
                break;
            default:
                img = Sprite.oneal_left1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 20).getFxImage();
                }
                break;
        }
    }

}
