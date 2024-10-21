package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class FarmGame extends Game {
    private SpriteBatch batch;//doi tuong de ve
    public static final float UNIT_SCALE = 1/32f;// ty le cac doi tuong trong game
    public static AssetManager manager;//quan ly tai nguyen

    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("music.mp3", Music.class);
        manager.load("water.mp3", Sound.class);
        manager.load("dirt.mp3", Sound.class);
        manager.finishLoading();
        setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
