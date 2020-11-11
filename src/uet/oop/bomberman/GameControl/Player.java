package uet.oop.bomberman.GameControl;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Mob.Enemy.Balloom;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.PowerUp;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;



public class Player extends Mob {


    public boolean UpPressed;
    public boolean DownPressed;
    public boolean LeftPressed;
    public boolean RightPressed;
    public boolean putBomb;


    public Player() {
        super();
    }

    public Player(int x, int y, Image img) {
        super(x, y, img);
    }


    @Override
    public void update() {
        if(!checkLive())   kill();
        if(!alive) {
            afterKill();
            return;
        }
        if(catchPowerUp()) {
            removePowerUp();
        }
        chooseSprite();
        animate();
        calculateMove();
    }


    /*
	|--------------------------------------------------------------------------
	| Move
	|--------------------------------------------------------------------------
	 */


    @Override
    public void calculateMove() {
        int xa = 0, ya = 0;
        if(UpPressed)   ya--;
        if(DownPressed) ya++;
        if(LeftPressed) xa--;
        if(RightPressed) xa++;
        detectPutBomb();
        if(xa != 0 || ya != 0) {
            move(xa * speed, ya * speed);
            moving = true;
        } else {
            moving = false;
        }
    }

    @Override
    protected boolean canMove(double x, double y) {
        if(x != 0 && y != 0) return false;
       double xa = x + this.x;
       double ya = y + this.y;

       int x1 = (int) (xa + 0.1);
       int x2 = (int) (xa + 0.7);
       int y1 = (int) (ya + 0.15);
       int y2 = (int) (ya + 0.9);
       Entity entity1 = BombermanGame.getEntity(x1, y1);
       Entity entity2 = BombermanGame.getEntity(x1, y2);
       Entity entity3 = BombermanGame.getEntity(x2, y1);
       Entity entity4 = BombermanGame.getEntity(x2, y2);
        if(entity1 instanceof Wall || entity1 instanceof Brick
        || entity2 instanceof Wall || entity2 instanceof Brick
        || entity3 instanceof Wall || entity3 instanceof Brick
        || entity4 instanceof Wall || entity4 instanceof Brick)
           return false;

        return true;
    }

    @Override
    protected void move(double xa, double ya) {
        if(ya < 0) direction = 0;
        if(ya > 0)  direction = 1;
        if(xa < 0)  direction = 2;
        if(xa > 0)  direction = 3;
        if(canMove(0, ya)) {
            this.y += ya;
        }
        if(canMove(xa, 0)) {
            this.x += xa;
        }
    }

    /*
	|--------------------------------------------------------------------------
	| Bomb
	|--------------------------------------------------------------------------
	 */

    private void detectPutBomb() {
        if(putBomb && BombermanGame.bombs.size() < BombermanGame.bombRate
                && canPutBomb((int)(this.x + 0.5), (int)(this.y + 0.5))) {
            putBomb((int)(this.x + 0.5), (int)(this.y + 0.5));
        }
    }

    protected void putBomb(int x, int y) {
        Bomb bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
        BombermanGame.bombs.add(bomb);
    }

    protected boolean canPutBomb(int x, int y) {
        if(BombermanGame.getEntity(x, y) instanceof Grass) {
            for(int i = 0; i < BombermanGame.bombs.size(); i++) {
                if(BombermanGame.bombs.get(i).getX() == x && BombermanGame.bombs.get(i).getY() == y) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    protected boolean catchPowerUp() {

        Entity entity = BombermanGame.getEntity((int)(x + 0.3), (int)(y + 0.3));
        if(entity instanceof PowerUp && ((PowerUp) entity).getMs() == "s") {
            this.speed = 0.1;
            return true;
        }
        if(entity instanceof PowerUp && ((PowerUp) entity).getMs() == "b") {
            BombermanGame.bombRate = 5;
            return true;
        }
        if(entity instanceof PowerUp && ((PowerUp) entity).getMs() == "f") {
            BombermanGame.bombRadius = 2;
            return true;
        }
        if(entity instanceof PowerUp && ((PowerUp) entity).getMs() == "x" && BombermanGame.enemies.size() == 0) {
            return true;
        }
        return false;
    }

    public void removePowerUp() {
        for(int i = 0; i < BombermanGame.powerUps.size(); i++) {
            if(BombermanGame.powerUps.get(i).getX() == (int)(x + 0.3) && BombermanGame.powerUps.get(i).getY() == (int)(y + 0.3)) {
                BombermanGame.powerUps.remove(BombermanGame.powerUps.get(i));
            }
        }
    }

    /*
	|--------------------------------------------------------------------------
	| Kill
	|--------------------------------------------------------------------------
	 */
    @Override
    public void kill() {
        if(!alive)  return;
        alive = false;
        BombermanGame.lives--;
    }

    @Override
    protected void afterKill() {
        if(timeAfter > 0) {
            timeAfter--;
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, 60).getFxImage();
        } else {
            BombermanGame.player = null;
            if(BombermanGame.lives > 0) {
                BombermanGame.player = new Player(1, 1, Sprite.player_down.getFxImage());
            }
        }
        animate();
    }

    @Override
    public boolean checkLive() {
        if(BombermanGame.isExplosion((int) x, (int) y) || BombermanGame.isExplosion((int) x, (int)(y + 1))
        || BombermanGame.isExplosion((int)(x + 1), (int) y) || BombermanGame.isExplosion((int)(x + 1), (int)(y + 1))) {
            return false;
        }
        int x1 = (int) (x + 0.1);
        int x2 = (int) (x + 0.7);
        int y1 = (int) (y + 0.15);
        int y2 = (int) (y + 0.9);
        Entity entity1 = BombermanGame.getEntity(x1, y1);
        Entity entity2 = BombermanGame.getEntity(x1, y2);
        Entity entity3 = BombermanGame.getEntity(x2, y1);
        Entity entity4 = BombermanGame.getEntity(x2, y2);
        if(entity1 instanceof Balloom || entity2 instanceof Balloom
        || entity3 instanceof Balloom || entity4 instanceof Balloom) {
            return false;
        }
        return true;
    }


    /*
	|--------------------------------------------------------------------------
	|
	|--------------------------------------------------------------------------
	 */
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.player_up.getFxImage();
                if(moving) {
                    img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20).getFxImage();
                }
                break;
            case 1:
                img = Sprite.player_down.getFxImage();
                if(moving) {
                    img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20).getFxImage();
                }
                break;
            case 2:
                img = Sprite.player_left.getFxImage();
                if(moving) {
                    img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20).getFxImage();
                }
                break;
            case 3:
                img = Sprite.player_right.getFxImage();
                if(moving) {
                    img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20).getFxImage();
                }
                break;
            default:
                img = Sprite.player_right.getFxImage();
                if(moving) {
                    img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20).getFxImage();
                }
                break;
        }
    }


}
