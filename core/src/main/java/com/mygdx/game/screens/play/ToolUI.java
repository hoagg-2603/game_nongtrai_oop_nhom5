package com.mygdx.game.screens.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Cursor;
import com.mygdx.game.screens.PlayScreen;

public class ToolUI extends Table{
    //Thanh công cụ

    private Label label[];//Nhãn cho tên các công cụ khác nhau trên thanh công cụ
    private float x,y;//Chiều rộng và chiều cao của thanh công cụ
    private Texture[] texture;//Kết cấu
    private TextureRegion[] buttonup;//Cắt kết cấu
    private TextureRegion[] buttondown;
    private TextureRegionDrawable[] up;//Lưu trạng thái biểu tượng thanh công cụ khi chuột nhả ra
    private TextureRegionDrawable[] down;//Lưu trạng thái biểu tượng thanh công cụ khi chuột bấm xuống
    private ImageButton[] button;//Nút biểu tượng công cụ
    private Pixmap pm1,pm2,pm3;//Ảnh trạng thái chuột

    public ToolUI(Skin skin) {

    	  super();
    	  pm1 = new Pixmap(Gdx.files.internal("../assets/image/mouse/tool/tool2.png"));
    	  pm2 = new Pixmap(Gdx.files.internal("../assets/image/mouse/tool/tool1.png"));
    	  pm3 = new Pixmap(Gdx.files.internal("../assets/image/mouse/tool/tool0.png"));


          label=new Label[5];
          texture=new Texture[10];

          label[0]=new Label("Thu hoạch",skin,"white");
          label[1]=new Label("Ba lô",skin,"white");
          label[2]=new Label("Tưới nước",skin,"white");
          label[3]=new Label("Cửa hàng",skin,"white");
          label[4]=new Label("Công cụ",skin,"white");



          texture[0]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton0.png"));
          texture[1]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton1.png"));
          texture[2]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton2.png"));
          texture[3]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton3.png"));
          texture[4]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton4.png"));
          texture[5]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton5.png"));
          texture[6]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton6.png"));
          texture[7]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton7.png"));
          texture[8]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton8.png"));
          texture[9]=new Texture(Gdx.files.internal("../assets/image/tools"+"/topbutton9.png"));
          buttonup=new TextureRegion[5] ;
          buttondown=new TextureRegion[5] ;
          up=new TextureRegionDrawable[5];
          down=new TextureRegionDrawable[5];
          button=new ImageButton[5];


          for(int i=0;i<=4;i++) {
          	buttonup[i]=new TextureRegion(texture[i+5],0,0,80,80);
          	buttondown[i]=new TextureRegion(texture[i],0,0,48,48);
          	up[i]=new TextureRegionDrawable(buttonup[i]);
          	down[i]=new TextureRegionDrawable(buttondown[i]);
          	button[i]=new ImageButton(up[i],down[i]);

          }

          button[0].addListener(new ClickListener(Buttons.LEFT){// Thay đổi trạng thái chuột khi nhấp vào công cụ
        	 public void clicked(InputEvent event,float x, float y) {
                 Cursor cursor = Gdx.graphics.newCursor(pm1, 0, 0);  // Tạo chuột mới từ Pixmap
                 Gdx.graphics.setCursor(cursor);  // Thiết lập chuột mới
        		 PlayScreen.mouseStatus=-3;
        	 }
          });

          button[2].addListener(new ClickListener(Buttons.LEFT){// Thay đổi trạng thái chuột khi nhấp vào công cụ
        	 public void clicked(InputEvent event,float x, float y) {
                 Cursor cursor = Gdx.graphics.newCursor(pm2, 0, 0);  // Tạo chuột mới từ Pixmap
                 Gdx.graphics.setCursor(cursor);  // Thiết lập chuột mới
        		 PlayScreen.mouseStatus=-2;
        	 }
          });
          button[4].addListener(new ClickListener(Buttons.LEFT){// Thay đổi trạng thái chuột khi nhấp vào công cụ
        	 public void clicked(InputEvent event,float x, float y) {
                 Cursor cursor = Gdx.graphics.newCursor(pm3, 0, 48);  // Tạo chuột mới từ Pixmap
                 Gdx.graphics.setCursor(cursor);  // Thiết lập chuột mới
        		 PlayScreen.mouseStatus=-1;
        	 }
          });

          this.add(button[0]).width(120).height(90);// Sắp xếp tất cả các biểu tượng công cụ vào thanh công cụ
          for(int i=1;i<=4;i++) {
        	  this.add(button[i]).padLeft(50).width(120).height(90);;
          }
          this.row();


          this.add(label[0]);
          for(int i=1;i<=4;i++){//Sắp xếp tất cả các nhãn tên công cụ vào thanh công cụ
        	  this.add(label[i]).padLeft(55);}
          x=Gdx.graphics.getWidth()/2-this.getWidth()/2;
          y=65;

          this.setPosition(x,y);
         }
          public ImageButton getPackageButton(){
        	  return button[1];

         }
          public ImageButton getStoreButton() {
        	  return button[3];
          }

}
