package com.tobia.game.Components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * HealthBar is a component that displays the health of an entity as a colored bar
 */
public class HealthBar implements Disposable {
    private float x;
    private float y;
    private float width;
    private float height;
    private float currentHealth;
    private float maxHealth;
    
    private ShapeRenderer shapeRenderer;

    public HealthBar(float x, float y, float width, float height, float maxHealth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        // Start with full health
        this.currentHealth = maxHealth; 

        this.shapeRenderer = new ShapeRenderer();
    }

    /**
     * Decrease the health of the entity by the given damage amount
     * @param damage The amount of damage to take
     */
    public void takeDamage(float damage) {
        // Ensure health doesn't drop below 0
        currentHealth = Math.max(0, currentHealth - damage); 
    }

    /**
     * Increase the health of the entity by the given heal amount
     * @param healAmount The amount of health to heal
     */
    public void heal(float healAmount) {
        // Ensure health doesn't exceed maxHealth
        currentHealth = Math.min(maxHealth, currentHealth + healAmount); 
    }
    
    private Color getHealthColor() {
        float healthPercentage = currentHealth / maxHealth;
        // Interpolate between colors based on current health percentage
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

        // Calculate width of foreground based on current health
        float healthPercentage = currentHealth / maxHealth;
        float remainingHealthWidth = width * healthPercentage;

        // Get current health color
        Color healthColor = getHealthColor();

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
