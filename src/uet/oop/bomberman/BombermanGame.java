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
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Level.Map;


import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private Map map;
    private GraphicsContext gc;
    private GraphicsContext gc1;
    private Canvas canvas;
    private Canvas canvas1;
    private List<Entity> entities = new ArrayList<Entity>();
    private List<Entity> entities1 = new ArrayList<Entity>();
    private List<Entity> stillObjects = new ArrayList<Entity>();
    private List<Entity> stillObjects1 = new ArrayList<Entity>();
    private String Level;
    private Player player = new Player();



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
        stage.setTitle("Bomberman UET");

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(canvas1);


        // Tao scene
        Scene scene = new Scene(root);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("press");

                switch (keyEvent.getCode()) {
                    case UP:
                        player.UpPressed = true;
                        break;
                    case DOWN:
                        player.DownPressed = true;
                        break;
                    case LEFT:
                        player.LeftPressed = true;
                        break;
                    case RIGHT:
                        player.RightPressed = true;
                        break;
                    case SPACE:
                       // player.putBomb = true;
                        break;
                }
                render1();
                update1();
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("release");
                switch (keyEvent.getCode()) {
                    case UP:
                        player.UpPressed = false;
                        break;
                    case DOWN:
                        player.DownPressed = false;
                        break;
                    case LEFT:
                        player.LeftPressed = false;
                        break;
                    case RIGHT:
                        player.RightPressed = false;
                        break;
                    case SPACE:
                        //player.putBomb = false;
                        break;
                }
                render1();
                update1();
            }

        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                //render();
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
                if(map.map.get(i).charAt(j) == '*') {
                    object = new Grass(j, i, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                }

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

    public void update() {
        entities.forEach(Entity::update);
    }

    public void update1() {
        player.update();
        entities1.forEach(Entity::update);
    }

    public void render() {
        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));

    }

    public void render1() {
        gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
        stillObjects1.forEach(g -> g.render(gc1));
        entities1.forEach(g -> g.render(gc1));
    }
}
