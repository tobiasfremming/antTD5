package com.tobia.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

public abstract class Defense {

    protected Texture texture;
    protected static int damage;
    protected int cost;
    protected float rotation;
    protected Rectangle rangeCircle;
    protected long lastShotTime;
    protected Animation cannonAnimation;
    protected boolean loaded;
    protected Vector3 position;
    protected int range;
    protected Rectangle hitBox;

    protected abstract void update(float deltaTime);

    protected abstract boolean checkLoaded();


    public void update(float x, float y){
        position.set(x,y,0);
    }

    public Vector3 setRotation(Vector3 nextPos){
        return new Vector3(nextPos.x - position.x, nextPos.y - position.y, 0 );
    }
    public float getUpdatedRotation(Vector3 rotation){
        float x = rotation.x;
        float y = rotation.y;

        double angleRadians = Math.atan2(y, x);
        float angleDegrees = (float) Math.toDegrees(angleRadians);
        return angleDegrees - 90;

    }



    public void aim(List<Enemy> enemies) {

        for (Enemy enemy : enemies){
            if (enemy.getHitbox().overlaps(rangeCircle) && enemy.getTimeOfDeath() == 0){

                // gir det mest mening å ha rotation som vekor eller float?
                rotation = getUpdatedRotation(setRotation(enemy.position));
                if (checkLoaded()) {
                    shoot(enemy);
                }
                break;

            }

        }
    }


    public void shoot(Enemy enemy){
        loaded = true;
        enemy.handleHit(damage);
    }

    public Vector3 getPosition() {
        return position;
    }

    public int getRange() {
        return range;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public TextureRegion getTexture() {
        return cannonAnimation.getFrame();
    }


    public Rectangle getHitbox() {
        return hitBox;
    }
    public Rectangle getTextureHitbox() {
        return hitBox;
    }

    public float getRotation(){
        return rotation;
    }

    public float getCost(){
        return cost;
    }
}
