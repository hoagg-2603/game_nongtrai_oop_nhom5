package com.mygdx.game.screens.login;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.crops.User;
import com.mygdx.game.tools.Check;
import com.mygdx.game.tools.DataManage;

public class LoginUI extends Group {
    //Giao diện đăng nhập

    private Skin skin;

    private Table loginTable;
    private Table buttonTable;

    public TextField getUserIDTextField() {
        return userIDTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    private Label userIDLabel;
    private Label passwordLabel;
    private TextField userIDTextField;
    private TextField passwordTextField;
    private CheckBox remember;
    private User user;


    private TextButton loginButton;
    private TextButton registerButton;

    public LoginUI(Skin skin){
        this.skin=skin;

<<<<<<< Updated upstream
        userIDLabel=new Label("Username:", skin);
        userIDLabel.setAlignment(Align.left);
        passwordLabel=new Label("Password:",skin);
=======
        userIDLabel=new Label("Tài khỏa:", skin);
        userIDLabel.setAlignment(Align.left);
        passwordLabel=new Label("Mật khẩu:",skin);
>>>>>>> Stashed changes
        passwordLabel.setAlignment(Align.left);

        userIDTextField=new TextField("",skin);
        userIDTextField.setAlignment(Align.center);

        passwordTextField=new TextField("",skin);
        passwordTextField.setAlignment(Align.center);
        passwordTextField.setWidth(400);
        passwordTextField.setPasswordMode(true);
        passwordTextField.setPasswordCharacter('*');

<<<<<<< Updated upstream
        remember=new CheckBox("Remember password?",skin);
=======
        remember=new CheckBox("Ghi nhớ mật khẩu",skin);
>>>>>>> Stashed changes

        if(Check.checkLocalUserExist())
        {
            remember.setChecked(true);
            user=DataManage.readUserMsg();
            userIDTextField.setText(user.getUserID());
            passwordTextField.setText(user.getPass());
        }

<<<<<<< Updated upstream
        loginButton=new TextButton("Log in", skin);
        registerButton=new TextButton("Sign up", skin);
=======
        loginButton=new TextButton("Đăng nhập", skin);
        registerButton=new TextButton("Đăng ký", skin);


>>>>>>> Stashed changes


        loginTable=new Table();
        buttonTable=new Table();

        loginTable.add(userIDLabel).padBottom(50).padRight(50).padTop(100);
        loginTable.add(userIDTextField).width(250).padBottom(50).padTop(100);
        loginTable.row();
        loginTable.add(passwordLabel).padBottom(50).padRight(50);
        loginTable.add(passwordTextField).width(250).padBottom(50);
        loginTable.row();
        loginTable.add(remember).colspan(2).padLeft(100).padBottom(80);

        buttonTable.add(loginButton);
        buttonTable.add(registerButton).padLeft(100);

        this.addActor(loginTable);
        loginTable.setPosition(0,0);
        this.addActor(buttonTable);
        buttonTable.setPosition(0,-200);
        this.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);


    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public CheckBox getRemember(){
        return remember;
    }



}
