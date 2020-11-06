package uet.oop.bomberman.Level;

import java.io.*;
import java.util.ArrayList;

public class Map {
    private String level;
    public ArrayList<String> map;
    public int WIDTH;
    public int HEIGHT;

    public Map() {
        map = new ArrayList<String>();
    }

    public Map(String level) {
        this.level = level;
        map = new ArrayList<String>();
    }

    public void insertFromFile(String path) {
        try {
            InputStream inputStream = new FileInputStream(new File(path));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String [] p = reader.readLine().split("\\s");
            int l = Integer.parseInt(p[0]);
            this.HEIGHT = Integer.parseInt(p[1]);
            this.WIDTH = Integer.parseInt(p[2]);
            for(int i = 0; i < this.HEIGHT; i++) {
                String row = reader.readLine();
                map.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
