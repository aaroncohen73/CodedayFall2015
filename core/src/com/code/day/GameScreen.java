package com.code.day;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.code.day.level.Barrel;
import com.code.day.level.Girder;
import com.code.day.level.Ladder;
import com.code.day.level.Level;

/**
 * Created by merfoo on 11/7/15.
 */
public class GameScreen implements Screen {

    public static final int GAME_WIDTH = 256;
    public static final int GAME_HEIGHT = 224;

    private Level level;

    public GameScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        level = new Level();
        level.load();
    }

    @Override
    public void show(){

    }

    @Override
    public void hide(){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void resize(int width, int height){

    }

    @Override
    public void render(float delta){
        level.update(delta);


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = new SpriteBatch();
        batch.setTransformMatrix(new Matrix4(new Vector3(), new Quaternion(), new Vector3(2, 2, 2))); //Super janky
        batch.begin();
        level.draw(batch);
        batch.end();
        batch.dispose();
    }

    @Override
    public void dispose(){

    }
}