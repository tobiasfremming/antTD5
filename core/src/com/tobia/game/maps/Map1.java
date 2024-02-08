package com.tobia.game.maps;

import com.badlogic.gdx.graphics.Texture;
import com.tobia.game.TowerDefense;
import com.tobia.game.entities.Border;

import java.util.ArrayList;
import java.util.List;

public class Map1 extends Map {
    private final int ROAD_WIDTH = 10;
    private final int DELTA_ROAD = 10;

    public Map1() {
        background = new Texture("mapR1.png");
        System.out.println("bg:");
        System.out.println(background.getWidth());
        roadBoarders = new ArrayList<>();

        //target = new Border(TowerDefense.WIDTH -1, f(TowerDefense.WIDTH -1), 1, ROAD_WIDTH );
        for (int i = 0; i < TowerDefense.WIDTH -1; i+=DELTA_ROAD){
            roadBoarders.add(new Border(i, f(i), DELTA_ROAD, ROAD_WIDTH ));
        }

        //target = roadBoarders.get(roadBoarders.size()-1);
    }

    @Override
    public float f(float x) {
        return (float) (300*Math.cos(x/85)+400);
    }

    @Override
    public List<Border> getBorders() {
        return roadBoarders;
    }


}
