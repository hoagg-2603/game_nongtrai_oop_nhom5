package com.mygdx.game.crops;

import java.util.logging.Level;

public class User {
    //Sản phẩm thông tin người chơi

    private String userID;// id
    private String userName;// tên người chơi
    private String pass;// mật khẩu người chơi
    private String pic;// ảnh đại diện
    private double money;// số tiền người chơi đang có 
    private int exp;// kinh nghiệm
    private int maxExp;//kinh nghiệm để tăng cấp
    private int level;// cấp độ của người chơi
    private int hp;//máu
    private boolean faint;//có ngất xỉu hay không?
    private boolean sleep;//state，thức/ngủ
    private float positionX; //vị trí người chơi theo Ox
    private float positionY; // vị trí người chơi theo Oy

    public User() {
        super();
    }

    public User(String userID, String userName, String pass, String pic, double money, int exp, int level, int hp) {
        super();
        this.userID = userID;
        this.userName = userName;
        this.pass = pass;
        this.pic = pic;
        this.money = money;
        this.exp = exp;
        this.level = level;
        this.hp = hp;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public User(String userID, String pass) {
        this.userID = userID;
        this.pass = pass;
        this.exp=0;
        this.level=1;
        this.maxExp=20;
        this.money=200;
        this.pic="";
        this.hp=100;
        this.sleep=false;
        this.faint=false;
        this.positionX=500f;
        this.positionY=612f;
    }

    public void LevelUp(){
        exp=exp-maxExp;
        maxExp=maxExp+20*level;
        level++;
    }
    public boolean isActable(int consumeHp){
        if(consumeHp>hp)
            return false;
        else return true;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getMoney() {
        return money;
    }

    public int getHp() {
        return hp;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isFaint() {
        return faint;
    }

    public void setFaint(boolean faint) {
        this.faint = faint;
    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }
}

