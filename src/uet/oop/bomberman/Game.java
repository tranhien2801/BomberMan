package uet.oop.bomberman;

import javafx.application.Application;

public class Game {

    public static BombermanGame bombermanGame;

    public static void main(String[] args) {
        //bombermanGame = new BombermanGame();
        Application.launch(BombermanGame.class);
    }
}
