package com.tobia.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.tobia.game.maps.Map;
import com.tobia.game.states.PlayState;
import com.tobia.game.states.State;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy {

    protected int hitpoints;
    protected int speed;
    protected int cashForKill;
    protected int attack;
    protected Vector3 velocity;
    protected Rectangle hitBox;
    protected Animation animation;
    protected List<AntObserver> observers = new ArrayList<>();
    protected long timeOfDeath;
    protected Texture texture;
    protected Vector3 position;

    protected int frameCount;

    protected abstract void handleHit(int damage);
    public abstract void update(float deltaTime, Map map);


    public void attachObserver(AntObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Enemy enemy) {
        for (AntObserver observer : observers) {
            observer.antDied(enemy);
        }
    }


    public Vector3 getNextPos(Map map){
        float x = position.x + speed;
        float y = map.f(x);
        return new Vector3(x,y,0);
    }


    public Vector3 getVelocity(Vector3 nextPos){
        return new Vector3(nextPos.x - position.x, nextPos.y - position.y, 0 );
    }


    public float getRotation(){
        float x = velocity.x;
        float y = velocity.y;

        double angleRadians = Math.atan2(y, x);
        float angleDegrees = (float) Math.toDegrees(angleRadians);
        return angleDegrees - 90;

    }

    public void dispose() {

    }

    public Vector3 getPosition(){
        return position;
    }
    public Rectangle getHitbox(){
        return hitBox;
    }
    public int getSpeed(){
        return speed;
    }
    protected Vector3 getVelocity() {
        return velocity;
    }
    public TextureRegion getTexture() {
        return animation.getFrame();
    }
    public int getHitpoints(){
        return hitpoints;
    }
    public long getTimeOfDeath(){
        return timeOfDeath;
    }

    public float getCashForKill(){
        return cashForKill;
    }


}
