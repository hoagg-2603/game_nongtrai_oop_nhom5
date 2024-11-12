package com.mygdx.game.screens.login;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;

public class RegisterUI extends Group {

    //Các thành phần giao diện đăng ký

    private Table buttonTable;

    //Nhãn
    private Label passwordLabel;
    private Label verifyPassLabel;
    private Label userIDLabel;
    private Label passpow;

    //hộp văn bản
    private TextField userIDTextField;
    private TextField passwordTextField;// Hộp văn bản (mật khẩu)
    private TextField verifyPassTextField;//Hộp văn bản (xác nhận mật khẩu)

    //nút
    private TextButton registerButton;
    private TextButton cancelButton;

    //Xác định mức độ mạnh của mật khẩu
    private Image pass1;
    private Image pass2;
    private Image pass3;
    private Image pass4;




    public RegisterUI(Skin skin) {

        //Khởi tạolabel
        userIDLabel = new Label("Tài khoản:", skin);
        passwordLabel = new Label("Mật khẩu:", skin);
        passpow=new Label("Độ mạnh mật khẩu:",skin);
        verifyPassLabel = new Label("Xác nhận mật khẩu:", skin);


        //Có 4 trường thông tin：Số tk+Tên người dùng+Mật khẩu+Xác nhận mật khẩu
        userIDTextField = new TextField("", skin);
        userIDTextField.setAlignment(Align.center);
        userIDTextField.setSize(300,50);
        passwordTextField = new TextField("", skin);
        passwordTextField.setAlignment(Align.center);
        passwordTextField.setSize(300,50);
        verifyPassTextField = new TextField("", skin);
        verifyPassTextField.setAlignment(Align.center);
        verifyPassTextField.setSize(300,50);

        pass1=new Image(new Texture(Gdx.files.internal( "../assets/image/other/pass1.png")));
        pass2=new Image(new Texture(Gdx.files.internal( "../assets/image/other/pass2.png")));
        pass3=new Image(new Texture(Gdx.files.internal( "../assets/image/other/pass3.png")));
        pass4=new Image(new Texture(Gdx.files.internal( "../assets/image/other/pass4.png")));

        // Textbox dùng để hiển thị mật khẩu, textbõ cần được đặt ở chế độ mật khẩu
        passwordTextField.setPasswordMode(true);
        verifyPassTextField.setPasswordMode(true);
        // Sử dụng * thay cho kí tự mật khẩu được nhập
        passwordTextField.setPasswordCharacter('*');
        verifyPassTextField.setPasswordCharacter('*');
        userIDTextField.setMessageText("5-12kí tự，bao gồm cả chữ và số");
        passwordTextField.setMessageText("Độ dài mật khẩu phải lớn hơn 5");
        verifyPassTextField.setMessageText("Độ dài mật khẩu phải lớn hơn 5");

        registerButton = new TextButton("Đăng ký", skin);
        registerButton.setTransform(true);
        cancelButton = new TextButton("Hủy bỏ", skin);
        cancelButton.setTransform(true);

        buttonTable = new Table();

        buttonTable.add(registerButton);
        buttonTable.add(cancelButton).padLeft(100).padRight(80);
        buttonTable.setPosition(50, -350);

        userIDLabel.setPosition(-250,100);
        userIDTextField.setPosition(-100,100);
        passwordLabel.setPosition(-250,0);
        passwordTextField.setPosition(-100,0);
        passpow.setPosition(-250,-100);
        pass1.setPosition(-100,-100);
        pass2.setPosition(-100,-100);
        pass3.setPosition(-100,-100);
        pass4.setPosition(-100,-100);
        verifyPassLabel.setPosition(-250,-200);
        verifyPassTextField.setPosition(-100,-200);
        pass2.setVisible(false);
        pass3.setVisible(false);
        pass4.setVisible(false);

        this.addActor(userIDLabel);
        this.addActor(userIDTextField);
        this.addActor(passwordLabel);
        this.addActor(passwordTextField);
        this.addActor(passpow);
        this.addActor(pass1);
        this.addActor(pass2);
        this.addActor(pass3);
        this.addActor(pass4);
        this.addActor(verifyPassLabel);
        this.addActor(verifyPassTextField);
        this.addActor(buttonTable);
        this.setVisible(false);
        this.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getCancelButton() {
        return cancelButton;
    }

    public TextField getUserIDTextField() {
        return userIDTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public TextField getVerifyPassTextField() {
        return verifyPassTextField;
    }
    public  Image getPass1(){
        return pass1;
    }
    public  Image getPass2(){
        return pass2;
    }
    public Image getPass3(){
        return pass3;
    }
    public Image getPass4(){
        return pass4;
    }
}
