package com.mygdx.game.lwjgl3;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.FarmGame;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("FarmGame"); // Đặt tiêu đề cửa sổ
        config.setWindowIcon("../assets/image/icon/1.png");// Đặt biểu tượng cửa sổ
        config.setWindowedMode(1480, 950); // Thiết lập kích thước cửa sổ
        config.setResizable(false); // Không cho phép thay đổi kích thước cửa sổ

        new Lwjgl3Application(new FarmGame(), config);
        Gdx.app.setLogLevel(Application.LOG_DEBUG); // Đặt mức độ log
    }
}
