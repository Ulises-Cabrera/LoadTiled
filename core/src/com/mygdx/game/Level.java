package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level {

    private TiledMap map;
    private TiledMapRenderer renderer;
    private int[] decorationLayersIndices;
    private Viewport viewport;

    Level(Viewport viewport) {
        this.viewport = viewport;
        createMap();
    }

    private void createMap() {
        AssetManager manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load("Mapa.tmx", TiledMap.class);
        manager.finishLoading();
        map = manager.get("Mapa.tmx", TiledMap.class);

        MapLayers mapLayers = map.getLayers();
        decorationLayersIndices = new int[]{
                mapLayers.getIndex("BackGround")
        };

        renderer = new OrthogonalTiledMapRenderer(map);
    }

    void render() {
        viewport.apply();
        renderer.setView((OrthographicCamera) viewport.getCamera());
        renderer.render(decorationLayersIndices);
    }

    public void dispose() {
        map.dispose();
    }
}
