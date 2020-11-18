package uet.oop.bomberman.entities.Mob.Enemy.AI;

import java.util.Random;

public class balloomAI extends AI{

    public int calculate() {

        return random.nextInt(4);
    }
}
