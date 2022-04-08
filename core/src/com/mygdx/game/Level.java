package com.mygdx.game;

// this unused class is supposed to generate changes to the difficulty and appearance of the program
// as the player progresses through the game. Due to time limitations this class was not fully implemented
// and is left in its current unused state. This class remains due to the possibility of it being
// fully implemented if this project is developed further in the future
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
