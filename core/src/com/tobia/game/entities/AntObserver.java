package com.tobia.game.entities;

public interface AntObserver {
    void antDied(Enemy enemy);
    void antReachedEnd(Enemy enemy);
}
