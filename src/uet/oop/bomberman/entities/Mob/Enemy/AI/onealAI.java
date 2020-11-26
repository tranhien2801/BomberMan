package uet.oop.bomberman.entities.Mob.Enemy.AI;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

import java.util.Queue;

public class onealAI extends AI {

    private Queue<Entity> que;
    private BombermanGame bombermanGame;

    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }


}
