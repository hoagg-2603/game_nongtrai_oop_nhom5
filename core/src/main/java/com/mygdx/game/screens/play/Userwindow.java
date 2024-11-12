package com.mygdx.game.screens.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.game.screens.PlayScreen;


public class Userwindow extends Window {
    //Cửa sổ thông tin người dùng

    private Label username;//Tên người dùng
    private Image icon;//Ảnh đại diện
    private Label userID;//Tài khoản
    private Label level;//Cấp độ
    private Label power;//Thể lực
    private Label exp;//Điểm kinh nghiệm
    private Label money;//Vàng
    public TextButton change;//Chỉnh sửa thông tin
    public TextButton exit;//Đăng xuất tài khoản
    private ImageButton win1;//Nút đóng
    private Table tableicon;
    private Table tablemoney;
    private ImageButton X;
    private PlayScreen playScreen;

    public Userwindow(String title, Skin skin, PlayScreen playScreen) {
        super(title, skin,"window1");
        this.playScreen=playScreen;
        this.setModal(true);
        this.setVisible(true);
        this.setSize(900,700);
        this.setPosition(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5);

        userID=new Label("Tài khoản:"+playScreen.getUser().getUserID(),skin);
        username=new Label("Tên người dùng:"+playScreen.getUser().getUserName(),skin);
        level=new Label("Cấp độ:"+playScreen.getUser().getLevel(),skin);
        power=new Label("Thể lực:"+playScreen.getUser().getHp(),skin);
        exp=new Label("Kinh nghiệm:"+playScreen.getUser().getExp(),skin);
        money=new Label("Vàng:"+playScreen.getUser().getMoney(),skin);
        change=new TextButton("Chỉnh sửa thông tin",skin);
        exit=new TextButton("Đăng xuất",skin);
        win1=new ImageButton(skin,"win1");
        X=new ImageButton(skin,"win1");
        this.addActor(X);
        X.setPosition(800,600);
        icon=new Image(new Texture(Gdx.files.internal(playScreen.getUser().getPic())));
        icon.setSize(70,70);
        icon.setPosition(0,0);
        tableicon=new Table();
        tablemoney=new Table();


        this.add(tableicon);
        this.add(tablemoney);

        tableicon.setVisible(true);
        tableicon.setPosition(600,200);
        tableicon.setSize(400,500);
        tableicon.add(icon).padRight(150).padBottom(40);
        tableicon.row();
        tableicon.add(userID).padRight(150).padBottom(50);
        tableicon.row();
        tableicon.add(username).padRight(150).padBottom(50);
        tableicon.row();
        tableicon.add(change).padRight(150);

        tablemoney.setVisible(true);
        tablemoney.setPosition(100,200);
        tablemoney.setSize(400,500);
        tablemoney.add(level).padBottom(50).padTop(10);
        tablemoney.row();
        tablemoney.add(money).padBottom(50);
        tablemoney.row();
        tablemoney.add(exp).padBottom(50);
        tablemoney.row();
        tablemoney.add(power).padBottom(50);
        tablemoney.row();
        tablemoney.add(exit);
    }
    public TextButton Getexit(){
        return exit;
    }
    public TextButton Getchange(){
        return change;
    }
    public ImageButton GetX(){
        return X;
    }
    public void update() {
   	 icon=new Image(new Texture(Gdx.files.internal(playScreen.getUser().getPic())));
   	 icon.setSize(70, 70);
   	 icon.setPosition(600, 200);
   	 tableicon.clearChildren();
   	 tableicon.add(icon).padRight(150).padBottom(40);
        tableicon.row();
        tableicon.add(userID).padRight(150).padBottom(50);
        tableicon.row();
        tableicon.add(username).padRight(150).padBottom(50);
        tableicon.row();
        tableicon.add(change).padRight(150);


   }
   @Override
   public void act(float delta) {
   	super.act(delta);
   	power.setText("Thể lực:"+playScreen.getUser().getHp());
   	money.setText("Vàng:"+playScreen.getUser().getMoney());
   	level.setText("Cấp độ:"+playScreen.getUser().getLevel());
   	exp.setText("Kinh nghiệm:"+playScreen.getUser().getExp());
   	username.setText("Tên người dùng:"+playScreen.getUser().getUserName());

	}


}
