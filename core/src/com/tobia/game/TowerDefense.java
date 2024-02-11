package com.tobia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tobia.game.entities.Textures;
import com.tobia.game.states.GameStateManager;
import com.tobia.game.states.MenuState;

public class TowerDefense extends ApplicationAdapter {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;

	public static final String TITLE = "AntsTD5";

	public static BitmapFont font;




	private GameStateManager gameStateManager;

	private SpriteBatch batch;
	
	@Override
	public void create () {
		font = new BitmapFont();
		font.setColor(Color.WHITE);



		batch = new SpriteBatch();
		gameStateManager = GameStateManager.getInstance();
		Gdx.gl.glClearColor(1,0,0,1);
		gameStateManager.push(new MenuState());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
