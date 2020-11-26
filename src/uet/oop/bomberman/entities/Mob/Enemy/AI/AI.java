package uet.oop.bomberman.entities.Mob.Enemy.AI;

import uet.oop.bomberman.entities.Entity;

import java.util.Random;

public abstract class AI {
    protected Random random = new Random();

    public abstract int calculateDirection();
}
