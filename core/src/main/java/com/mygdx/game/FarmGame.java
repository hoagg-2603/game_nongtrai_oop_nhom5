package com.mygdx.game;

import java.sql.SQLException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.screens.LoadingScreen;
import com.mygdx.game.screens.LoginScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.tools.GameMusic;
import com.mygdx.game.tools.SQLConnector;

public class FarmGame extends Game {
    public AssetManager manager;

    public SpriteBatch batch;
    public PlayScreen playScreen;
    public LoginScreen loginScreen;
    public MenuScreen menuScreen;
    public LoadingScreen loadingScreen;
    public GameMusic gamemusic;

    @Override
    public void create () {
        manager=new AssetManager();
        gamemusic=new GameMusic();
        manager.load("../assets/skin/skin.atlas", TextureAtlas.class);
        manager.load("../assets/skin/skin.json", Skin.class, new SkinLoader.SkinParameter("../assets/skin/skin.atlas"));
        manager.finishLoading();
        batch = new SpriteBatch();
        setScreen(loginScreen=new LoginScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {

        if(playScreen!=null)
            playScreen.dispose();
        if(loginScreen!=null)
            loginScreen.dispose();
        if(menuScreen!=null)
            menuScreen.dispose();
        if(loginScreen!=null)
            loginScreen.dispose();
        if(batch!=null)
            batch.dispose();
        if(manager!=null)
            manager.dispose();
        if(SQLConnector.getConn()!=null) {
            try {
                SQLConnector.getStmt().close();
                SQLConnector.getConn().close();
                System.out.println("Kết nối cơ sở dữ liệu đã đóng！");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
