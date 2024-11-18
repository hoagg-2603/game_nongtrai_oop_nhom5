package com.mygdx.game.screens.play;

import java.lang.classfile.Label;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.tools.Timer;
import com.mygdx.game.screens.PlayScreen;

public class StatusUI extends Table {
    private PlayScreen playScreen;
    private Image timebackground;
    private Image userbackground;


    private Label hpLabel;
    private Label expLabel;
    private Label nameLabel;
    private Label levelLabel;
    private Label moneyLabel;
    private Image headPic;
    private Table leftTable;
    private Table downTable;
    private Table buttontable;
    private ImageButton music;
    private ImageButton close;
    private ProgressBar hp;
    private ProgressBar exp;
    private Table headPicContainer;
    public final static float NIGHT_TIME = 19; // 7 giờ tối
    private final static float DARKTODAWNTIME = 1; //Từ đêm đến bình minh
    public final static float DAWN_TIME = 7; // 7 giờ sáng
    private final static float DAWNTOLIGHTTIME = 2.15f; //  Từ bình minh đến ban ngày
    public final static float DAY_TIME = 10.0f; // 12 giờ trưa
    private final static float LIGHTTODUSKTIME = .75f; //Từ ban ngày đến hoàng hôn
    public final static float DUSK_TIME = 18.0f; //6 giờ tối
    private final static float DUSKTODARKTIME = 1; // Từ hoàng hôn đến đêm

    private Color ambient = new Color();

    private final static Color DAWNCOLOR = new Color(0, 0, .75f, .2f);
    private final static Color DARKCOLOR = new Color(0, 0, 0, .6f);
    private final static Color LIGHTCOLOR = new Color(1, 1, 1, 0);
    private final static Color DUSKCOLOR = new Color(1, .3f, .67f, .1f);

    private Label timeLabel;
    private Timer nowTimer;



    public StatusUI(PlayScreen playScreen){
        /*Danh sách việc cần làm: Thêm khung ảnh, thanh máu, thanh kinh nghiệm, thêm sự kiện hiển thị cửa sổ bật lên*/
        this.playScreen=playScreen;
        this.nowTimer = playScreen.getWorldTime();

        userbackground=new Image(new Texture("../assets/image/background/userbackground.png"));
        userbackground.setSize(370,230);
        hpLabel=new Label("Thể lực："+playScreen.getUser().getHp(), playScreen.getSkin());
        expLabel= new Label("Kinh nghiệm："+playScreen.getUser().getExp(), playScreen.getSkin());
        moneyLabel=new Label("Tiền:"+playScreen.getUser().getMoney(),playScreen.getSkin());
        downTable=new Table();
        downTable.add(hpLabel).align(Align.left);
        downTable.row();
        downTable.add(expLabel).align(Align.left);
        downTable.row();
        downTable.add(moneyLabel).align(Align.left);

        nameLabel=new Label("Tên："+playScreen.getUser().getUserName(),playScreen.getSkin());
        levelLabel=new Label("Cấp độ："+playScreen.getUser().getLevel(), playScreen.getSkin());
        leftTable=new Table();
        leftTable.add(nameLabel).align(Align.left);
        leftTable.row();
        leftTable.add(levelLabel).align(Align.left);
        headPic=new Image(new Texture(Gdx.files.internal(playScreen.getUser().getPic())));
        headPic.setSize(70, 70);
        headPicContainer = new Table();
        headPicContainer.addActor(headPic);
        timeLabel=new Label(playScreen.getWorldTime().getFormattedTimeofDay(),playScreen.getSkin());
        timebackground=new Image(new Texture("../assets/image/background/timebackground.png"));
        timebackground.setSize(250,100);


        buttontable=new Table();
        music=new ImageButton(playScreen.getSkin(),"set");
        close=new ImageButton(playScreen.getSkin(),"close");
        buttontable.add(music).padLeft(30);
        buttontable.add(close);

        hp=new ProgressBar(0,100,2,false,playScreen.getSkin(),"red");
        exp=new ProgressBar(0,100,2,false,playScreen.getSkin(),"green");
        hp.setValue(playScreen.getUser().getHp());
        exp.setValue(playScreen.getUser().getExp());
        hp.setSize(200,20);
        exp.setSize(200,20);

        userbackground.setPosition(-25,-30);
        userbackground.toBack();
        hp.setPosition(110,72);
        exp.setPosition(110,34);
        timeLabel.setPosition(770,130);
        timebackground.setPosition(700,100);
        headPicContainer.setPosition(0,100);
        leftTable.setPosition(200,140);
        downTable.setPosition(100,50);
        buttontable.setPosition(1450,140);

        this.addActor(userbackground);
        this.addActor(hp);
        this.addActor(exp);
        this.addActor(headPicContainer);
        this.addActor(leftTable);
        this.addActor(downTable);
        this.addActor(timebackground);
        this.addActor(timeLabel);
        this.addActor(buttontable);

    }
    public Image getImage(){
        return headPic;
    }
    public Table getHeadPicContainer() {
    	return headPicContainer;
    }
    public ImageButton getMusic(){
        return music;
    }
    public ImageButton getExit(){
        return close;
    }
    public void update(){

   	 headPic=new Image(new Texture(Gdx.files.internal(playScreen.getUser().getPic())));
   	 headPic.setSize(70, 70);
   	 headPicContainer.clearChildren();
   	 headPicContainer.addActor(headPic);

   }
    @Override
    public void act(float delta){
        super.act(delta);
        hp.setValue(playScreen.getUser().getHp());
        exp.setRange(0,playScreen.getUser().getMaxExp());
        exp.setValue(playScreen.getUser().getExp());
        timeLabel.setText(playScreen.getWorldTime().getFormattedTimeofDay());

        Color currentcolor = new Color();
        Color lerp = null;
        double amt = 0;
        nowTimer.tick();
        double time = nowTimer.getElapsedInHours();

        if (time >= NIGHT_TIME) {
            currentcolor.set(DARKCOLOR);
        }
        else if (time < DAWN_TIME) {
            // start at dark
            currentcolor.set(DARKCOLOR);
            // if in transition to dawn
            if (time >= (DAWN_TIME - DARKTODAWNTIME)) {
                // lerp to dawn
                lerp = DAWNCOLOR;
                amt = (time - (DAWN_TIME - DARKTODAWNTIME)) / DARKTODAWNTIME;
            }
        } else if
            // between dawn and day
        ((time >= DAWN_TIME) && (time < DAY_TIME)) {
            // start at dawn
            currentcolor.set(DAWNCOLOR);
            // if in transition to day
            if (time >= (DAY_TIME - DAWNTOLIGHTTIME)) {
                // lerp to day
                lerp = LIGHTCOLOR;
                amt = (time - (DAY_TIME - DAWNTOLIGHTTIME)) / DAWNTOLIGHTTIME;
            }
        } else if
            // between day and dusk
        ((time >= DAY_TIME) && (time < DUSK_TIME)) {
            // start at full light
            currentcolor.set(LIGHTCOLOR);
            // if in transition to dusk
            if (time >= (DUSK_TIME - LIGHTTODUSKTIME)) {
                // lerp to dusk
                lerp = DUSKCOLOR;
                amt = (time - (DUSK_TIME - LIGHTTODUSKTIME)) / LIGHTTODUSKTIME;
            }
        } else if
            // between dusk and night
        ((time >= DUSK_TIME) && (time < NIGHT_TIME)) {
            // start at dusk
            currentcolor.set(DUSKCOLOR);
            // if in transition to night
            if (time >= (NIGHT_TIME - DUSKTODARKTIME)) {
                // lerp to full dark
                lerp = DARKCOLOR;
                amt = (time - (NIGHT_TIME - DUSKTODARKTIME)) / DUSKTODARKTIME;
            }
        }

        //here we set the amibients start color
        ambient.set(currentcolor);
        // blend the start color with the lerp color if its set
        if (lerp != null) {
            ambient.lerp(lerp, (float) amt);
        }
        if(playScreen.getUser().getExp()>=playScreen.getUser().getMaxExp())
    		playScreen.getUser().LevelUp();


    	hpLabel.setText("Thể lực:         "+playScreen.getUser().getHp()+"/100");
    	nameLabel.setText("Tên: "+playScreen.getUser().getUserName());
    	expLabel.setText("Kinh nghiệm: "+playScreen.getUser().getExp()+"/"+playScreen.getUser().getMaxExp());
    	levelLabel.setText("Cấp độ:"+playScreen.getUser().getLevel());
    	moneyLabel.setText("Tiền: "+playScreen.getUser().getMoney());
    }
    public Color getAmbientLighting() {
        return ambient;
    }

}





