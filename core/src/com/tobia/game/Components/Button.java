package com.tobia.game.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class Button {
    protected Rectangle hitBox;
    protected Vector3 position;


    public Texture texture;

    protected TowerType action;

    protected abstract void notifyObservers();

    protected abstract void attachObserver(ButtonObserver observer);

    public boolean isClicked(Vector3 mouse){
        if (!Gdx.input.justTouched()) return false;
        float mouseX =mouse.x;
        float mouseY = mouse.y;

        return hitBox.contains(mouseX, mouseY);

    }

    public abstract void update(Vector3 mouse);


    public Texture getTexture() {
        return texture;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public Vector3 getPosition() {
        return position;
    }

}
