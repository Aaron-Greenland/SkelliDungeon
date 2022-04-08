package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import org.w3c.dom.Text;

// This class handles all of the code associated with the Main Menu screen of the game
// The Main menu consists of the title of the game and instructions on how to either
// go to the help page of the game to see the controls on how to play and how to start the game itself
public class MainMenuScreen implements Screen {

    final SkelliDungeon game;

    // initializes the camera view
    OrthographicCamera camera;

    // initializes the title's image
    Texture titleImage;

    boolean helpScreen = false;

    public MainMenuScreen(final SkelliDungeon game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Looks for a file with the name "title.jpg" in the "assets" folder
        titleImage = new Texture("title.jpg");
    }

    @Override
    public void show() {

    }

    // handles key input
    public void Control () {
        // checks if the H key is pressed, if so then the view transitions to the help screen
        if (Gdx.input.isKeyPressed(Input.Keys.H)) {
            game.setScreen(new HelpScreen(game));
            dispose();
        }

    }

    // render is a common operation used throughout most of the classes, it loops through all
    // of the code contained within it and its primary purpose is to render in any assets
    // that are used in the game and execute any operations specified
    @Override
    public void render(float delta) {

        // sets the background to be black
        ScreenUtils.clear(0, 0, 0, 1);

        // updates the camera view
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // executes the check for key input "H"
        Control();

        // begins the batch loading of assets
        game.batch.begin();

        // draws the title image
        game.batch.draw(titleImage, 100, 150);
        //game.font.draw(game.batch, "Welcome to SkelliDungeon", 100, 150);
        // draws the instructions
        game.font.draw(game.batch, "Click anywhere to begin! Press H to see the controls.", 110, 140);
        game.batch.end();

        // This checks to see if the mouse is clicked and then transitions to game screen when
        // input is detected. The reason why this is outside the control method is because
        // this code was implemented very early into the implementation of the project before the
        // control method was created and it functions properly in its current location
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }


    }

    // overrides that were created by the ide due to the implementation of screen by LibGDX

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
