package com.mygdx.game.lwjgl3;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.FarmGame;

/** Launches the desktop (LWJGL3) Lightweight Java Game Library */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("FarmGame"); // Đặt tiêu đề cửa sổ
        config.setWindowedMode(1480, 950); // Thiết lập kích thước cửa sổ
        config.setResizable(false); // Không cho phép thay đổi kích thước cửa sổ

        new Lwjgl3Application(new FarmGame(), config);
        Gdx.app.setLogLevel(Application.LOG_DEBUG); // Đặt mức độ log
<<<<<<< Updated upstream
        //LOG_NONE: Không ghi log.
        //LOG_ERROR: Chỉ ghi lại các lỗi.
        //LOG_INFO: Ghi lại thông tin chung.
        //LOG_DEBUG: Ghi lại thông tin chi tiết để phục vụ việc gỡ lỗi.
=======
>>>>>>> Stashed changes
    }
}
