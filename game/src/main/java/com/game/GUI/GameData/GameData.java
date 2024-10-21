package com.game.GUI.GameData;

import java.awt.Point;
import java.io.Serializable;
import java.util.Map;

public class GameData implements Serializable{
    private Map<Point, String> spritePath;

    public GameData(Map<Point, String> spritePath){
        this.spritePath = spritePath;
    }

    public Map<Point, String> getSpritePath(){
        return spritePath;
    }
}
