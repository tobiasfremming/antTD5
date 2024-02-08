package com.tobia.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tobia.game.TowerDefense;
import com.tobia.game.maps.Map;
import com.tobia.game.states.GameStateManager;
import com.tobia.game.states.PlayState;
import com.tobia.game.states.State;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant extends Enemy{



    public Ant(float x, float y, EnemyObserver observer) {

        texture = Textures.ANT_TEXTURE;
        speed = 50;
        cashForKill = 10;
        attack = 1;
        hitpoints = 200;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        frameCount = 4;

        attachObserver(observer);

        animation = new Animation(new TextureRegion(texture), frameCount, 2.0f);
        hitBox = new Rectangle(x,y,(texture.getWidth()/frameCount), texture.getHeight());

    }






    public void update(float deltaTime, Map map){

        animation.update(deltaTime);
        if (timeOfDeath == 0){
            velocity.set(getVelocity(getNextPos(map)));
            velocity.scl(deltaTime);

        }
        else {
            velocity.set(0,0,0);
            long currentTime = System.currentTimeMillis();
            if(currentTime - timeOfDeath >= 2000){
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
            texture = Textures.DEAD_ANT_TEXTURE;
            animation = new Animation(new TextureRegion(texture), frameCount, 2.0f);
            hitBox = new Rectangle(position.x,position.y,(texture.getWidth()/frameCount), texture.getHeight());
            timeOfDeath = System.currentTimeMillis();
            //notifyObservers();
        }
    }




}
