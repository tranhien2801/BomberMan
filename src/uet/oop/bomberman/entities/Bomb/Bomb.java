package uet.oop.bomberman.entities.Bomb;

import uet.oop.bomberman.AnimatedEntity;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomb extends AnimatedEntity {

    protected int timeToExplode = 250;
    public int timeAfter = 60; //thoi gian de vu no bien mat

    protected DirectionalExplosion[] explosions = null;
    protected boolean exploded = false;
    protected boolean allowedToPassThru = true;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        sprite = Sprite.bomb;
    }


    public boolean isExploded() {
        return true;
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
                img = Sprite.bomb_exploded2.getFxImage();
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2, animate, 60).getFxImage();
                updateExplosions();
                timeAfter--;
            } else {
                BombermanGame.bombs.remove(this);
                for(int i = 0; i < explosions.length; i++) {
                    for(int j = 0; j < explosions[i].explosions.length; j++) {
                        BombermanGame.explosions.remove(explosions[i].explosions[j]);
                    }
                }
            }
        }
        animate();

    }

    public void explosion() {
        allowedToPassThru = true;
        exploded = true;
        Entity entity = BombermanGame.getEntity((int)this.x, (int)this.y);
        if(entity instanceof Mob) {
            ((Mob) entity).kill();
        }

        explosions = new DirectionalExplosion[4];
        for(int i = 0; i < explosions.length; i++) {
            explosions[i] = new DirectionalExplosion((int)this.x, (int)this.y, i, BombermanGame.bombRadius);
        }
    }

    public void updateExplosions() {
        for(int i = 0; i < explosions.length; i++) {
            for(int j = 0; j < explosions[i].explosions.length; j++) {
                explosions[i].explosions[j].animate = animate;
                explosions[i].explosions[j].update();
            }
        }
    }

    @Override
    public boolean collide(Entity e) {

        return false;
    }
}
