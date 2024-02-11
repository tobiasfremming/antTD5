package com.tobia.game.states;

import com.tobia.game.Components.TowerType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tobia.game.Components.Button;
import com.tobia.game.Components.ButtonObserver;
import com.tobia.game.Components.PlaceTowerButton;
import com.tobia.game.TowerDefense;
import com.tobia.game.entities.Border;
import com.tobia.game.entities.Cannon;
import com.tobia.game.entities.Defense;
import com.tobia.game.entities.FlameThrower;
import com.tobia.game.entities.TowerFactory;

import java.util.ArrayList;
import java.util.List;

public class PlaceTowerState extends State implements ButtonObserver {

    PlayState playState;

    TowerType towerType;
    List<Rectangle> hitboxList;

    Vector3 mouse;

    private List<Button> buttons;

    private Defense newTower;

    protected PlaceTowerState(TowerType buttonAction) {
        super();

        towerType = buttonAction;
        playState = gameStateManager.getPlayState();
        buttons = new ArrayList<>();
        buttons.add(new PlaceTowerButton(TowerDefense.WIDTH - 300, 10, this, TowerType.CANNON));
        buttons.add(new PlaceTowerButton(TowerDefense.WIDTH - 150, 10, this, TowerType.CANNON));



        mouse = new Vector3(0,0,0);
        newTower = TowerFactory.create(mouse.x, mouse.y, towerType);

        hitboxList = new ArrayList<>();
        List <Border> borders= playState.getMap().getBorders();
        for (Border border: borders){
            hitboxList.add(border.getHitBox());

        }
        List <Defense> towerList = playState.getTowers();
        for (Defense tower: towerList){
            hitboxList.add(tower.getHitbox());
        }







    }

    public Boolean checkAvailableSpot(Defense cannon){
        Rectangle towerHitbox  = cannon.getHitbox();
        for (Rectangle hitbox: hitboxList){
            if (towerHitbox.overlaps(hitbox)){
                return false;
            }
        }
        return true;
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()){
            Defense tower = TowerFactory.create(mouse.x, mouse.y, towerType);
            if (checkAvailableSpot(tower)){
                float cost = tower.getCost();
                if (playState.getMoney() >= cost){
                    playState.pay(cost);
                    playState.addCannons(tower); // må av en eller annen grunn legge til en ny kannon her. Den gamle fungerer ikke riktig.
                    gameStateManager.set(playState);
                }
            }
        }
    }

    @Override
    protected void update(float deltaTime) {

        mouse.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);

        for (Button button: buttons) {
            button.update(mouse);
        }

        newTower.update(mouse.x, mouse.y);

        handleInput();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        playState.render(spriteBatch);

        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();

        spriteBatch.draw(newTower.getTexture(), newTower.getPosition().x, newTower.getPosition().y, newTower.getTextureHitbox().width / 2, newTower.getHitbox().height / 4, newTower.getHitbox().width, newTower.getHitbox().height *2, 1, 1, newTower.getRotation());
        spriteBatch.end();


    }




    @Override
    public void dispose() {
        //backGround.dispose();
        //playBtn.dispose();
        System.out.println("tower state disposed");

    }

    @Override
    public void justClicked(TowerType buttonAction) {
        gameStateManager.set(playState);

    }
}
