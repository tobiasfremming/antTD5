package com.tobia.game.Components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public class HealthBar implements Disposable {
    private float x;
    private float y;
    private float width;
    private float height;
    private float healthPercentage;
    private static final float MAX_HEALTH_PERCENT = 1.0f;
    
    private ShapeRenderer shapeRenderer;

    public HealthBar(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.healthPercentage = MAX_HEALTH_PERCENT;

        this.shapeRenderer = new ShapeRenderer();
    }

    public void takeDamage(float damage) {
        setHealthPercentage(healthPercentage - damage);
    }

    public void heal(float healAmount) {
        setHealthPercentage(healthPercentage + healAmount);
    }
    
    private void setHealthPercentage(float healthPercentage) {
        this.healthPercentage = Math.max(0, Math.min(healthPercentage, MAX_HEALTH_PERCENT)); // Ensure the value is between 0 and 1
    }



    private Color getHealthColor(float healthPercentage) {
        // Interpolate between colors based on health percentage
        if (healthPercentage > 0.5f) {
            // Green to Yellow
            return Color.YELLOW.cpy().lerp(Color.GREEN, (healthPercentage - 0.5f) * 2);
        } else {
            // Yellow to Red
            return Color.RED.cpy().lerp(Color.YELLOW, healthPercentage * 2);
        }
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.end(); // End SpriteBatch to begin ShapeRenderer drawing

        // Draw background of health bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY); // Background color
        shapeRenderer.rect(x, y, width, height);

        // Calculate width of foreground based on health percentage
        float remainingHealthWidth = width * healthPercentage;

        // Get current health color based on health percentage
        Color healthColor = getHealthColor(healthPercentage);

        // Draw foreground of health bar
        shapeRenderer.setColor(healthColor);
        shapeRenderer.rect(x, y, remainingHealthWidth, height);
        shapeRenderer.end();

        // Begin SpriteBatch again to continue rendering sprites
        spriteBatch.begin(); 

    }

    @Override
    public void dispose() {
        // Dispose of the ShapeRenderer
        shapeRenderer.dispose();
    }
}
