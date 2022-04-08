package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

// this class handles the main code for the game
public class GameScreen implements Screen {

    final SkelliDungeon game;

    // initializes assets, camera, camera position, and font to be used
    Sprite playerTex;
    SpriteBatch batch;
    BitmapFont font;
    OrthographicCamera camera;
    Vector3 cameraPos;
    Sprite background;

    //Music SpookyScaryMusic;

    // this array handles the hitboxes for the enemy types - the "bossa" array is supposed to
    // be for a boss enemy type however it was not fully implemented due to time
    Array<Rectangle> skulls;
    Array<Rectangle> bossa;

    // initializes the textures for the boss and normal enemy types
    Texture skullTex;
    Texture bossTex;

    // initializes the hitbox for the player character
    Rectangle player;

    // initializes the values shown next to the player for gameplaty such as the score, lives, and current level
    int score = 0;
    int Health = 3;

    // level is implemented as a string so that the word "MAX" can be used once the
    // enemies reach the maximum difficulty
    String level = "1";

    // intialization of some test code
    //test movement
    //Texture img;
   /* float x;
    float y;*/


    public GameScreen(final SkelliDungeon game) {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        cameraPos = new Vector3();
        background = new Sprite(new Texture("shrek.jpg"));

        //player = new Texture("player.jpg");
        playerTex = new Sprite(new Texture("player.png"));
        batch = new SpriteBatch();
        font = new BitmapFont();

// This code is used for the implementation of backround music, due to an issue with LibGDX not being
// able to properly detect the music files, this code is not currently used
//        SpookyScaryMusic = Gdx.audio.newMusic(Gdx.files.internal("spooky.wav"));
//        SpookyScaryMusic.setLooping(true);
//        SpookyScaryMusic.play();

        skulls = new Array<Rectangle>();
        skullTex = new Texture("skull.png");

        bossa = new Array <Rectangle>();
        bossTex = new Texture("shrek.jpg");


        // executes the enemy method
        spawnSkull();



        float x = 0;
        float y = 0;



        }

    //another method implemented by the LibGDX library
    @Override
    public void show() {

    }

    // an array containing the hitboxes of the enemies
    private Array<Rectangle> Skulls;

    // intialization of the variable that checks for the last time an enemy is spawned
    private long lastSkullTime;

    // code meant for the implementation of the boss enemy type that was not finished
    // due to time
    private Array<Rectangle> Bossa;
    private long lastBossTime;


    // the coe used to generate the enemies
    private void spawnSkull() {
        // create's a new hitbox for each individual enemy
        Rectangle skull = new Rectangle();

        // spawn's the skull on a random x position between 0 and 600 and at a y position set to 600
        skull.x = MathUtils.random(0, 600);
        skull.y = 600;

        // the size of the skull is set to be 64 by 64
        skull.width = 64;
        skull.height = 64;

        // adds the enemy
        skulls.add(skull);
        lastSkullTime = TimeUtils.nanoTime();
    }

    // code meant for the boss implementation
    private void spawnBoss() {
        Rectangle boss = new Rectangle();
        boss.x = MathUtils.random(0, 600);
        boss.y = 600;
        boss.width = 64;
        boss.height = 64;
        bossa.add(boss);
        lastBossTime = TimeUtils.nanoTime();
    }

    // the render loop similar to the other classes
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // creates a new hitbox for the player and sets the player to be 128 by 128 pixels
        player = new Rectangle();
        player.x = cameraPos.x;
        player.y = 0;
        player.width = 128;
        player.height = 128;

        // executes the method that checks for input
        Control();

        // this code is used to move the camera to the position indicated by the cameraPos variable
        // note that the only axis to change during gameplay is the x axis due to the player being
        // limited to moving only in the left and right directions
        camera.position.lerp(cameraPos, 0.1f);
        camera.position.y = 275;
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.enableBlending();
        batch.begin();



        // draw the player character
        batch.draw(playerTex, cameraPos.x, player.y, 128, 128);



        // loop to draw the textures for the enemies
        for(Rectangle skull: skulls) {
            batch.draw(skullTex, skull.x, skull.y);
        }

        // code meant for unifinished boss type
        for(Rectangle boss: bossa) {
            batch.draw(bossTex, boss.x, boss.y);
        }


        // displays the lives, level, and score to the user
        game.font.draw(batch, "Monsters slain: " + score, cameraPos.x - 150, 40);
        game.font.draw(batch, "Lives: " + Health, cameraPos.x - 150, 20);
        game.font.draw(batch, "Level: " + level, cameraPos.x - 150, 60);

        batch.end();

        // the enemies are spawned after this set time since the previous enemy is spawned
        if(TimeUtils.nanoTime() - lastSkullTime > 1000000000) spawnSkull();

        // unused code for the boss
        if(TimeUtils.nanoTime() - lastBossTime > 1000000000) spawnBoss();

        // speedE is the variable used for the speed at which the enemy goes down the screen
        int speedE = 100;
        for (Iterator<Rectangle> iter = skulls.iterator(); iter.hasNext(); ) {
            Rectangle skull = iter.next();
            // moves the enemy down the screen modified by the speedE variable
            skull.y -= speedE * Gdx.graphics.getDeltaTime();

            // these if statements implement the various difficulty stages throughtout the game
            // each if statement adjusts the speed modifiers and the level display, additionally
            // changes the enemy texture to a texture appropriate to indicate each stage of difficulty
            // visually
            if (score > 10 & score <20) {
                skullTex = new Texture("strong_skull.png");
                speedE = 150;
                level = "2";
            }

            if (score > 19 & score < 30) {
                skullTex = new Texture("Insane_skull.png");
                speedE = 200;
                level = "3";
            }

            if (score > 29){
                speedE = 500;
                level = "MAX";
            }


            // this if statement detects if an enemy was able to get by the player,
            // if so the player loses a health point and the enemy is removed from the screen
            if (skull.y < 0) {
                iter.remove();
                Health --;
            }

            // this if statement checks to see if the player's hitbox intercepts the hitbox of an enemy
            // if so it indicates that the player has succesfully blocked an enemy and the enemy is removed from the screen
            if (skull.overlaps(player)){
                iter.remove();
                score ++;
            }

            // if the player's health points reach 0 then the game transitions to the game over screen
            if (Health == 0){
                game.setScreen(new GameOverScreen(game));
                dispose();
            }

            // unused code for the implementation of a boss enemy type
//            for (Iterator<Rectangle> iter2 = bossa.iterator(); iter2.hasNext(); ) {
//                Rectangle boss = iter2.next();
//                boss.y -= 100  * Gdx.graphics.getDeltaTime();
//                if (boss.y < 0) {
//                    iter.remove();
//                    Health --;
//                }
//                if (boss.overlaps(player)){
//                    iter.remove();
//                    score ++;
//                }
//                if (Health == 0){
//                    game.setScreen(new GameOverScreen(game));
//                    dispose();
//                }
//            }
        }


    }

    // the controls for camera were going to be moved fully to this method however
    // they were not do to running out of time, as of right now this method is not used
    private void cameraMovement(){
        cameraPos.x+=100.0f + Gdx.graphics.getDeltaTime();
        cameraPos.y+=100.0f + Gdx.graphics.getDeltaTime();
    }

    @Override
        public void resize ( int width, int height){

        }



        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

    @Override
    public void hide() {

    }

    // detects player movement and executes the appropriate actions
    public void Control () {


            cameraPos.y = 0;

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                cameraPos.x-= 400 * Gdx.graphics.getDeltaTime();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                cameraPos.x+= 400 * Gdx.graphics.getDeltaTime();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                Gdx.app.exit();;
            }

            // constrains the camera to the game screen
            if(cameraPos.x < 0) cameraPos.x = 0;

            if(cameraPos.x > 800) cameraPos.x = 800;
        }

        @Override
        public void dispose () {

        }
    }

