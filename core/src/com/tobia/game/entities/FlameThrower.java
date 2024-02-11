package com.tobia.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class FlameThrower extends Defense{
    private boolean fueled;

    public FlameThrower(float x,float y) {
        fueled = false;
        cost = 0;
        damage = 300;
        loaded = false;
        range = 1000;
        position = new Vector3(x,y,0);
        rotation  = 0;
        lastShotTime = System.currentTimeMillis();
        rangeCircle = new Rectangle(position.x- range/2, position.y- range/2, range, range);
        texture = Textures.CANNON_TEXTURE;
        frameCount = 5;

        animation = new Animation(new TextureRegion(texture), 5, 1.0f);
        hitBox = new Rectangle(x,y,(texture.getWidth()/frameCount), texture.getHeight()/2);

    }
    @Override
    public void update(float deltaTime) {

        if (loaded){
            loaded = animation.conditionalUpdate(deltaTime);

        }

    }

    @Override
    public boolean checkLoaded() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastShotTime >= 3000) {
            lastShotTime = currentTime;
            fueled = !fueled;
            if (fueled)
                return true;
        }
        return fueled;
    }
}
