package com.mygdx.game.screens.loading;

import com.badlogic.gdx.scenes.scene2d.ui.*;


public class LeadingWindow extends Window { // Lớp LeadingWindow kế thừa lớp Window
    private ImageButton next; // Xác định nút hình ảnh
    private final String text; // Xác định văn bản
    private Label label; // Xác định nhãn

    public LeadingWindow(String title, Skin skin, String styleName, String text) { // Xác định phương thức xây dựng
        super(title, skin, styleName);   // Gọi phương thức xây dựng của lớp cha
        this.text=text; // Gán văn bản
        label=new Label(text,skin); // Khởi tạo nhãn
        label.setPosition(0,0); // Thiết lập vị trí
        next=new ImageButton(skin, "up"); // Khởi tạo nút hình ảnh
        next.setPosition(250,20); // Thiết lập vị trí

        this.setVisible(false); // Thiết lập không hiển thị
        this.add(label); // Thêm nhãn
        this.row(); // Thêm hàng mới
        this.addActor(next); // Thêm nút hình ảnh
        this.setMovable(true); // Cho phép di chuyển
        this.setSize(300,270); // Thiết lập kích thước
        this.setPosition(470,250); // Thiết lập vị trí

    }
    public  ImageButton getnextButton(){ // Hàm lấy nút hình ảnh
        return next;
    }
}