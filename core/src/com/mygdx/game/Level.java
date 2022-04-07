package com.mygdx.game;

public class Level {

    private byte[] tiles;
    public int width;
    public int height;

    public Level(int width, int height) {
        tiles = new byte[width * height];

        this.width = width;
        this.height = height;
        this.generateLevel();
    }

    public void generateLevel() {

        for(int y = 0; y < height; y++){
            for(int x =0; x < width; x++) {
               // tiles[x + y * width] = Tile.Grass.getID();
            }
        }
    }
    //public void renderTiles()
}
