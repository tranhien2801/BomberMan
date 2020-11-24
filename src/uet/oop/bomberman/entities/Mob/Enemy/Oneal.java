package uet.oop.bomberman.entities.Mob.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.Enemy.AI.onealAI;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;


public class Oneal extends Enemy {

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        ai = new onealAI();
        direction = ai.calculateDirection(0, 0);
    }

    protected boolean canMove(double x, double y) {

        double xa = x + this.x;
        double ya = y + this.y;

        int x1 = (int) (xa + 0.1);
        int x2 = (int) (xa + 0.94);
        int y1 = (int) (ya + 0.15);
        int y2 = (int) (ya + 0.94);

        Entity entity1 = bombermanGame.getEntity(x1, y1);
        Entity entity2 = bombermanGame.getEntity(x1, y2);
        Entity entity3 = bombermanGame.getEntity(x2, y1);
        Entity entity4 = bombermanGame.getEntity(x2 - 1, y2);

        if (direction == 0 && (entity1 instanceof Wall || entity1 instanceof Brick)) {
            return false;
        }
        if (direction == 1 && (entity2 instanceof Wall || entity2 instanceof Brick)) {
            return false;
        }
        if (direction == 2 && (entity4 instanceof Wall || entity4 instanceof Brick)) {
            return false;
        }
        if (direction == 3 && (entity3 instanceof Wall || entity3 instanceof Brick)) {
            return false;
        }


        /*
        if (direction != -1) {
            if (entity1 instanceof Wall || entity1 instanceof Brick
                    || entity2 instanceof Wall || entity2 instanceof Brick
                    || entity3 instanceof Wall || entity3 instanceof Brick
                    || entity4 instanceof Wall || entity4 instanceof Brick) return false;
        }
*/
        int xd1 = (int) (this.x + 0.1);
        int xd2 = (int) (this.x + 0.7);
        int yd1 = (int) (this.y + 0.15);
        int yd2 = (int) (this.y + 0.9);
        Entity e1 = bombermanGame.getEntity(xd1, yd1);
        Entity e2 = bombermanGame.getEntity(xd1, yd2);
        Entity e3 = bombermanGame.getEntity(xd2, yd1);
        Entity e4 = bombermanGame.getEntity(xd2, yd2);
        if((entity1 instanceof Bomb || entity2 instanceof Bomb
                || entity3 instanceof Bomb || entity4 instanceof Bomb)
                && (e1 instanceof Grass && e2 instanceof Grass
                && e3 instanceof Grass && e4 instanceof Grass)) {
            return false;
        }

        return true;
    }

    protected void calculateMove() {

            direction = ai.calculateDirection(0, 0);
            canChangeOrient(xd*speed, yd*speed);
            if(direction == 0) {
                xd = 0;
                yd = -1;
            }
            if(direction == 1) {
                xd = 0;
                yd = 1;
            }
            if(direction == 2) {
                yd = 0;
                xd = -1;
            }
            if(direction == 3) {
                yd = 0;
                xd = 1;
            }

        if(xd != 0 || yd != 0) {
            move(xd * speed, yd * speed);
            moving = true;
        } else {
            moving = false;
        }
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
