package main.java.uet.oop.bomberman.GameControl;

import main.java.uet.oop.bomberman.AnimatedEntity;
import main.java.uet.oop.bomberman.entities.Entity;
import javafx.scene.image.Image;
import main.java.uet.oop.bomberman.graphics.Sprite;



public class Player extends AnimatedEntity {
    private boolean isAlive = true;
    public boolean moving = true;
    private double speed = 0.05;
    public int direction = 3;
    public Sprite sprite;

    public boolean UpPressed;
    public boolean DownPressed;
    public boolean LeftPressed;
    public boolean RightPressed;


    public Player(int x, int y, Image img) {
        super(x, y, img);
    }

    public Player() {
        super();
    }

    @Override
    public void update() {
        //keyBoard.update();
        if(isAlive) {
            //keyBoard.update();
            if(UpPressed) {
                direction = 0;
                chooseSprite();
                this.y -= this.speed;
            }
            if(DownPressed) {
                direction = 1;
                chooseSprite();
                this.y += this.speed;
            }
            if(LeftPressed) {
                direction = 2;
                chooseSprite();
                this.x -= this.speed;
            }
            if(RightPressed) {
                direction = 3;
                chooseSprite();
                this.x += this.speed;
            }
        } else {
            Sprite.player_dead1.getFxImage();

        }
        animate();
    }

    public void calculateMove() {

    }

    public void chooseSprite() {
        switch (direction) {
            case 0:
                sprite = Sprite.player_up;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
            case 1:
                sprite = Sprite.player_down;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
            case 2:
                sprite = Sprite.player_left;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
            case 3:
                sprite = Sprite.player_right;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
            default:
                sprite = Sprite.player_right;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
        }
    }
}
