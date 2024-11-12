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

import com.mygdx.game.tools.Check;
import com.mygdx.game.tools.DataManage;
import com.mygdx.game.crops.User;
import com.mygdx.game.FarmGame;
import com.mygdx.game.screens.login.*;


import java.sql.SQLException;

public class LoginScreen implements Screen {
	//Giao diện đăng nhập

	private User user;
	private FarmGame game;
	private Skin skin;

	private Image background;//Hình nền
	private Stage loginStage;

	private RegisterUI registerUI;
	private LoginUI loginUI;

	public LoginScreen(FarmGame game) {
		//Giao diện đăng ký

		this.game = game;
//        game.gamemusic.setMusicStop();//Không phát nhạc trên giao diện đăng ký
		skin=new Skin(Gdx.files.internal("../assets/skin/skin.json"), new TextureAtlas(Gdx.files.internal("../assets/skin/skin.atlas")));
		loginStage = new Stage();

		//Khởi tạo các UI
		background = new Image(new Texture("../assets/image/background/login.png"));
		background.setSize(1600, 900);
		registerUI = new RegisterUI(skin);
		loginUI = new LoginUI(skin);

		//Thêm các UI vào sân khấu
		loginStage.addActor(background);
		loginStage.addActor(loginUI);
		loginStage.addActor(registerUI);
		Gdx.input.setInputProcessor(loginStage);

		//Thêm sự kiện vào nút và xử lý logic
		loginUI.getRegisterButton().addListener(new ClickListener() {
			public void clicked(InputEvent event,
								float x,
								float y) {
				loginUI.setVisible(false);
				registerUI.setVisible(true);
			}
		});
		loginUI.getLoginButton().addListener(new ClickListener(){
            public void clicked(InputEvent event,
                                float x,
                                float y) {
				int status= 0;
				try {
					status = Check.checkLogin(loginUI.getUserIDTextField().getText(),loginUI.getPasswordTextField().getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(status==0){
					new Dialog("",skin,"dialog") {
						protected void result(Object object) {
							if (object.equals(true)) {
								loginUI.getPasswordTextField().setText("");
							}
						}
					}.text("Tài khoản không tồn tại, vui lòng đăng ký trước!").button("Xác nhận", true).show(loginStage);
				}

                else if(status==1)
				{
					new Dialog("",skin,"dialog") {
					protected void result(Object object) {
						if (object.equals(true)) {
							loginUI.getPasswordTextField().setText("");
						}
					}
				}.text("Sai mật khẩu, vui lòng nhập lại!").button("Xác nhận", true).show(loginStage);
				}

                else {
					try {
						user= DataManage.readUserDataFromSQL(loginUI.getUserIDTextField().getText());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					new Dialog("",skin,"dialog") {
						protected void result(Object object) {
							if (object.equals(true)) {
								if(loginUI.getRemember().isChecked())
									DataManage.saveUserMsg(user);
								else
									Gdx.files.local("user/user.json").delete();
								game.setScreen(game.menuScreen=new MenuScreen(game, user));

							}
						}
					}.text("Đăng nhập thành công!").button("Xác nhận", true).show(loginStage);
				}
            }
        }
        );

		registerUI.getCancelButton().addListener(new ClickListener() {
			public void clicked(InputEvent event,
								float x,
								float y) {
				loginUI.setVisible(true);
				registerUI.setVisible(false);
				registerUI.getUserIDTextField().setText("");
				registerUI.getVerifyPassTextField().setText("");
			}
		});
		registerUI.getRegisterButton().addListener(new ClickListener(){
            public void clicked(InputEvent event,
                                float x,
                                float y) {
                if (!Check.checkID(registerUI.getUserIDTextField().getText()))
				{
					new Dialog("",skin,"dialog") {
						protected void result(Object object) {
							if (object.equals(true)) {
							}
						}
					}.text("Tên tài khoản phải có độ dài từ 5-12 ký tự và chỉ bao gồm chữ và số").button("Xác nhận", true).show(loginStage);
				}
				else {
					try {
						if (Check.checkIDExistFromSQL(registerUI.getUserIDTextField().getText()))
						{//Tài khoản đã được đăng ký
							new Dialog("",skin,"dialog") {
								protected void result(Object object) {
									if (object.equals(true)) {
									}
								}
							}.text("Tài khoản đã tồn tại, vui lòng nhập lại!").button("Xác nhận", true).show(loginStage);
						}

						else if (!Check.checkVerifyPass(registerUI.getPasswordTextField().getText(),
								registerUI.getVerifyPassTextField().getText())
						) {//Hai mật khẩu không trùng khớp
							new Dialog("",skin,"dialog") {
								protected void result(Object object) {
									if (object.equals(true)) {
										registerUI.getPasswordTextField().setText("");
										registerUI.getVerifyPassTextField().setText("");
									}
								}
							}.text("Mật khẩu không khớp, vui lòng nhập lại！").button("Xác nhận", true).show(loginStage);
						}
						else if(!Check.checkPassworst(registerUI.getPasswordTextField().getText()))
						{
							new Dialog("",skin,"dialog") {
								protected void result(Object object) {
									if (object.equals(true)) {
										registerUI.getPasswordTextField().setText("");
										registerUI.getVerifyPassTextField().setText("");
									}
								}
							}.text("Mật khẩu quá yếu, vui lòng nhập lại!！").button("Xác nhận", true).show(loginStage);
						}
						else{//Thông tin đăng ký hợp lệ, bắt đầu đăng ký
							user=new User(registerUI.getUserIDTextField().getText(),
									registerUI.getPasswordTextField().getText());
							DataManage.saveUserDataToSQL(user);//Lưu thông tin người dùng
							new Dialog("",skin,"dialog") {
								protected void result(Object object) {
									if (object.equals(true)) {
										registerUI.getUserIDTextField().setText("");
										registerUI.getPasswordTextField().setText("");
										registerUI.getVerifyPassTextField().setText("");
										registerUI.setVisible(false);
										loginUI.setVisible(true);
									}
								}
							}.text("Chúc mừng, bạn đã đăng ký thành công, hãy đăng nhập ngay！").button("Xác nhận", true).show(loginStage);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
            }
        });
		registerUI.getPasswordTextField().setTextFieldListener(new TextField.TextFieldListener() {
			@Override
			public void keyTyped(TextField textField, char c) {
				boolean isnull;
				boolean middle;
				boolean best;
				isnull=Check.checkPass(registerUI.getPasswordTextField().getText());
				middle=Check.checkPassmiddle(registerUI.getPasswordTextField().getText());
				best=Check.checkPassbest(registerUI.getPasswordTextField().getText());
				if(isnull==true){

					registerUI.getPass2().setVisible(false);
					registerUI.getPass3().setVisible(false);
					registerUI.getPass4().setVisible(false);
					registerUI.getPass1().setVisible(true);
				}

				else if(best==true){//Mật khẩu mạnh
					registerUI.getPass3().setVisible(false);
					registerUI.getPass2().setVisible(false);
					registerUI.getPass1().setVisible(false);
					registerUI.getPass4().setVisible(true);
				}
				else if(middle==true){// Mật khẩu trung bình
					registerUI.getPass4().setVisible(false);
					registerUI.getPass2().setVisible(false);
					registerUI.getPass1().setVisible(false);
					registerUI.getPass3().setVisible(true);
				}
				else{//Mật khẩu yếu
					registerUI.getPass3().setVisible(false);
					registerUI.getPass2().setVisible(false);
					registerUI.getPass1().setVisible(false);
					registerUI.getPass2().setVisible(true);
				}
			}
		});


	}


	@Override
	public void show() {

	}

	public void render(float delta) {
		// 黑色清屏

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.end();
		// 更新舞台逻辑
		loginStage.act();
		// 绘制舞台
		loginStage.draw();
	}

	@Override
	public void resize(int i, int i1) {

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

	public void dispose() {
		// 场景被销毁时释放资源



	}

	public Skin getSkin() {
		return skin;
	}
}

