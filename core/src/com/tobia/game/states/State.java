package com.tobia.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.tobia.game.TowerDefense;

public abstract class State {
    protected GameStateManager gameStateManager;
    protected OrthographicCamera cam;
    protected Vector3 mouse;

    protected State(){
        this.gameStateManager = GameStateManager.getInstance();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, TowerDefense.WIDTH, TowerDefense.HEIGHT);
    }

    protected abstract void handleInput();
    protected abstract void update(float deltaTime);

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void dispose();
}
