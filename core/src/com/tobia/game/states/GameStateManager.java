package com.tobia.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;
    private static GameStateManager instance = new GameStateManager();

    private GameStateManager(){
        states = new Stack<State>();
    }

    public static GameStateManager getInstance() {
        return instance;

    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);

    }

    public void setOverlapping(State state){
        states.push(state);
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch spriteBatch){
        states.peek().render(spriteBatch);
    }

    public PlayState getPlayState(){
        try {
            return (PlayState) states.peek();

        }catch (Exception e){

        }
        return null;

    }

    public State peek(){
        try {
            System.out.println(states.lastElement());
            return states.lastElement() ;


        }catch (Exception e){
            System.out.println("could not peek: "+ e);
        }
        return null;
    }
}
