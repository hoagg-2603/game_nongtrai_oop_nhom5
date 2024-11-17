package com.mygdx.game.screens.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class IntroUI extends Window {

<<<<<<< Updated upstream
    private Table table; // Sử dụng để bố trí các thành phần UI khác một cách có tổ chức và linh hoạt
                         // trong cửa sổ.
    private TextButton quitButton, confirmButton; // Xác định nút văn bản，Dùng để đóng cửa sổ sử dụng
    private Label label; // Hiển thị văn bản hoặc thông tin cho người dùng trong giao diện.

    public IntroUI(String title, Skin skin) { // Xác định phương thức xây dựng
        super(title, skin, "window1"); // Gọi phương thức xây dựng của lớp cha

        table = new Table(); // Khởi tạo bảng
        table.setSize(500, 500); // Thiết lập kích thước
        table.setPosition(Gdx.graphics.getWidth() / 2 - 225, Gdx.graphics.getHeight() / 2 - 225); // Thiết lập vị trí

        quitButton = new TextButton("Đóng", skin); // Khởi tạo nút văn bản
        quitButton.setTransform(true); // Cho phép biến đổi
        quitButton.setScale(0.8f); // Thiết lập tỷ lệ
        quitButton.setPosition(80, 5); // Thiết lập vị trí

        table.setSize(500, 500);
        table.setPosition(Gdx.graphics.getWidth() / 2 - 225, Gdx.graphics.getHeight() / 2 - 225);

        label = new Label(
                "Cài đặt có thể được sử dụng để tùy chỉnh、hình đại diện、âm nhạc\nSử dụng。\ncác phím lên, xuống, trái, phải\nđể di chuyển，Có thể ngủ hoặc bổ xung thức ăn\nđể khôi phục thể lực。"
                        +
                        "\nNhân vật di chuyển trên bản đồ，có thể nói chuyện với NPC\nCây trồng cần được tưới nươc。\nNếu không，sẽ bị héo úa。\n",
                skin);

        quitButton = new TextButton("Đóng", skin); // Khởi tạo nút văn bản
        quitButton.setTransform(true); // Cho phép biến đổi
        quitButton.setScale(0.8f); // Thiết lập tỷ lệ
        quitButton.setPosition(80, 5); // Thiết lập vị trí

        table.add(label).padLeft(10).padTop(20); // Thêm nhãn vào bảng
        table.row(); // Thêm hàng mới
        table.add(quitButton); // Thêm nút vào bảng
        this.add(table).padLeft(10).padTop(20); // Thêm bảng vào cửa sổ
        this.setPosition(Gdx.graphics.getWidth() / 2 - 225, Gdx.graphics.getHeight() / 2 - 225); // Thiết lập vị trí
        this.setSize(500, 500); // Thiết lập kích thước
        this.setVisible(false); // Thiết lập không hiển thị
        this.setMovable(true); // Cho phép di chuyển
    }

    public TextButton getQuitButton() { // Hàm lấy nút văn bản
        return quitButton; // Trả về nút văn bản
=======
    private Table table;
    private TextButton quitButton,confirmButton; //Xác định nút văn bản，Dùng để đóng cửa sổ sử dụng
    private Label label;           //Xác định nhãn，để thêm văn bản


    public IntroUI(String title, Skin skin){
        super(title,skin,"window1");

        table=new Table();
        table.setSize(500,500);
        table.setPosition( Gdx.graphics.getWidth()/2-225, Gdx.graphics.getHeight()/2-225);

        quitButton = new TextButton("Đóng", skin);
        quitButton.setTransform(true);
        quitButton.setScale(0.8f);
        quitButton.setPosition(80,5);

        table.setSize(500,500);
        table.setPosition( Gdx.graphics.getWidth()/2-225, Gdx.graphics.getHeight()/2-225);

        label=new Label("Cài đặt có thể được sử dụng để tùy chỉnh、hình đại diện、âm nhạc\nSử dụng。\ncác phím lên, xuống, trái, phải\nđể di chuyển，Có thể ngủ hoặc bổ xung thức ăn\nđể khôi phục thể lực。" +
                "\nNhân vật di chuyển trên bản đồ，có thể nói chuyện với NPC\nCây trồng cần được tưới nươc。\nNếu không，sẽ bị héo úa。\n",skin);

        quitButton = new TextButton("Đóng", skin);
        quitButton.setTransform(true);
        quitButton.setScale(0.8f);
        quitButton.setPosition(80,5);

        table.add(label).padLeft(10).padTop(20);
        table.row();
        table.add(quitButton);
        this.add(table).padLeft(10).padTop(20);
        this.setPosition(Gdx.graphics.getWidth()/2-225, Gdx.graphics.getHeight()/2-225);
        this.setSize(500, 500);
        this.setVisible(false);
        this.setMovable(true);
    }
    public TextButton getQuitButton(){
        return quitButton;
>>>>>>> Stashed changes
    }
}
