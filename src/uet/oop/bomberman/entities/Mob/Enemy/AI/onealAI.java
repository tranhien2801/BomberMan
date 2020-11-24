package uet.oop.bomberman.entities.Mob.Enemy.AI;

import uet.oop.bomberman.Level.Map;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Level.Map;
import uet.oop.bomberman.entities.Bomb.Bomb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class onealAI extends AI {

    //public int[][] matrix;
    private int[] stepColumn = {-1, 1, 0, 0};
    private int[] stepRow = {0 , 0, -1, 1};

    public int calculateWay(int x, int y) {
        //Khoi tao: tao array tu Map
        int[][] matrix = new int[BombermanGame.map.HEIGHT][BombermanGame.map.WIDTH];
        int[][] checkMat = new int[BombermanGame.map.HEIGHT][BombermanGame.map.WIDTH];
        for (int i = 0; i < (int) BombermanGame.map.HEIGHT; i++)
            for (int j = 0; j < BombermanGame.map.map.get(i).length(); j++)
                checkMat[i][j] = 0;
        for (int i = 0; i < (int) BombermanGame.map.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.map.map.get(i).length(); j++) {
                char cur = BombermanGame.map.map.get(i).charAt(j);
                if (cur == ' ') matrix[i][j] = 0; else matrix[i][j] = 1;
            }
        }
        int res = 0;
        Queue loan = new LinkedList();
        Point v = new Point(x, y,0);
        loan.add(v);
        checkMat[v.getX()][v.getY()] = -1;
        v.setLoaned(-1);
        while (!loan.isEmpty()) {
            Point p = (Point) loan.poll();
            for (int i = 0; i < 4; i++) {
                int xu = p.getX() + stepRow[i];
                int yu = p.getY() + stepColumn[i];
                if (0 <= xu && xu <= BombermanGame.map.WIDTH && yu >= 0 && yu <= BombermanGame.map.HEIGHT) {
                    if (xu == (int) BombermanGame.player.getX() && yu == (int) BombermanGame.player.getY()) {
                        while (!(xu == x && yu == y)) {
                            res = checkMat[xu][yu];
                            switch (checkMat[xu][yu]) {
                                case 0:
                                    yu++;
                                    break;
                                case 2:
                                    yu--;
                                    break;
                                case 3:
                                    xu++;
                                    break;
                                case 4:
                                    xu--;
                                    break;
                            }
                        }
                        return res;
                    }
                    if (checkMat[xu][yu] == 0) {
                        loan.add(new Point(xu, yu, i));
                        checkMat[xu][yu] = i;
                    }
                } else continue;
            }

        }
        /*
        if (checkMat[(int) BombermanGame.player.getX()][(int) BombermanGame.player.getY()] != 0) {
            return random.nextInt();
        }
         */
        return -1;
    }

    @Override
    public int calculateDirection(int x, int y) {

        int direction = calculateWay(x, y);
        if (direction != -1) {
            return direction;
        }
        return random.nextInt(4);
    }

}
