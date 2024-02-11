package com.tobia.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Cannon extends Defense {
    public Cannon(float x,float y) {
        frameCount = 4;
        cost = 50;
        damage = 100;
        readyToAnimate = false;
        range = 400;
        position = new Vector3(x,y,0);
        rotation  = 0;
        lastShotTime = System.currentTimeMillis();
        rangeCircle = new Rectangle(position.x- range/2, position.y- range/2, range, range);
        texture = Textures.CANNON_TEXTURE;

        animation = new Animation(new TextureRegion(texture), 5, 1.0f);
        hitBox = new Rectangle(x,y,(texture.getWidth()/frameCount), texture.getHeight()/2);

    }

    public void update(float deltaTime){

        if (readyToAnimate){
            readyToAnimate = animation.conditionalUpdate(deltaTime);

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
