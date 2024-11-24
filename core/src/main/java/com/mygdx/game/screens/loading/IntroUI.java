package com.mygdx.game.screens.loading;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class IntroUI extends Window {

    private Table table;
    private TextButton quitButton, confirmButton; //Xác định nút văn bản，Dùng để đóng cửa sổ sử dụng
    private Label label; //Xác định nhãn，để thêm văn bản


    public IntroUI(String title, Skin skin){
        super(title,skin,"window1");

        table=new Table();
        table.setSize(500,500);
        table.setPosition( Gdx.graphics.getWidth()/2-225, Gdx.graphics.getHeight()/2-225);

        quitButton = new TextButton("Đóng", skin);
        quitButton.setTransform(true);
        quitButton.setScale(0.8f);
        quitButton.setPosition(80,5);

        table.setSize(500,500); 
        table.setPosition( Gdx.graphics.getWidth()/2-225, Gdx.graphics.getHeight()/2-225);

        label=new Label("Cài đặt có thể được sử dụng để tùy chỉnh\nhình đại diện, âm nhạc. Sử dụng các phím\nlên, xuống, trái, phải để di chuyển.\n\nCó thể ngủ hoặc bổ sung thức ăn để khôi\nphục thể lực. Nhân vật di chuyển trên bản\nđồ có thể nói chuyện với NPC. Cây trồng\ncần được tưới nước, nếu không sẽ bị héo.", skin);

        quitButton = new TextButton("Đóng", skin);
        quitButton.setTransform(true);
        quitButton.setScale(0.8f);
        quitButton.setPosition(80,5);

        table.add(label).padLeft(10).padTop(20);
        table.row();
        table.add(quitButton);
        this.add(table).padLeft(10).padTop(20);
        this.setPosition(Gdx.graphics.getWidth()/2-225, Gdx.graphics.getHeight()/2-225);
        this.setSize(500, 500);
        this.setVisible(false);
        this.setMovable(true);
    }
    public TextButton getQuitButton(){
        return quitButton;
    }
}
