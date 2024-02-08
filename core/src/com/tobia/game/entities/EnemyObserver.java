package com.tobia.game.entities;

public interface EnemyObserver {
    void antDied(Enemy enemy);
    void antReachedEnd(Enemy enemy);
}
