package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

// the game over screen displays after the player loses all of their lives and it gives
// the user the option to either restart the game or quit the application
public class GameOverScreen implements Screen  {
    final SkelliDungeon game;

    OrthographicCamera camera;

    // intitialize the images used in the game over screen
    Texture GameOverImage;
    Texture quit;

    public GameOverScreen(SkelliDungeon game) {
        this.game = game;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // sets the intialized variables to their respective images in the assets folder
        GameOverImage = new Texture("GameOver.png");
        quit = new Texture("exitGame.png");


    }

    @Override
    public void show() {

    }

    // render class used similarly to implementations in other classes
    @Override
    public void render(float delta) {
        // sets the background to black
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // executes the control method so that the player can choose to either quit or restart the game
        Control();
        game.batch.begin();

        // draws the images on to the screen in their designated x and y positions
        game.batch.draw(GameOverImage, 0, 0);
        game.batch.draw(quit, 212, -10);

        game.batch.end();
    }


    // control method that checks for "esc" and "R" key input, if pressed closes the game or restarts the game respectively
    public void Control () {

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            game.setScreen(new MainMenuScreen(game));
        }


    }

    // overides implemented by the ide and LibGDX
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
