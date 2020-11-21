package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
<<<<<<< HEAD
=======
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
>>>>>>> fa7af81ce39dc9c3ef5c1a59208d41be6e5977dd
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
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


import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {

    public static Map map;
    private GraphicsContext gc;
    private GraphicsContext gc1;
    private Canvas canvas;
    private Canvas canvas1;
<<<<<<< HEAD
    private Canvas canvasBomb;
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> powerUps = new ArrayList<>();
    public static List<Balloom>   enemies = new ArrayList<>();
    public static List<Brick> bricks = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static List<Explosion> explosions = new ArrayList<>();
    private String Level;
    public static Player player = new Player();

    public static int bombRate = 1;
    public static int bombRadius = 1;
    public static int lives = 3;
    public static game_sound background_music = new game_sound();


    public static void main(String[] args) {
        background_music.sound_effect("sound/background_music.wav", 1, true);
        Application.launch(BombermanGame.class);
=======
    //private Image imgStart =

    public static List<Entity> stillObjects;
    public static List<Entity> powerUps;
    public static List<Enemy> enemies;
    public static List<Brick> bricks;
    public static List<Bomb> bombs;
    public static List<Explosion> explosions;
    public static Player player;

    private String path;
    public static int level;
    public static int bombRate;
    public static int bombRadius;
    public static int lives;
    private static int timeGame;
    public static int score;
    public static boolean onGame = false;

    private Text textTime;
    private Text textScore;
    private Text textLives;
    private Text textLevel;
    private Text textEndGame;

    public BombermanGame() {

        map = new Map();
        canvas1 = new Canvas();
        canvas = new Canvas();
        stillObjects = new ArrayList<>();
        powerUps = new ArrayList<>();
        enemies = new ArrayList<>();
        bricks = new ArrayList<>();
        bombs = new ArrayList<>();
        explosions = new ArrayList<>();
        player = new Player();
        textTime = new Text();
        textScore = new Text();
        textLives = new Text();
        textLevel = new Text();
        textEndGame = new Text();
        this.level = 1;
        this.bombRadius = 1;
        this.bombRate = 1;
        this.timeGame = 200;
        this.score = 0;
        this.lives = 3;
    }
    public BombermanGame(int level, int bombRadius, int bombRate, int score, int lives) {
        map = new Map();
        canvas1 = new Canvas();
        canvas = new Canvas();
        stillObjects = new ArrayList<>();
        powerUps = new ArrayList<>();
        enemies = new ArrayList<>();
        bricks = new ArrayList<>();
        bombs = new ArrayList<>();
        explosions = new ArrayList<>();
        player = new Player();
        textTime = new Text();
        textScore = new Text();
        textLives = new Text();
        textLevel = new Text();
        textEndGame = new Text();
        this.bombRadius = bombRadius;
        this.bombRate = bombRate;
        this.level = level;
        this.timeGame = 200;
        this.score = score;
        this.lives = lives;
        changeLevel(level);
        System.out.println(level);
    }
>>>>>>> fa7af81ce39dc9c3ef5c1a59208d41be6e5977dd


    /*
	|--------------------------------------------------------------------------
	| ChangeLevel
	|--------------------------------------------------------------------------
	 */
    public void newGame() {
        resetProperties();
        changeLevel(1);
    }

    public void nextLevel() {
        changeLevel(level + 1);
    }

    private void resetProperties() {
        powerUps.clear();
        bombRate = 1;
        bombRadius = 1;
        if(player != null) {
            player.speed = 0.05;
        }
    }

    public void gameStart() {
        path = "res/levels/Level1.txt";

        map = new Map(path);
        map.insertFromFile(path);
        createMap();

        Font font = Font.font("Consolas", FontWeight.BOLD, 28);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE * (map.HEIGHT + 1));
        gc = canvas.getGraphicsContext2D();
        gc.fillRect(0, 0, Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE * (map.HEIGHT + 1));


    }

    public void changeLevel(int level) {
       // game_sound.sound_effect("level_start.mp3", 1, false);
        enemies.clear();
        bombs.clear();
        bricks.clear();
        stillObjects.clear();
        powerUps.clear();

        path = "res/levels/Level" + level + ".txt";

        map = new Map(path);
        map.insertFromFile(path);
        createMap();

        Font font = Font.font("Consolas", FontWeight.BOLD, 18);

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE * (map.HEIGHT + 1));
        gc = canvas.getGraphicsContext2D();

        canvas1 = new Canvas(Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE * (map.HEIGHT + 1));
        gc1 = canvas1.getGraphicsContext2D();

        gc.fillRect(0, 0, Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE);

        // text time, score, lives
        textTime.setFont(font);
        textTime.setFill(Color.WHITE);
        textTime.setX(50);
        textTime.setY(20);

        textScore.setFont(font);
        textScore.setFill(Color.WHITE);
        textScore.setX(300);
        textScore.setY(20);

        textLives.setFont(font);
        textLives.setFill(Color.WHITE);
        textLives.setX(550);
        textLives.setY(20);

        textLevel.setFont(font);
        textLevel.setFill(Color.WHITE);
        textLevel.setX(800);
        textLevel.setY(20);

        Thread timeCount = new Thread() {
            public void run() {
                for (; timeGame >= 0; timeGame--) {
                    textTime.setText("Time: " + timeGame);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timeCount.start();


    }

    public void endGame() {

        enemies.clear();
        bombs.clear();
        bricks.clear();
        stillObjects.clear();
        powerUps.clear();

        path = "res/levels/Level1.txt";

        map = new Map(path);
        map.insertFromFile(path);
        createMap();

        Font font = Font.font("Consolas", FontWeight.BOLD, 68);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE * (map.HEIGHT + 1));
        gc = canvas.getGraphicsContext2D();
        gc.fillRect(0, 0, Sprite.SCALED_SIZE * map.WIDTH, Sprite.SCALED_SIZE * (map.HEIGHT + 1));

        textEndGame.setFont(font);
        textEndGame.setFill(Color.WHITE);
        textEndGame.setX(300);
        textEndGame.setY(200);

    }

    /*
	|--------------------------------------------------------------------------
	| Detections
	|--------------------------------------------------------------------------
	 */

    @Override
    public void start(Stage stage) throws Exception {

        game_sound.sound_effect("sound/background_music.mp3", 1, true);



        if(!onGame) {
            changeLevel(level);

            // Tao root container
            Group root = new Group();
            root.getChildren().add(canvas);
            root.getChildren().add(canvas1);
            root.getChildren().add(textTime);
            root.getChildren().add(textScore);
            root.getChildren().add(textLives);
            root.getChildren().add(textLevel);

            // Tao scene
            Scene scene = new Scene(root);

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (player != null) {
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
                    if (player != null) {
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

<<<<<<< HEAD
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        
        AnimationTimer timer = new AnimationTimer() {
=======
            AnimationTimer timer = new AnimationTimer() {

                @Override
                public void handle(long l) {
                    updateBombs();
                    render1();
                    update1();
                    textScore.setText("Score: " + score);
                    textLives.setText("Lives: " + lives);
                    textLevel.setText("Level: " + level);
                }
            };
            timer.start();
            render();

            // Them scene vao stage
            stage.setTitle("Bomberman UET");
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Platform.exit();
                    System.exit(0);
                }
            });
>>>>>>> fa7af81ce39dc9c3ef5c1a59208d41be6e5977dd

        } else {
            endGame();

            // Tao root container
            Group root = new Group();
            root.getChildren().add(canvas);
            root.getChildren().add(textEndGame);

            // Tao scene
            Scene scene = new Scene(root);

            // Them scene vao stage
            stage.setTitle("Bomberman UET");
            stage.setScene(scene);
            stage.show();

            AnimationTimer timer = new AnimationTimer() {

                @Override
                public void handle(long l) {
                    textEndGame.setText("Game Over!");
                }
            };
            timer.start();
            //renderBackground();
        }
    }



    public void createMap() {

        for(int i = 0; i < map.HEIGHT; i++) {
            for(int j = 0; j < map.map.get(i).length(); j++) {
                Entity object;
                if(map.map.get(i).charAt(j) == '#') {
                    object = new Wall(j, i + 1, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(j, i + 1, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }

        for(int i = 0; i < map.HEIGHT; i++) {
            for (int j = 0; j < map.map.get(i).length(); j++) {
                Entity object;
                if(map.map.get(i).charAt(j) == 'p') {
                    player = new Player(j, i + 1, Sprite.player_down.getFxImage());
                }
                if(map.map.get(i).charAt(j) == '1') {
                    Enemy enemy = new Balloom(j, i + 1, Sprite.balloom_dead.getFxImage());
                    enemies.add(enemy);
                }
                if(map.map.get(i).charAt(j) == '2') {
                    Enemy enemy = new Oneal(j, i + 1, Sprite.oneal_dead.getFxImage());
                    enemies.add(enemy);
                }
                if(map.map.get(i).charAt(j) == 'b') {
                    PowerUp powerUp = new PowerUp(j, i + 1, Sprite.powerup_bombs.getFxImage(), "b");
                    powerUps.add(powerUp);
                }
                if(map.map.get(i).charAt(j) == 'f') {
                    PowerUp powerUp = new PowerUp(j, i + 1, Sprite.powerup_flames.getFxImage(), "f");
                    powerUps.add(powerUp);
                }
                if(map.map.get(i).charAt(j) == 's') {
                    PowerUp powerUp = new PowerUp(j, i + 1, Sprite.powerup_speed.getFxImage(), "s");
                    powerUps.add(powerUp);
                }
                if (map.map.get(i).charAt(j) == 'x') {
                    PowerUp powerUp = new PowerUp(j, i + 1, Sprite.portal.getFxImage(), "x");
                    powerUps.add(powerUp);
                }
                if(map.map.get(i).charAt(j) == '*' || map.map.get(i).charAt(j) == 'b'
                || map.map.get(i).charAt(j) == 'f' || map.map.get(i).charAt(j) == 's'
                || map.map.get(i).charAt(j) == 'x') {
                    Brick brick = new Brick(j, i + 1, Sprite.brick.getFxImage());
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

    public void renderBackground(Image imgStart) {
        gc.drawImage(imgStart, 0, 0);
    }

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

        for(int i = 0; i < bombs.size(); i++) {
            if(bombs.get(i).getX() == x && bombs.get(i).getY() == y) {
                return bombs.get(i);
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

    public static Enemy getEnemy(int x, int y, int direction) {
        for(int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i).getDirection() == direction && (direction == 1 || direction == 3)) {
                int xa = (int)(enemies.get(i).getX() + 0.7);
                int ya = (int)(enemies.get(i).getY() + 0.9);
                if(xa == x && ya == y)  return enemies.get(i);
            }
            if(enemies.get(i).getDirection() == direction && (direction == 0 || direction == 2)) {
                int xa = (int)(enemies.get(i).getX() + 0.1);
                int ya = (int)(enemies.get(i).getY() + 0.15);
                if(xa == x && ya == y)  return enemies.get(i);
            }
        }
        return null;
    }

    public static boolean isExplosion(int x, int y) {
        for(int i = 0; i < explosions.size(); i++) {
            if(explosions.get(i).getX() == x && explosions.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }



}
