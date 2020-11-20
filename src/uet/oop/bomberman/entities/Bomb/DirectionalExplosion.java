package uet.oop.bomberman.entities.Bomb;

import uet.oop.bomberman.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Wall;

public class DirectionalExplosion extends AnimatedEntity {

    protected int direction;
    private int radius;
    protected int xOrigin, yOrigin;
    protected Explosion[] explosions;

    public DirectionalExplosion(int x, int y, int direction, int radius) {
        xOrigin = x;
        yOrigin = y;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.radius = radius;

        explosions = new Explosion[calculatePermitedDistance()];
        createExplosions();

    }

    private void createExplosions() {
        boolean last = false;

        int x = (int) this.x;
        int y = (int) this.y;
        for(int i = 0; i < explosions.length; i++) {
            if(i == explosions.length - 1) {
                last = true;
            } else {
                last = false;
            }
            switch (direction) {
                case 0:
                    y--;
                    break;
                case 1:
                    y++;
                    break;
                case 2:
                    x--;
                    break;
                case 3:
                    x++;
                    break;
            }
            explosions[i] = new Explosion(x, y, direction, last);
            bombermanGame.explosions.add(explosions[i]);

        }
    }


    private int calculatePermitedDistance() {
        int radius = 0;
        int x = (int) this.x;
        int y = (int) this.y;
        while (radius < this.radius) {
            if(direction == 0) y--;
            if(direction == 1) y++;
            if(direction == 2) x--;
            if(direction == 3) x++;

            Entity a = bombermanGame.getEntity(x, y);
            if(a instanceof Wall) break;
            radius++;
        }
        return radius;
    }


    @Override
    public void update() {
        for(int i = 0; i < explosions.length; i++) {
            explosions[i].update();
        }
    }

}
