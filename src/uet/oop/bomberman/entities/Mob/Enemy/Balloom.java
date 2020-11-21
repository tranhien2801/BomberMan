package uet.oop.bomberman.entities.Mob.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.GameControl.Player;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.game_sound;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Mob {

    public game_sound enemy_die = new game_sound();

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(!checkLive())   kill();
        if(!alive) {
            afterKill();
            return;
        }
        chooseSprite();
        animate();
        calculateMove();
    }

    @Override
    protected void move(double xa, double ya) {
        if(ya < 0) direction = 0;
        if(ya > 0)  direction = 1;
        if(xa < 0)  direction = 2;
        if(xa > 0)  direction = 3;
        if(canMove(0, ya)) {
            this.y += ya;
        }
        if(canMove(xa, 0)) {
            this.x += xa;
        }
    }

    @Override
    public void kill() {
        if(!alive)  return;
        alive = false;
    }

    @Override
    protected void afterKill() {
        if(timeAfter > 0) {
            timeAfter--;
            img = Sprite.balloom_dead.getFxImage();
        } else {
            BombermanGame.enemies.remove(this);
        }
    }

    @Override
    protected boolean canMove(double x, double y) {
        if(x != 0 && y != 0) return false;
        double xa = x + this.x;
        double ya = y + this.y;

        int x1 = (int) (xa + 0.1);
        int x2 = (int) (xa + 0.94);
        int y1 = (int) (ya + 0.15);
        int y2 = (int) (ya + 0.94);
        Entity entity1 = BombermanGame.getEntity(x1, y1);
        Entity entity2 = BombermanGame.getEntity(x1, y2);
        Entity entity3 = BombermanGame.getEntity(x2, y1);
        Entity entity4 = BombermanGame.getEntity(x2, y2);
        if(entity1 instanceof Wall || entity1 instanceof Brick
                || entity2 instanceof Wall || entity2 instanceof Brick
                || entity3 instanceof Wall || entity3 instanceof Brick
                || entity4 instanceof Wall || entity4 instanceof Brick)
            return false;

        return true;
    }

    @Override
    protected void calculateMove() {
        int xa = 1, ya = 0;
        if(xa != 0 || ya != 0) {
            move(xa * speed, ya * speed);
            moving = true;
        } else {
            moving = false;
        }
    }

    @Override
    public boolean checkLive() {
        if(BombermanGame.isExplosion((int) x, (int) y) || BombermanGame.isExplosion((int) x, (int)(y + 1))
                || BombermanGame.isExplosion((int)(x + 1), (int) y) || BombermanGame.isExplosion((int)(x + 1), (int)(y + 1))) {
            enemy_die.sound_effect("sound/enemy_die.wav", 0.5, false);
            return false;
        }
        return true;
    }

    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.player_down.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 20).getFxImage();
                }
                break;
            case 2:
                img = Sprite.player_left.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();
                }
                break;
            case 3:
                img = Sprite.player_right.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 20).getFxImage();
                }
                break;
            default:
                img = Sprite.player_right.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();
                }
                break;
        }
    }
}
