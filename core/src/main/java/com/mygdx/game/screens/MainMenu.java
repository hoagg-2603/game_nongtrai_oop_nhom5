package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FarmGame;

public class MainMenu implements Screen{
    private Viewport viewport;//dieu chinh camera hien thi
    private Stage mainStage;//UI
    private FarmGame game;
    private Skin skin;
    private Texture backGround;

    public MainMenu(final FarmGame game){
        this.game = game;
        //thiet lap viewport va cac thanh phan giao dien
        viewport = new FillViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera());
        mainStage = new Stage(viewport,game.batch);
        Gdx.input.setInputProcessor(mainStage);
        backGround = new Texture("back_ground1.jpg");
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        //tao cac nut va tuy chinh phong chu
        final TextButton startGame = new TextButton("New Game",skin);
        final TextButton closeButton = new TextButton("Close",skin);
        startGame.getLabel().setFontScale(1.5f);
        closeButton.getLabel().setFontScale(1.5f);
        //tao font chu cho lable
        Label.LabelStyle  font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        font.font.getData().setScale(2);
        //tao bang va sap xep nut
        final Table table = new Table();
        table.center();
        table.setFillParent(true);
        //cac nut vao bang
        table.add(startGame).padBottom(50f).padTop(100f).width(250).height(75);
        table.row();
        table.add(closeButton).width(250).height(75);
        mainStage.addActor(table);
        //su kien khi nhan new game
        startGame.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x,float y,int pointer,int button){
                game.setScreen(new PlayScreen(game));
                dispose();
                return true;
            }
        });
        //su kien khi nhan exit
        closeButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x,float y,int pointer,int button){
                Gdx.app.exit();
                return true;
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(backGround, 0, 0, 1280, 720);
        game.batch.end();

        mainStage.act(delta);
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backGround.dispose();
        skin.dispose();
        mainStage.dispose();
    }

}
