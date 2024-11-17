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
        userIDLabel = new Label("Username:", skin);
        passwordLabel = new Label("Password:", skin);
        passpow=new Label("Password strength:",skin);
        verifyPassLabel = new Label("Confirm password:", skin);


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
        userIDTextField.setMessageText("5 - 12 characters, letters and numbers only");
        passwordTextField.setMessageText("Password must be greater than 5");
        verifyPassTextField.setMessageText("Password must be greater than 5");

        registerButton = new TextButton("Sign up", skin);
        registerButton.setTransform(true);
        cancelButton = new TextButton("Cancel", skin);
        cancelButton.setTransform(true);

        buttonTable = new Table();

        buttonTable.add(registerButton);
        buttonTable.add(cancelButton).padLeft(100).padRight(80);
        buttonTable.setPosition(50, -350);

        userIDLabel.setPosition(-200,118);
        userIDTextField.setPosition(-100,100);
        passwordLabel.setPosition(-200,18);
        passwordTextField.setPosition(-100,0);
        passpow.setPosition(-250,-88);
        pass1.setPosition(-100,-100);
        pass2.setPosition(-100,-100);
        pass3.setPosition(-100,-100);
        pass4.setPosition(-100,-100);
        verifyPassLabel.setPosition(-250,-182);
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

    public TextButton getRegisterButton() { // Hàm lấy nút đăng ký
        return registerButton;
    }

    public TextButton getCancelButton() { // Hàm lấy nút hủy
        return cancelButton;
    }

    public TextField getUserIDTextField() { // Hàm lấy trường văn bản tên người dùng
        return userIDTextField;
    }

    public TextField getPasswordTextField() { // Hàm lấy trường văn bản mật khẩu 
        return passwordTextField;
    }

    public TextField getVerifyPassTextField() { // Hàm lấy trường văn bản xác nhận mật khẩu
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