// cửa sổ exit
package com.mygdx.game.screens.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Exitwindow extends Window {
    private Label verify;
    private TextButton yes;
    private TextButton no;

    public Exitwindow(String title, Skin skin) {
        super(title, skin,"dialog");
        this.setModal(true);
        this.setVisible(true);
        this.setSize(400,200);
        this.setPosition(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3);
        verify=new Label("Change account?", skin);
        yes=new TextButton("Yes",skin);
        no=new TextButton("No",skin);
        this.add(verify).colspan(2).padBottom(50);
        this.row();
        this.add(yes).padRight(50);
        this.add(no);
    }
    public TextButton Getyes(){
        return yes;
    }
    public TextButton Getno(){
        return no;
    }

}
