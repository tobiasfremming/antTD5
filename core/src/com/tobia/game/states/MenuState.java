package com.tobia.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tobia.game.TowerDefense;
import com.tobia.game.entities.Textures;
import com.tobia.game.maps.Map;
import com.tobia.game.maps.Map1;

public class MenuState extends State{

    private Texture backGround;
    private Texture playBtn;
    public MenuState() {
        super();
        backGround = Textures.backGround;
        playBtn = Textures.playBtn;


    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            Map map = new Map1();
            gameStateManager.set(new PlayState(map));
        }

    }

    @Override
    protected void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(backGround, 0,0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
        spriteBatch.draw(playBtn, (TowerDefense.WIDTH/2)-playBtn.getWidth()/2, (TowerDefense.HEIGHT/2));
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        backGround.dispose();
        playBtn.dispose();
        System.out.println("Menu state disposed");

    }
}
