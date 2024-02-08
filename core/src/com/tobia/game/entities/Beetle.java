package com.tobia.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tobia.game.TowerDefense;
import com.tobia.game.maps.Map;
import com.tobia.game.states.PlayState;

public class Beetle extends Enemy{

    public Beetle(float x, float y, PlayState observer) {

        texture = Textures.BEETLE_TEXTURE;
        speed = 40;
        cashForKill = 20;
        attack = 5;
        hitpoints = 400;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        frameCount = 3;

        attachObserver(observer);

        animation = new Animation(new TextureRegion(texture), frameCount, 2.0f);
        hitBox = new Rectangle(x,y,(texture.getWidth()/frameCount), texture.getHeight());

    }
    @Override
    public void update(float deltaTime, Map map){

        animation.update(deltaTime);
        if (timeOfDeath == 0){
            velocity.set(getVelocity(getNextPos(map)));
            velocity.scl(deltaTime);

        }
        else {
            velocity.set(0,0,0);
            long currentTime = System.currentTimeMillis();
            if(currentTime - timeOfDeath >= 2500){
                notifyObservers(this);
            }
        }



        position.add(velocity.x, velocity.y, 0);




        velocity.scl(1/deltaTime);
        hitBox.setPosition(position.x, position.y);

    }
    @Override
    protected void handleHit(int damage) {

        hitpoints -= damage;
        if (hitpoints <= 0){
            texture = Textures.DEAD_BEELTE_TEXTURE;
            animation = new Animation(new TextureRegion(texture), frameCount, 2.0f);
            hitBox = new Rectangle(position.x,position.y,(texture.getWidth()/frameCount), texture.getHeight());
            timeOfDeath = System.currentTimeMillis();
        }

    }



}
