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

    protected double _steps;
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
    protected boolean canChangeOrient(double x, double y) {
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

        int count = 4;
        if (entity1 instanceof Wall || entity1 instanceof Brick) count--;
        if (entity2 instanceof Wall || entity2 instanceof Brick) count--;
        if (entity3 instanceof Wall || entity3 instanceof Brick) count--;
        if (entity4 instanceof Wall || entity4 instanceof Brick) count--;
        System.out.println(entity1.getClass());
        System.out.println(entity2.getClass());
        System.out.println(entity3.getClass());
        System.out.println(entity4.getClass());
        System.out.println();
        if (count >= 2) return true;

        return false;
    }

    @Override
    public boolean checkLive() {
        if(bombermanGame.isExplosion((int) x, (int) y) || bombermanGame.isExplosion((int) x, (int)(y + 1))
                || bombermanGame.isExplosion((int)(x + 1), (int) y) || bombermanGame.isExplosion((int)(x + 1), (int)(y + 1))) {
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
