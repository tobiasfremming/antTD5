package com.tobia.game.entities;

import com.tobia.game.Components.TowerType;

import java.util.Random;

/**
 * The Towerfactory class is responsible for creating instances of towers in the game.
 * It follows the factory pattern, providing a centralized mechanism for tower creation.
 */
public class TowerFactory {
    private static final int AMOUNT_OF_TOWERS = 2;

    /**
     * Creates a tower for the game
     * @param x x-coordinate
     * @param y y-coordinate
     * @param towerType towerType - what tower to make
     * @return Defense
     * @throws IllegalArgumentException
     */
    public static Defense create(float x, float y, TowerType towerType) {
        switch (towerType) {
            case CANNON:
                return new Cannon(x, y);
            case FLAMETHROWER:
                return new FlameThrower(x, y);
        }
        return null;
    }



}
