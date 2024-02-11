package com.tobia.game.entities;

import java.util.Random;
/**
 * The EnemyFactory class is responsible for creating instances of enemies in the game.
 * It follows the factory pattern, providing a centralized mechanism for enemy creation.
 */
public class EnemyFactory {
    private static int amountOfEnemies = 3;
    /**
     * Creates an enemy for the game
     * @param x x-coordinate
     * @param y y-coordinate
     * @param observer EnemyObserver
     * @return Enemy 
     * @throws IllegalArgumentException
     */
    public static Enemy create(float x, float y, EnemyObserver observer) throws IllegalArgumentException {
        Random random = new Random();
        int randomNumber = random.nextInt(0, amountOfEnemies);
        switch (randomNumber) {
            case 0:
                return new Ant(x, y, observer);
            case 1:
                return new Wasp(x, y, observer);
            case 2:
                return new Beetle(x, y, observer);
            default:
                throw new IllegalArgumentException("Invalid enemy type");
        }
    }
}
