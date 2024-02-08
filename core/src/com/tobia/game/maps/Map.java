package com.tobia.game.maps;
import com.badlogic.gdx.graphics.Texture;
import com.tobia.game.entities.Border;

import java.util.List;


public abstract class Map {

    protected Texture background;

    protected List<Border> roadBoarders;

    public abstract float f(float x);


    public float divide(int a, int b){
        return (float) a / (float) b;
    }

    public Texture getBackground(){
        return background;
    }

    public abstract List<Border> getBorders();




}
