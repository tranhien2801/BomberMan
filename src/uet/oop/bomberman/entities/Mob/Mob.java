package uet.oop.bomberman.entities.Mob;

import javafx.scene.image.Image;
import uet.oop.bomberman.AnimatedEntity;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Mob.Enemy.AI.AI;

public abstract class Mob extends AnimatedEntity {


    public double speed = 0.04;
    protected int direction = -1;
    protected boolean alive = true;
    protected boolean moving = false;
    public int timeAfter = 40;

    public Mob() {}

    public Mob(int x, int y, Image img) {
        super(x, y, img);
    }

    protected abstract void move(double xa, double ya);

    public abstract void kill();

    protected abstract void afterKill();

    protected abstract boolean canMove(double x, double y);

    protected abstract void calculateMove();

    public abstract boolean checkLive();

    public boolean isAlive() {
        return alive;
    }

    public boolean isMoving() {
        return moving;
    }

    public int getDirection() {
        return direction;
    }

}
