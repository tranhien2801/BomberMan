package uet.oop.bomberman.entities.Mob.Enemy.AI;

public class onealAI extends AI {

    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}
