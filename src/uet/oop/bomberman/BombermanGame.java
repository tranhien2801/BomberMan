package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import uet.oop.bomberman.GameControl.Player;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.Explosion;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.Enemy.Balloom;
import uet.oop.bomberman.entities.Mob.Enemy.Enemy;
import uet.oop.bomberman.entities.Mob.Enemy.Oneal;
import uet.oop.bomberman.entities.PowerUp;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Level.Map;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {

    private static Map map;
    private GraphicsContext gc;
    private GraphicsContext gc1;
    private GraphicsContext gcBomb;
    private Canvas canvas;
    private Canvas canvas1;
    private Canvas canvasBomb;
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> powerUps = new ArrayList<>();
    public static List<Enemy>   enemies = new ArrayList<>();
    public static List<Brick> bricks = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static List<Explosion> explosions = new ArrayList<>();
    private String Level;
    public static Player player = new Player();

    public static int bombRate = 1;
    public static int bombRadius = 1;
    public static int lives = 3;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Level = "res/levels/Level1.txt";

        map = new Map(Level);
        map.insertFromFile(Level);
        createMap();



        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE * map.HEIGHT);
        gc = canvas.getGraphicsContext2D();

        canvas1 = new Canvas(Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE * map.HEIGHT);
        gc1 = canvas1.getGraphicsContext2D();

        canvasBomb = new Canvas(Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE * map.HEIGHT);
        gcBomb = canvasBomb.getGraphicsContext2D();

        stage.setTitle("Bomberman UET");

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(canvasBomb);
        root.getChildren().add(canvas1);
        //music();


        // Tao scene
        Scene scene = new Scene(root);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(player != null) {
                    switch (keyEvent.getCode()) {
                        case UP:
                        case W:
                            player.UpPressed = true;
                            break;
                        case DOWN:
                        case S:
                            player.DownPressed = true;
                            break;
                        case LEFT:
                        case A:
                            player.LeftPressed = true;
                            break;
                        case RIGHT:
                        case D:
                            player.RightPressed = true;
                            break;
                        case SPACE:
                        case E:
                            player.putBomb = true;
                            break;
                    }
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(player != null) {
                    switch (keyEvent.getCode()) {
                        case UP:
                        case W:
                            player.UpPressed = false;
                            break;
                        case DOWN:
                        case S:
                            player.DownPressed = false;
                            break;
                        case LEFT:
                        case A:
                            player.LeftPressed = false;
                            break;
                        case RIGHT:
                        case D:
                            player.RightPressed = false;
                            break;
                        case SPACE:
                        case E:
                            player.putBomb = false;
                            break;
                    }
                }
            }

        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                updateBombs();
                render1();
                update1();

            }
        };
        timer.start();
        render();
    }



    public void createMap() {

        for(int i = 0; i < map.HEIGHT; i++) {
            for(int j = 0; j < map.map.get(i).length(); j++) {
                Entity object;
                if(map.map.get(i).charAt(j) == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }

        for(int i = 0; i < map.HEIGHT; i++) {
            for (int j = 0; j < map.map.get(i).length(); j++) {
                Entity object;
                if(map.map.get(i).charAt(j) == 'p') {
                    player = new Player(j, i, Sprite.player_down.getFxImage());
                }
                if(map.map.get(i).charAt(j) == '1') {
                    Balloom enemy = new Balloom(j, i, Sprite.balloom_left2.getFxImage());
                    enemies.add(enemy);
                }
                if(map.map.get(i).charAt(j) == '2') {
                    //Oneal enemy = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                    //enemies.add(enemy);
                }
                if(map.map.get(i).charAt(j) == 'b') {
                    PowerUp powerUp = new PowerUp(j, i, Sprite.powerup_bombs.getFxImage(), "b");
                    powerUps.add(powerUp);
                }
                if(map.map.get(i).charAt(j) == 'f') {
                    PowerUp powerUp = new PowerUp(j, i, Sprite.powerup_flames.getFxImage(), "f");
                    powerUps.add(powerUp);
                }
                if(map.map.get(i).charAt(j) == 's') {
                    PowerUp powerUp = new PowerUp(j, i, Sprite.powerup_speed.getFxImage(), "s");
                    powerUps.add(powerUp);
                }
                if (map.map.get(i).charAt(j) == 'x') {
                    PowerUp powerUp = new PowerUp(j, i, Sprite.portal.getFxImage(), "x");
                    powerUps.add(powerUp);
                }
                if(map.map.get(i).charAt(j) == '*' || map.map.get(i).charAt(j) == 'b'
                || map.map.get(i).charAt(j) == 'f' || map.map.get(i).charAt(j) == 's'
                || map.map.get(i).charAt(j) == 'x') {
                    Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                    bricks.add(brick);
                }
            }
        }

    }


    /*
	|--------------------------------------------------------------------------
	| Updates
	|--------------------------------------------------------------------------
	 */

    public void update1() {
        if(player != null)  player.update();
        updateBombs();
        updateEnermies();
    }

    protected void updateBombs() {
         for(int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
        }
    }

    protected void updateEnermies() {
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
    }


    /*
	|--------------------------------------------------------------------------
	| Render
	|--------------------------------------------------------------------------
	 */
    public void render() {
        stillObjects.forEach(g -> g.render(gc));
    }

    public void render1() {
        gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
        for(int i = 0; i < powerUps.size(); i++) {
            powerUps.get(i).render(gc1);
        }
        for(int i = 0; i < bombs.size(); i++) {
            bombs.get(i).render(gc1);
        }
        for(int i = 0; i < bricks.size(); i++) {
            bricks.get(i).render(gc1);
        }
        for(int i = 0; i < explosions.size(); i++) {
            explosions.get(i).render(gc1);
        }
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(gc1);
        }
        if(player != null)  player.render(gc1);
    }


    public static Entity getEntity(int x, int y) {
        Entity res = null;
        for(int i = 0; i < enemies.size(); i++) {
            int x1 = (int) (enemies.get(i).getX() + 0.1);
            int x2 = (int) (enemies.get(i).getX() + 0.7);
            int y1 = (int) (enemies.get(i).getY() + 0.15);
            int y2 = (int) (enemies.get(i).getY() + 0.9);
            if((x1 == x && y1 == y) || (x1 == x && y2 == y)
            || (x2 == x && y1 == y) || (x2 == x && y2 == y)) {
                return enemies.get(i);
            }
        }
        for(int i = 0; i < bricks.size(); i++) {
            if(bricks.get(i).getX() == x && bricks.get(i).getY() == y) {
                return bricks.get(i);
            }
        }
        for (int i = 0; i < powerUps.size(); i++) {
            if(powerUps.get(i).getX() == x && powerUps.get(i).getY() == y) {
                return powerUps.get(i);
            }
        }
        for(int i = 0; i < stillObjects.size(); i++) {
            if(stillObjects.get(i).getX() == x && stillObjects.get(i).getY() == y) {
                return stillObjects.get(i);
            }
        }
        return res;

    }



    public static boolean isExplosion(int x, int y) {
        for(int i = 0; i < explosions.size(); i++) {
            if(explosions.get(i).getX() == x && explosions.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }

    /*
	|--------------------------------------------------------------------------
	| Getter & Setter
	|--------------------------------------------------------------------------
	 */
    public void music() {
        String s = "background.mp3";
        Media media = new Media(new File(s).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static int getMapWidth() {
        return map.WIDTH;
    }
}
