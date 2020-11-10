package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import uet.oop.bomberman.GameControl.Player;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.Explosion;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Level.Map;


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
    public static ArrayList<Entity> entities = new ArrayList<Entity>();
    public static ArrayList<Entity> entities1 = new ArrayList<Entity>();
    public static ArrayList<Entity> stillObjects = new ArrayList<Entity>();
    public static ArrayList<Entity> stillObjects1 = new ArrayList<Entity>();
    public static List<Bomb> bombs = new ArrayList<Bomb>();
    public static List<Explosion> explosions = new ArrayList<Explosion>();
    private String Level;
    private Player player = new Player();

    public static int bombRate = 50;
    public static int bombRadius = 1;


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



        // Tao scene
        Scene scene = new Scene(root);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                switch (keyEvent.getCode()) {
                    case UP: case W:
                        player.UpPressed = true;
                        break;
                    case DOWN: case S:
                        player.DownPressed = true;
                        break;
                    case LEFT: case A:
                        player.LeftPressed = true;
                        break;
                    case RIGHT: case D:
                        player.RightPressed = true;
                        break;
                    case SPACE: case E:
                        player.putBomb = true;
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                switch (keyEvent.getCode()) {
                    case UP: case W:
                        player.UpPressed = false;
                        break;
                    case DOWN: case S:
                        player.DownPressed = false;
                        break;
                    case LEFT: case A:
                        player.LeftPressed = false;
                        break;
                    case RIGHT: case D:
                        player.RightPressed = false;
                        break;
                    case SPACE: case E:
                        player.putBomb = false;
                        break;
                }
            }

        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                //update();
                //renderBomb();
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
                } else if(map.map.get(i).charAt(j) == '*') {
                    object = new Brick(j, i, Sprite.brick.getFxImage());
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
                    stillObjects1.add(player);
                }
                if(map.map.get(i).charAt(j) == '1') {

                }
            }
        }

    }


    /*
	|--------------------------------------------------------------------------
	| Updates
	|--------------------------------------------------------------------------
	 */
    public void update() {
        entities.forEach(Entity::update);
    }

    public void update1() {
        player.update();
        updateBombs();
        entities1.forEach(Entity::update);
        System.out.println(stillObjects1.size() + " " + bombs.size() + " " + explosions.size());
    }

    protected void updateBombs() {
         for(int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
        }
    }

    protected void updateExplosions() {
        for(int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
        }
    }


    /*
	|--------------------------------------------------------------------------
	| Render
	|--------------------------------------------------------------------------
	 */
    public void render() {
        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));

    }

    public void render1() {
        gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
        bombs.forEach(g -> g.render(gc1));
        stillObjects1.forEach(g -> g.render(gc1));
        entities1.forEach(g -> g.render(gc1));
        explosions.forEach(g -> g.render(gc1));
    }

    public void renderBomb() {
        gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
        bombs.forEach(g -> g.render(gc1));
    }

    public static Entity getEntity(int x, int y) {
        Entity res = null;
        for(int i = 0; i < stillObjects.size(); i++) {
            if(stillObjects.get(i).getX() == x && stillObjects.get(i).getY() == y) {
                return stillObjects.get(i);
            }
        }
        return res;

    }

    /*
	|--------------------------------------------------------------------------
	| Getter & Setter
	|--------------------------------------------------------------------------
	 */

}
