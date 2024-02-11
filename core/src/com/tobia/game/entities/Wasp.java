package com.tobia.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tobia.game.maps.Map;
import com.tobia.game.states.PlayState;

public class Wasp extends Enemy {

    private boolean isEnraged = false;
    private static final int DEFAULT_SPEED = 60;
    private static final int ENRAGED_SPEED = 120;
    private static final int ENRAGEMENT_DURATION = 3000;
    private int lastAttackTime = 0;

    public Wasp(float x, float y, EnemyObserver observer) {
            
            texture = new Texture("deadBeetle.png");
            speed = DEFAULT_SPEED;
            cashForKill = 30;
            attack = 10;
            hitpoints = 150;
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
        if (isEnraged) {
            speed = ENRAGED_SPEED;
            int currentTime = (int) System.currentTimeMillis();
            if (currentTime - lastAttackTime >= ENRAGEMENT_DURATION) {
                isEnraged = false;
                speed = DEFAULT_SPEED;
            }
        }

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
        isEnraged = true;
        lastAttackTime = (int) System.currentTimeMillis();
        
        hitpoints -= damage;
        if (hitpoints <= 0){
            this.texture = new Texture("deadBeetle.png");
            animation = new Animation(new TextureRegion(texture), frameCount, 2.0f);
            hitBox = new Rectangle(position.x,position.y,(texture.getWidth()/frameCount), texture.getHeight());
            timeOfDeath = System.currentTimeMillis();
        }
    }
}
