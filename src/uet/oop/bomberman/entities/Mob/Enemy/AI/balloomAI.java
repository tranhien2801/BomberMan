package uet.oop.bomberman.entities.Mob.Enemy.AI;

public class balloomAI extends AI{
    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }

}
