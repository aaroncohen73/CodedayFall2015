package com.code.day;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.code.day.input.InputHandler;

public class Game extends ApplicationAdapter {

	private GameScreen gameScreen;
	
	@Override
	public void create () {
		gameScreen = new GameScreen();

		Sfx.addSfx("barrel_fall", Gdx.audio.newSound(Gdx.files.internal("sounds/barrel_fall.wav")));

        Gdx.input.setInputProcessor(new InputHandler());
        Gdx.graphics.getDeltaTime(); //Reset timer
	}

	@Override
	public void render () {
		gameScreen.render(Gdx.graphics.getDeltaTime());
	}
}
