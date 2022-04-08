package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

// The purpose of the Help Screen is to teach the player the controls to be used to operate
// the game.
public class HelpScreen implements Screen {
    final SkelliDungeon game;

    OrthographicCamera camera;


    Texture help;

    // the help screen is very simple as it is only needed to show the players the controls to be used
    // the instructions are in the form of an image so that stylized text can be used without the
    // need for additional code
    public HelpScreen(SkelliDungeon game) {
        this.game = game;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Here is the image that loads in the text for the controls
        help = new Texture("controls.png");
    }

    @Override
    public void show() {

    }

    // similar to the render methods used in other classes, this render method sets the background to be
    // black and executes the control() method in its loop as well as draws the help image to be used
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);


        Control();
        game.batch.begin();

        //game.batch.draw(GameOverImage, 0, 0);
        game.batch.draw(help, 0, 0);
        //game.font.draw(game.batch, "Welcome to SkelliDungeon", 100, 150);
        //game.font.draw(game.batch, "Click anywhere to begin!", 110, 140);
        game.batch.end();
    }


    // control method detects if the "H" key is pressed and transitions the screen back to the
    // main menu of the game
    public void Control () {

        if (Gdx.input.isKeyPressed(Input.Keys.H)) {
            game.setScreen(new MainMenuScreen(game));
        }

//        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
//            game.setScreen(new MainMenuScreen(game));
//        }


    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {


    }
}
