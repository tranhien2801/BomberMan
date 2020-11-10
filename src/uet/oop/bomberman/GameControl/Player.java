package uet.oop.bomberman.GameControl;

import javafx.scene.image.Image;
import uet.oop.bomberman.AnimatedEntity;
import uet.oop.bomberman.Level.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Player extends AnimatedEntity {
    private boolean isAlive = true;
    public boolean moving = true;
    private double speed = 1.0;
    public int direction = 3;
    public Sprite sprite;
    public Map map = new Map();


    public boolean UpPressed;
    public boolean DownPressed;
    public boolean LeftPressed;
    public boolean RightPressed;

    public Player(int x, int y, Image image) {
        super(x, y, image);
    }

    public Player() {
        super();
    }

    @Override
    public void update() {
        if (isAlive) {
            if (UpPressed) {
                direction = 0;

                int i = ((int) y) / Sprite.SCALED_SIZE; // hàng
                int j = ((int) x) / Sprite.SCALED_SIZE; // kí tự
                i = i -1;
                chooseSprite();
                if (checkCanMove(j, i)) {
                    this.y -= this.speed;
                }
            }
            if (DownPressed) {
                direction = 1;
                int i = ((int) y) / Sprite.SCALED_SIZE; // hàng
                int j = ((int) x) / Sprite.SCALED_SIZE; // kí tự
                i = i + 1;
                chooseSprite();
                if (checkCanMove(j, i)) {
                    this.y += this.speed;
                }
            }
            if (LeftPressed) {
                direction = 2;
                int i = ((int) y) / Sprite.SCALED_SIZE; // hàng
                int j = ((int) x) / Sprite.SCALED_SIZE; // kí tự
                j = j - 1;
                chooseSprite();
                if (checkCanMove(j, i)) {
                    this.x -= this.speed;
                }
            }
            if (RightPressed) {
                direction = 3;
                int i = ((int) y) / Sprite.SCALED_SIZE; // hàng
                int j = ((int) x) / Sprite.SCALED_SIZE; // kí tự
                j = j + 1;
                chooseSprite();
                if (checkCanMove(j, i)) {
                    this.x += this.speed;
                }
            }
        } else {
            Sprite.player_dead1.getFxImage();
        }
        animate();
    }
    /*
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    */
    public void calculateMove() {

    }

    public void chooseSprite() {

        switch (direction) {
            case 0:
                sprite = Sprite.player_up;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
            case 1:
                sprite = Sprite.player_down;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
            case 2:
                sprite = Sprite.player_left;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
            case 3:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
            default:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                    img = sprite.getFxImage();
                }
                break;
        }
    }

    public boolean checkCanMove(int x, int y) {
        map.insertFromFile("Level1.txt");
        return y >= 1 && y < map.HEIGHT && x>= 1 && x < map.map.get(y - 1).length() - 1 && map.map.get(y - 1).charAt(x) == ' ';
    }
}
