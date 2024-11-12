package com.mygdx.game.tools;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.screens.PlayScreen;

public class MapLoader {
    //Lớp tải bản đồ

	private Vector2 playerSpawn;
    private TiledMap map;

    public MapLoader(PlayScreen screen) {
        map = screen.getMap();
        playerSpawn = new Vector2();

        for (MapObject object : map.getLayers().get("Player_Spawn").getObjects()) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            playerSpawn.set(rectangle.x, rectangle.y);
        }
    }
    public Vector2 getPlayerSpawn() {
        return playerSpawn;
    }


}


