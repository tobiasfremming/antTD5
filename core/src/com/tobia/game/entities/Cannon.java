package com.tobia.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tobia.game.TowerDefense;

import java.util.List;

public class Cannon extends Defense {
    public Cannon(float x,float y) {
        cost = 50;
        damage = 100;
        loaded = false;
        range = 400;
        position = new Vector3(x,y,0);
        rotation  = 0;
        lastShotTime = System.currentTimeMillis();
        rangeCircle = new Rectangle(position.x- range/2, position.y- range/2, range, range);
        texture = Textures.CANNON_TEXTURE;

        cannonAnimation = new Animation(new TextureRegion(texture), 5, 1.0f);
        hitBox = new Rectangle(x,y,(texture.getWidth()/5), texture.getHeight()/2);

    }

    public void update(float deltaTime){

        if (loaded){
            loaded = cannonAnimation.conditionalUpdate(deltaTime);

        }
    }

    public boolean checkLoaded() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastShotTime >= 2000) {
            lastShotTime = currentTime;
            return true;
        }
        return false;
    }
}
