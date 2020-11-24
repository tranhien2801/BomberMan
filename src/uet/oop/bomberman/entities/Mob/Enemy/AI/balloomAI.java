package uet.oop.bomberman.entities.Mob.Enemy.AI;

public class balloomAI extends AI{
    @Override
    public int calculateDirection(int x, int y) {
        return random.nextInt(4);
    }
}
