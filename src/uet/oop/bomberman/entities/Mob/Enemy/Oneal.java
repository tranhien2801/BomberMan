package uet.oop.bomberman.entities.Mob.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Mob.Enemy.AI.balloomAI;
import uet.oop.bomberman.entities.Mob.Enemy.AI.onealAI;

public class Oneal extends Enemy {

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        ai = new onealAI();
        direction = ai.calculate();
    }
}
