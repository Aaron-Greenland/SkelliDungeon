package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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

public class GameScreen implements Screen {

    final SkelliDungeon game;

    Sprite playerTex;
    SpriteBatch batch;
    BitmapFont font;
    OrthographicCamera camera;
    Vector3 cameraPos;
    Sprite background;
    Array<Rectangle> skulls;
    Texture skullTex;
    Rectangle player;
    int score = 0;
    int Health = 3;

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
        playerTex = new Sprite(new Texture("player.jpg"));
        batch = new SpriteBatch();
        font = new BitmapFont();

        skulls = new Array<Rectangle>();
        skullTex = new Texture("skull.jpg");



//        player = new Rectangle();
//        player.x = cameraPos.x;
//        player.y = 20;
        spawnSkull();

//        player.width = 128;
//        player.height = 128;

        float x = 0;
        float y = 0;



        }

    @Override
    public void show() {

    }

    private Array<Rectangle> Skulls;
    private long lastSkullTime;

    private void spawnSkull() {
        Rectangle skull = new Rectangle();
        skull.x = MathUtils.random(0, 600);
        skull.y = 600;
        skull.width = 64;
        skull.height = 64;
        skulls.add(skull);
        lastSkullTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        player = new Rectangle();
        player.x = cameraPos.x;
        player.y = 0;
        player.width = 128;
        player.height = 128;

        Control();

        camera.position.lerp(cameraPos, 0.1f);
        camera.position.y = 275;
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();




        batch.draw(playerTex, cameraPos.x, player.y, 128, 128);
        //background.draw(batch);
        //playerTex.setPosition(cameraPos.x, 0);



        for(Rectangle skull: skulls) {
            batch.draw(skullTex, skull.x, skull.y);
        }

        //playerTex.draw(batch);

        game.font.draw(batch, "Monsters slain: " + score, cameraPos.x - 150, 40);
        game.font.draw(batch, "Health: " + Health, cameraPos.x - 150, 20);

        batch.end();

        if(TimeUtils.nanoTime() - lastSkullTime > 1000000000) spawnSkull();



        for (Iterator<Rectangle> iter = skulls.iterator(); iter.hasNext(); ) {
            Rectangle skull = iter.next();
            skull.y -= 100  * Gdx.graphics.getDeltaTime();
            if (skull.y < 0) {
                iter.remove();
                Health --;
            }
            if (skull.overlaps(player)){
                iter.remove();
                score ++;
            }
            if (Health == 0){
                game.setScreen(new GameOverScreen(game));
                dispose();
            }
        }


    }

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

    public void Control () {
//            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//                if(cameraPos.y <0){
//                    cameraPos.y = 0;
//                }
//                if(cameraPos.y > 600) {
//                    cameraPos.y = 600;
//                }
//                else{
//                    cameraPos.y += 25.0f + Gdx.graphics.getDeltaTime();
//                }
//
//            }
//
//            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//                cameraPos.y-=25.0f + Gdx.graphics.getDeltaTime();
//            }

            cameraPos.y = 0;

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                cameraPos.x-= 400 * Gdx.graphics.getDeltaTime();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                cameraPos.x+= 400 * Gdx.graphics.getDeltaTime();
            }

            if(cameraPos.x < 0) cameraPos.x = 0;

            if(cameraPos.x > 800) cameraPos.x = 800;
        }

        @Override
        public void dispose () {
            batch.dispose();
            font.dispose();
        }
    }

