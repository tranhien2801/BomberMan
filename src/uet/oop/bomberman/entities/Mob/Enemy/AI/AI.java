package uet.oop.bomberman.entities.Mob.Enemy.AI;

import java.util.Random;

public abstract class AI {
    protected Random random = new Random();

    public abstract int calculateDirection(int x, int y);
}
