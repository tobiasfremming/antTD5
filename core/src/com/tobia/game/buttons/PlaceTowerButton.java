package com.tobia.game.buttons;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tobia.game.entities.Textures;
import com.tobia.game.states.GameStateManager;

import java.util.ArrayList;
import java.util.List;

public class PlaceTowerButton extends Button{

    private List<ButtonObserver> observers = new ArrayList<>();


    public PlaceTowerButton(int  x, int y, ButtonObserver observer) {

        texture = Textures.playBtn;
        position = new Vector3(x, y, 0);
        hitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        attachObserver(observer);


    }

    public void attachObserver(ButtonObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ButtonObserver observer : observers) {
            System.out.println(observer);
            System.out.println(observer == GameStateManager.getInstance().peek());
            if (observer == GameStateManager.getInstance().peek())
                observer.justClicked();
        }
    }


    public void update(Vector3 mouse){
        if (isClicked( mouse)) notifyObservers();
    }


}
