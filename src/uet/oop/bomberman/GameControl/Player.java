package uet.oop.bomberman.GameControl;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Level.Map;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Mob.Enemy.Balloom;
import uet.oop.bomberman.entities.Mob.Enemy.Enemy;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.PowerUp;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.game_sound;
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
            game_sound.sound_effect("sound/player_die.mp3", 1, false);
            afterKill();
            return;
        }
        if(catchPowerUp()) {
            removePowerUp();
        }
        chooseSprite();
        animate();
        calculateMove();
        if(checkPass()) {
            game_sound.sound_effect("sound/level_win.mp3", 1, false);
            System.out.println("Yes");
            removePowerUp();
            passLevel();
        }
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
       Entity entity1 = bombermanGame.getEntity(x1, y1);
       Entity entity2 = bombermanGame.getEntity(x1, y2);
       Entity entity3 = bombermanGame.getEntity(x2, y1);
       Entity entity4 = bombermanGame.getEntity(x2, y2);
        if(entity1 instanceof Wall || entity1 instanceof Brick
        || entity2 instanceof Wall || entity2 instanceof Brick
        || entity3 instanceof Wall || entity3 instanceof Brick
        || entity4 instanceof Wall || entity4 instanceof Brick) {
            game_sound.sound_effect("sound/cant_move.wav", 0.5, false);
            return false;
        }

        int xd1 = (int) (this.x + 0.1);
        int xd2 = (int) (this.x + 0.7);
        int yd1 = (int) (this.y + 0.15);
        int yd2 = (int) (this.y + 0.9);
        Entity e1 = bombermanGame.getEntity(xd1, yd1);
        Entity e2 = bombermanGame.getEntity(xd1, yd2);
        Entity e3 = bombermanGame.getEntity(xd2, yd1);
        Entity e4 = bombermanGame.getEntity(xd2, yd2);

        if((entity1 instanceof Bomb || entity2 instanceof Bomb
        || entity3 instanceof Bomb || entity4 instanceof Bomb)
        && (e1 instanceof Grass && e2 instanceof Grass
        && e3 instanceof Grass && e4 instanceof Grass)) {
            game_sound.sound_effect("sound/cant_move.wav", 0.5, false);
            return false;
        }

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
        if(putBomb && bombermanGame.bombs.size() < bombermanGame.bombRate
                && canPutBomb((int)(this.x + 0.5), (int)(this.y + 0.5))) {
            putBomb((int)(this.x + 0.5), (int)(this.y + 0.5));
        }
    }

    protected void putBomb(int x, int y) {
        game_sound.sound_effect("sound/bomb_put.wav", 1, false);
        Bomb bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
        bombermanGame.bombs.add(bomb);
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

        Entity entity = bombermanGame.getEntity((int)(x + 0.3), (int)(y + 0.3));
        if(entity instanceof PowerUp && ((PowerUp) entity).getMs() == "s") {
            this.speed = 0.1;
            game_sound.sound_effect("sound/power_up.wav", 1, false);
            return true;
        }
        if(entity instanceof PowerUp && ((PowerUp) entity).getMs() == "b") {
            bombermanGame.bombRate = 5;
            game_sound.sound_effect("sound/power_up.wav", 1, false);
            return true;
        }
        if(entity instanceof PowerUp && ((PowerUp) entity).getMs() == "f") {
            bombermanGame.bombRadius = 2;
            game_sound.sound_effect("sound/power_up.wav", 1, false);
            return true;
        }
        return false;
    }

    public void removePowerUp() {
        for(int i = 0; i < bombermanGame.powerUps.size(); i++) {
            if(bombermanGame.powerUps.get(i).getX() == (int)(x + 0.3) && bombermanGame.powerUps.get(i).getY() == (int)(y + 0.3)) {
                bombermanGame.powerUps.remove(bombermanGame.powerUps.get(i));
            }
        }
    }

    public boolean checkPass() {
        Entity entity = bombermanGame.getEntity((int)(x + 0.3), (int)(y + 0.3));
        if(entity instanceof PowerUp && ((PowerUp) entity).getMs() == "x" && bombermanGame.enemies.size() == 0) {

            return true;
        }
        return false;
    }

    public void passLevel() {
        bombermanGame.level++;
        bombermanGame = new BombermanGame(bombermanGame.level, bombermanGame.bombRadius,
                bombermanGame.bombRate, bombermanGame.score, bombermanGame.lives);
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
        if(bombermanGame.lives > 0) {
            bombermanGame.lives--;
        } else {
            bombermanGame = new BombermanGame(bombermanGame.level, bombermanGame.bombRadius,
                    bombermanGame.bombRate, bombermanGame.score, bombermanGame.lives);
        }
    }

    @Override
    protected void afterKill() {
        if(timeAfter > 0) {
            timeAfter--;
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, 60).getFxImage();
        } else {
            bombermanGame.player = null;
            if(bombermanGame.lives > 0) {
                bombermanGame.player = new Player(1, 2, Sprite.player_down.getFxImage());
            }
        }
        animate();

    }

    @Override
    public boolean checkLive() {
        if(bombermanGame.isExplosion((int) x, (int) y) || bombermanGame.isExplosion((int) x, (int)(y + 1))
        || bombermanGame.isExplosion((int)(x + 1), (int) y) || bombermanGame.isExplosion((int)(x + 1), (int)(y + 1))) {
            return false;
        }
        int x1 = (int) (x + 0.1);
        int x2 = (int) (x + 0.7);
        int y1 = (int) (y + 0.15);
        int y2 = (int) (y + 0.9);
        Enemy enemy1 = bombermanGame.getEnemy(x1, y1, 0);
        Enemy enemy11 = bombermanGame.getEnemy(x1, y1, 2);
        Enemy enemy2 = bombermanGame.getEnemy(x1, y2, 2);
        Enemy enemy22 = bombermanGame.getEnemy(x1, y2, 1);
        Enemy enemy3 = bombermanGame.getEnemy(x2, y1, 0);
        Enemy enemy33 = bombermanGame.getEnemy(x2, y1, 3);
        Enemy enemy4 = bombermanGame.getEnemy(x2, y2, 3);
        Enemy enemy44 = bombermanGame.getEnemy(x2, y2, 1);
        if(enemy1 instanceof Enemy || enemy11 instanceof Enemy || enemy2 instanceof Enemy || enemy22 instanceof Enemy
        || enemy3 instanceof Enemy || enemy33 instanceof Enemy || enemy4 instanceof Enemy || enemy44 instanceof Enemy) {
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
