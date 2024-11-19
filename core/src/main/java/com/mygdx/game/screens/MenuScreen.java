package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.crops.User;
import com.mygdx.game.tools.DataManage;
import com.mygdx.game.FarmGame;
import com.mygdx.game.screens.loading.*;
import com.mygdx.game.screens.play.SettingUI;

import java.sql.SQLException;

public class MenuScreen implements Screen {
    //Giao diện Menu

    private FarmGame game;
    private User user;
    private Stage stage;
    private Skin skin;

    private Image background;
    private IntroUI introui;

    private Table table;
    private TextButton startButton,setupButton,helpButton;
    private TextButton quitButton,returnButton;


    private SettingUI settingUI;


    public MenuScreen(FarmGame game,User user) {
        this.game = game;
        this.user=user;
        skin=new Skin(Gdx.files.internal("../assets/skin/skin.json"), new TextureAtlas(Gdx.files.internal("../assets/skin/skin.atlas")));

    }
    @Override
    public void show() {
        stage = new Stage();
        table = new Table();
        background=new Image(new Texture(Gdx.files.internal("../assets/image/background/menubackground.jpg")));
        background.setSize(1600, 900);
        //Thiết lập văn bản và định dạng cho các nút
        returnButton = new TextButton("Chuyển tài khoản",skin);
        startButton = new TextButton("Bắt đầu trò chơi",skin);
        setupButton = new TextButton("Cài đặt trò chơi",skin);
        helpButton = new TextButton("Hướng dẫn trò chơi",skin);
        quitButton = new TextButton("Thoát trò chơi", skin);

        introui=new IntroUI("Hướng dẫn trò chơi",skin);
        settingUI=new SettingUI("Cài đặt âm nhạc",skin,game.gamemusic);
        game.gamemusic.setMusicPlaying();
        game.gamemusic.setMusicVolume(settingUI.getSlider().getValue());
        game.gamemusic.setIsSoundPlaying(true);

        startButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(user.getUserName() != null && !user.getUserName().equals(""))
                    game.setScreen(game.playScreen=new PlayScreen(game, user));
                else {
                    game.setScreen(game.loadingScreen=new LoadingScreen(game, user));
                }

            }
        });

        setupButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                settingUI.setVisible(true);
            }
        });

        helpButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                introui.setVisible(true);
            }
        });

        quitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // Thoát trò chơi
                try {
                    DataManage.saveUserDataToSQL(user);//Lưu thông tin người dùng
                    new Dialog("",skin,"dialog") {
                        protected void result(Object object) {
                            if (object.equals(true)) {

                                System.exit(-1);
                            }

                        }
                    }.text("Xác nhận thoát?").button("Đúng", true).button("Không",false).show(stage);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        introui.getQuitButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                introui.setVisible(false);;
            }
        });


        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Quay lại trang đăng nhập
                game.loginScreen=new LoginScreen(game);
                game.setScreen(game.loginScreen);
            }
        });

        table.setWidth(stage.getWidth());
        table.align(Align.right| Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        table.add(startButton).padTop(80).padRight(250);
        table.row();
        table.add(setupButton).padTop(80).padRight(250);
        table.row();
        table.add(helpButton).padTop(80).padRight(250);
        table.row();
        table.add(quitButton).padTop(80).padRight(250);
        table.row();
        table.add(returnButton).padTop(80).padRight(250);


        stage.addActor(background);
        stage.addActor(table);
        stage.addActor(introui);
        stage.addActor(settingUI);

        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
    @Override
    public void dispose() {

        stage.dispose();
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
}

