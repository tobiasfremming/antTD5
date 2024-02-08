package com.tobia.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.concurrent.ThreadPoolExecutor;

public class Border {

    private Rectangle hitBox;
    private Vector3 position;



    public Border(float x, float y, float width, float height) {
        position = new Vector3(x,y,0);
        hitBox = new Rectangle(x,y-height/2,width, height);


    }

    public Rectangle getHitBox(){
        return hitBox;
    }
}
