package uet.oop.bomberman.entities.Bomb;

import uet.oop.bomberman.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends AnimatedEntity {

    protected boolean last;
    protected int direction;

    public Explosion(int x, int y, int direction, boolean last) {
        this.x = x;
        this.y = y;
        this.last = last;
        this.direction = direction;
        switch (direction) {
            case 0:
                if(!last) {
                    img = Sprite.explosion_vertical.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_top_last.getFxImage();
                }
                break;
            case 1:
                if(!last) {
                    img = Sprite.explosion_vertical.getFxImage();
                } else {
                    img = Sprite.explosion_vertical_down_last.getFxImage();
                }
                break;
            case 2:
               if(!last) {
                    img = Sprite.explosion_horizontal.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal.getFxImage();
                }
                break;
            case 3:
                if(!last) {
                    img = Sprite.explosion_horizontal.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal_right_last.getFxImage();
                }
                break;

        }
    }

    @Override
    public void update() {
        switch (direction) {
            case 0:
                if(!last) {
                    img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2, animate, 35).getFxImage();
                } else {
                    img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,
                            Sprite.explosion_vertical_top_last2, animate, 35).getFxImage();
                }
                break;
            case 1:
                if(!last) {
                    img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2, animate, 35).getFxImage();
                } else {
                    img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1,
                            Sprite.explosion_vertical_down_last2, animate, 35).getFxImage();
                }
                break;
            case 2:
                if(!last) {
                    img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2, animate, 35).getFxImage();
                } else {
                    img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,
                            Sprite.explosion_horizontal_left_last2, animate, 35).getFxImage();
                }
                break;
            case 3:
                if(!last) {
                    img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2, animate, 35).getFxImage();
                } else {
                    img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,
                            Sprite.explosion_horizontal_right_last2, animate, 35).getFxImage();
                }
                break;
        }
    }

}
