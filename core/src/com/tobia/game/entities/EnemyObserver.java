package com.tobia.game.entities;


/**
 * Interface for observing an enemy
 */
public interface EnemyObserver {
    void antDied(Enemy enemy);
    void antReachedEnd(Enemy enemy);
}
