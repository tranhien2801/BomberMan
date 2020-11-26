package uet.oop.bomberman.entities.Mob.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.Enemy.AI.AI;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.game_sound;

public abstract class Enemy extends Mob {
    protected AI ai;

    public game_sound enemy_die = new game_sound();

    protected Image imgDead;

    protected int xd = 0;
    protected int yd = 0;

    public Enemy(int x, int y, Image img) {

        super(x, y, img);

        imgDead = img;

        direction = 1;

        if(direction == 0) {
            xd = 0;
            yd--;
        }
        if(direction == 1) {
            xd = 0;
            yd++;
        }
        if(direction == 2) {
            yd = 0;
            xd--;
        }
        if(direction == 3) {
            yd = 0;
            xd++;
        }

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
        if(ya > 0) direction = 1;
        if(xa < 0) direction = 2;
        if(xa > 0) direction = 3;
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
        bombermanGame.score += 100;
    }

    @Override
    protected void afterKill() {
        if(timeAfter > 0) {
            timeAfter--;
            img = imgDead;
        } else {
            bombermanGame.enemies.remove(this);
        }
    }

    @Override
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
        Entity entity4 = bombermanGame.getEntity(x2, y2);
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
        if (direction != -1) {
            if (entity1 instanceof Wall || entity1 instanceof Brick
                    || entity2 instanceof Wall || entity2 instanceof Brick
                    || entity3 instanceof Wall || entity3 instanceof Brick
                    || entity4 instanceof Wall || entity4 instanceof Brick) return false;
        }

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

    @Override
    protected void calculateMove() {

        if (!canMove(xd*speed, yd*speed)) {
            int cur = direction;
            while(direction == cur) direction = ai.calculateDirection();

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

        }

        if(xd != 0 || yd != 0) {
            move(xd * speed, yd * speed);
            moving = true;
        } else {
            moving = false;
        }
    }

    @Override
    public boolean checkLive() {
        if(bombermanGame.isExplosion((int)(x + 0.1), (int) (y + 0.1)) || bombermanGame.isExplosion((int) (x + 0.1), (int)(y + 0.9))
                || bombermanGame.isExplosion((int)(x + 0.9), (int) (y + 0.1)) || bombermanGame.isExplosion((int)(x + 0.9), (int)(y + 0.9))) {
            enemy_die.sound_effect("sound/enemy_die.wav", 0.5, false);
            return false;
        }
        return true;
    }

    public abstract void chooseSprite();

    public int getDirection() {
        return direction;
    }
}
