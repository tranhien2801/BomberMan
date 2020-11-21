package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.AnimatedEntity;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.game_sound;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntity {

    protected int timeToExplode = 250;
    public int timeAfter = 40; //thoi gian de vu no bien mat

    protected DirectionalExplosion[] explosions = null;
    //protected Brick [] bricks;
    protected boolean exploded = false;

    public game_sound bomb_explosion = new game_sound();

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        sprite = Sprite.bomb;
    }


    @Override
    public void update() {
        if(!exploded) {
            if(timeToExplode > 0) {
                img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1,
                        Sprite.bomb_2, animate, 60).getFxImage();
                timeToExplode--;
            } else {
                explosion();
            }
        } else {
            if(timeAfter > 0) {
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2, animate, 35).getFxImage();
                updateExplosions();
                updateBrick();
                timeAfter--;
            } else {
                BombermanGame.bombs.remove(this);
                removeBricksDestroy();
                removeExplosions();
            }
        }
        animate();

    }



    public void explosion() {
        exploded = true;
        explosions = new DirectionalExplosion[4];
        for(int i = 0; i < explosions.length; i++) {
            explosions[i] = new DirectionalExplosion((int)this.x, (int)this.y, i, BombermanGame.bombRadius);
        }
        bomb_explosion.sound_effect("sound/bomb_explosion_1.wav", 1, false);
    }

    public void updateExplosions() {
        for(int i = 0; i < explosions.length; i++) {
            for(int j = 0; j < explosions[i].explosions.length; j++) {
                int xa = (int) explosions[i].explosions[j].getX();
                int ya = (int) explosions[i].explosions[j].getY();
                if(BombermanGame.getEntity(xa, ya) instanceof Brick) {
                    explosions[i].explosions[j].setImg(null);
                } else {
                    explosions[i].explosions[j].animate = animate;
                    explosions[i].explosions[j].update();
                }
            }
        }
        //game_sound.sound_effect("sound/bomb_explosion.mp3", 0.2, false);
    }

    protected void removeExplosions() {
        for(int i = 0; i < explosions.length; i++) {
            for(int j = 0; j < explosions[i].explosions.length; j++) {
                BombermanGame.explosions.remove(explosions[i].explosions[j]);
            }
        }
    }

    protected void updateBrick() {
        for(int i = 0; i < BombermanGame.bricks.size(); i++) {
            int xa = (int)BombermanGame.bricks.get(i).getX();
            int ya = (int)BombermanGame.bricks.get(i).getY();
            if(BombermanGame.isExplosion(xa, ya)) {
                BombermanGame.bricks.get(i).animate = animate;
                BombermanGame.bricks.get(i).update();
            }
        }
    }

    protected void removeBricksDestroy() {
        for(int i = 0; i < BombermanGame.bricks.size(); i++) {
            int xa = (int)BombermanGame.bricks.get(i).getX();
            int ya = (int)BombermanGame.bricks.get(i).getY();
            if(BombermanGame.isExplosion(xa, ya)) {
                BombermanGame.bricks.remove(BombermanGame.bricks.get(i));
                 i--;
            }
        }
    }

}
