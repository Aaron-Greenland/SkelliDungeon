package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import org.w3c.dom.Text;

public class MainMenuScreen implements Screen {

    final SkelliDungeon game;

    OrthographicCamera camera;

    Texture titleImage;

    public MainMenuScreen(final SkelliDungeon game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        titleImage = new Texture("title.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);



        game.batch.begin();

        game.batch.draw(titleImage, 100, 150);
        //game.font.draw(game.batch, "Welcome to SkelliDungeon", 100, 150);
        game.font.draw(game.batch, "Click anywhere to begin!", 110, 140);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
