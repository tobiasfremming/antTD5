package com.tobia.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tobia.game.TowerDefense;
import com.tobia.game.entities.Textures;

public class GameOverState extends State {

    private Texture screen;

    public GameOverState() {
        super();
        screen = Textures.GAME_OVER;
    }

    @Override
    protected void handleInput() {
        // Change state to menu state when user clicks
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new MenuState());
        }
    }

    @Override
    protected void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(screen, 0,0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

}
