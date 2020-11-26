package uet.oop.bomberman.entities.Mob.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameControl.Player;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.Enemy.AI.onealAI;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.LinkedList;
import java.util.Queue;


public class Oneal extends Enemy {

    private int i = (int)bombermanGame.player.getX();
    private int j = (int)bombermanGame.player.getY();

    private Queue<Grass> que;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        this.direction = -1;
        ai = new onealAI();
        direction = ai.calculateDirection();
    }

  /*  @Override
    protected void calculateMove(){
        findWay(i, j);
        if(direction == 0) {
            xd = 0;
            yd = -1;
        }
        if(direction == 1) {
            xd = 0;
            yd = 1;
        }
        if(direction == 2) {
            yd = 0;
            xd = -1;
        }
        if(direction == 3) {
            yd = 0;
            xd = 1;
        }
        if(xd != 0 || yd != 0) {
            move(xd * speed, yd * speed);
            moving = true;
        } else {
            moving = false;
        }
    }


    public void findWay(int x, int y) {
        int xa = (int)(this.x + 0.1);
        int ya = (int)(this.y + 0.1);
        if(xa == x && ya == y)  return;
        que = new LinkedList<>();
        if(!(bombermanGame.getEntity(xa, ya - 1) instanceof Wall
        || bombermanGame.getEntity(xa, ya - 1) instanceof Brick
        || bombermanGame.getEntity(xa, ya - 1) instanceof Bomb))
            que.add(new Grass(xa, ya - 1, 0, 9, Sprite.grass.getFxImage()));
        if(!(bombermanGame.getEntity(xa, ya + 1) instanceof Wall
        || bombermanGame.getEntity(xa, ya + 1) instanceof Brick
        || bombermanGame.getEntity(xa, ya + 1) instanceof Bomb))
            que.add(new Grass(xa, ya + 1, 1, 9, Sprite.grass.getFxImage()));
        if(!(bombermanGame.getEntity(xa - 1, ya) instanceof Wall
        || bombermanGame.getEntity(xa, ya + 1) instanceof Brick
        || bombermanGame.getEntity(xa, ya + 1) instanceof Bomb))
            que.add(new Grass(xa - 1, ya, 2, 9, Sprite.grass.getFxImage()));
        if(!(bombermanGame.getEntity(xa + 1, ya) instanceof Wall
        || bombermanGame.getEntity(xa + 1, ya) instanceof Brick
        || bombermanGame.getEntity(xa + 1, ya) instanceof Bomb))
            que.add(new Grass(xa + 1, ya, 3, 9, Sprite.grass.getFxImage()));

        if(que.size() == 0) {
            i = (int)(bombermanGame.player.getX() + 0.2);
            j = (int)(bombermanGame.player.getY() + 0.2);
            return;
        }
        while(!que.isEmpty()) {
            Grass grass = que.poll();
            System.out.println(grass.getX() + " " + grass.getY() + " " + grass.direction + " " + grass.count);
            if(grass.count == 0) {
                if(bombermanGame.player != null) {
                    i = (int) (bombermanGame.player.getX() + 0.2);
                    j = (int) (bombermanGame.player.getY() + 0.2);
                }
                return;
            }
            if(grass.getX() == x && grass.getY() == y) {
                System.out.println("Yes" + grass.getX() + " " + grass.getY());
                if(grass.direction == 0) {
                    this.direction = 0;
                }
                if(grass.direction == 1) {
                    this.direction = 1;
                }
                if(grass.direction == 2) {
                    this.direction = 2;
                }
                if(grass.direction == 3) {
                    this.direction = 3;
                }
                return;
            }

            if(!(bombermanGame.getEntity((int) grass.getX(), (int) grass.getY() - 1) instanceof Wall
                    || bombermanGame.getEntity((int) grass.getX(), (int) grass.getY() - 1) instanceof Brick
                    || bombermanGame.getEntity((int) grass.getX(), (int) grass.getY() - 1) instanceof Bomb))
                que.add(new Grass((int) grass.getX(), (int) grass.getY() - 1, grass.direction, grass.count-1, Sprite.grass.getFxImage()));
            if(!(bombermanGame.getEntity((int) grass.getX(), (int) grass.getY() + 1) instanceof Wall
                    || bombermanGame.getEntity((int) grass.getX(), (int) grass.getY() + 1) instanceof Brick
                    || bombermanGame.getEntity((int) grass.getX(), (int) grass.getY() + 1) instanceof Bomb))
                que.add(new Grass((int) grass.getX(), (int) grass.getY() + 1, grass.direction, grass.count-1, Sprite.grass.getFxImage()));
            if(!(bombermanGame.getEntity((int) grass.getX() - 1, (int) grass.getY()) instanceof Wall
                    || bombermanGame.getEntity((int) grass.getX() - 1, (int) grass.getY()) instanceof Brick
                    || bombermanGame.getEntity((int) grass.getX() - 1, (int) grass.getY()) instanceof Bomb)){
                que.add(new Grass((int) grass.getX() - 1, (int) grass.getY(), grass.direction, grass.count-1, Sprite.grass.getFxImage()));
            }
            if(!(bombermanGame.getEntity((int) grass.getX() + 1, (int) grass.getY()) instanceof Wall
                    || bombermanGame.getEntity((int) grass.getX() + 1, (int) grass.getY()) instanceof Brick
                    || bombermanGame.getEntity((int) grass.getX() + 1, (int) grass.getY()) instanceof Bomb))
                que.add(new Grass((int) grass.getX() + 1, (int) grass.getY(), grass.direction, grass.count-1, Sprite.grass.getFxImage()));
        }

        //return true;
    }*/


    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.oneal_left1.getFxImage();
                if(moving) {
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 20).getFxImage();
                }
                break;
            case 1:
                img = Sprite.oneal_left1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 20).getFxImage();
                }
                break;
            case 2:
                img = Sprite.oneal_right1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 20).getFxImage();
                }
                break;
            case 3:
                img = Sprite.oneal_right1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 20).getFxImage();
                }
                break;
            default:
                img = Sprite.oneal_left1.getFxImage();
                if (moving) {
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 20).getFxImage();
                }
                break;
        }
    }

}
