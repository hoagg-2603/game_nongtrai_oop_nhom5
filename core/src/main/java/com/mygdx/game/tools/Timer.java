package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;

public class Timer {
    //quản lý thời gian

    // Xác định tốc độ dòng thời gian
    public final static float REALTIME = 1;//Thời gian thực 1 giây
    public final static float DEMOTIME = 3600; // Thời gian kiểm tra 1s=3600s
    public final static float GAMETIME = 300; // Thời gian trò chơi 1 giây=600s

    //Thời gian trôi qua kể từ khi cuộc chạy bắt đầu
    private double secondsSinceStart=28800;
    private float realToTimerRatio=Timer.GAMETIME; // Tốc độ dòng thời gian

    private int daysPassed=0;//ngày trôi qua

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    private boolean pause;

    public Timer() {
        super();
    }

    // Bộ hẹn giờ, được đặt trong render()
    public void tick() {
        if(!pause)
        {
            secondsSinceStart += (Gdx.graphics.getDeltaTime() * realToTimerRatio);
            if (secondsSinceStart >= 86400) {
                secondsSinceStart -= 86400;
                daysPassed++;
            }
        }

    }


    // Thời gian đầu ra ở định dạng tiêu chuẩn
    public String getFormattedTimeofDay() {

        int hours = (int) Math.floor(secondsSinceStart / 3600);
        String sub = " ";
        if (hours == 0) {
            hours = 12;
            sub = sub.concat("AM");
        } else if (hours >= 12) {
            if (hours > 12)
                hours -= 12;
            sub = sub.concat("PM");
        } else if ((hours < 12) && (hours > 0)) {
            sub = sub.concat("AM");
        }

        int minutes = (int) Math.floor((secondsSinceStart % 3600) / 60);

        String res = String.format("%1$d:%2$02d", hours, minutes);
        return res.concat(sub);
    }

    public void setDaysPassed(int daysPassed) {
        this.daysPassed = daysPassed;
    }

    public float getTimeRatio() {
        return realToTimerRatio;
    }

    public double getSecondsSinceStart() {
        return secondsSinceStart;
    }

    public void setSecondsSinceStart(double secondsSinceStart) {
        this.secondsSinceStart = secondsSinceStart;
    }


    public float getRealToTimerRatio() {
        return realToTimerRatio;
    }

    public void setRealToTimerRatio(float realToTimerRatio) {
        this.realToTimerRatio = realToTimerRatio;
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    public void setTimeRatio(float ratio) {
        realToTimerRatio = ratio;
    }

    public double getTotalPassedSecond(){
        return daysPassed*86400+secondsSinceStart;
    }
    public int getElapsedInHours() {
        return (int)Math.floor(secondsSinceStart / 3600);
    }

}
