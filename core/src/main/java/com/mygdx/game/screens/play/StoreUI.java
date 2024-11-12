package com.mygdx.game.screens.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.crops.Crop;
import com.mygdx.game.crops.Prop;
import com.mygdx.game.crops.User;

public class StoreUI extends Window{
	//Giao diện cửa hàng

     private Texture texture;//Kết cấu, dùng để xử lý hình ảnh
     private TextureRegion region;//Cắt kết cấu
     private TextureRegionDrawable windowdrable;//Dùng để đặt hình nền của cửa sổ
     private TextButton seedButton;//Nút điều hướng loại hạt giống
     private TextButton propButton;//Nút điều hướng loại đạo cụ
     private ImageButton leftButton;//Biểu tượng chuyển sang trang bên trái
     private ImageButton rightButton;//Biểu tượng chuyển sang trang bên phải
     private ImageButton closeButton;//Nút đóng
     private Label titleLabel;//Nhãn tên cửa hàng
     private Table seedTable1;//Trang đầu của cửa hàng hạt giống
     private Table seedTable2;//Trang thứ hai của cửa hàng hạt giống
     private Table propTable;//Trang đạo cụ
     private SlotUI[] storeSeedTable;//Các loại hạt giống
     private SlotUI[] storePropTable;//Các loại đạo cụ
     private float x,y;//Ghi lại chiều dài và chiều rộng của cửa sổ cửa hàng

	 public StoreUI(Array<Crop> cropArray, Array<Prop> propArray, User user , PackageUI bag, Stage stage, Skin skin) {
		super("",skin);

        seedTable1=new Table();
        seedTable2=new Table();
		propTable=new Table();

		 storeSeedTable=new SlotUI[cropArray.size];

	     for(int j=0;j<storeSeedTable.length;j++){// Tạo slot cho từng loại hạt giống trong cửa hàng và đưa đối tượng vào stage
	        	storeSeedTable[j]=new SlotUI(cropArray.get(j),user,bag,stage,skin,"storeSeed");
	        	stage.addActor(storeSeedTable[j].getInputWindow());
	        }

	     storePropTable=new SlotUI[propArray.size];
	     for(int i=0;i<propArray.size;i++){//Tạo slot cho từng loại đạo cụ trong cửa hàng và đưa đối tượng vào stage
	        	storePropTable[i]=new SlotUI(propArray.get(i),user,bag,stage,skin,"storeProp",true);
	        	stage.addActor(storePropTable[i].getInputWindow());
	        }

	     for(int i=0;i<storeSeedTable.length;i++){//Sắp xếp các slot hạt giống trên từng trang của cửa hàng
	        	if(i<=7) {
	        	seedTable1.add(storeSeedTable[i]).padLeft(40).height(245);
	        	if(i==3)
	        	seedTable1.row();
	        	}
	        	if(i>7) {
	        	seedTable2.add(storeSeedTable[i]).padLeft(40).height(245);
	            	if(i==11)
	            seedTable2.row();
	        	}
	        }
	     for(int i=0;i<storePropTable.length;i++){//Sắp xếp các slot đạo cụ trên từng trang của cửa hàng

	        	propTable.add(storePropTable[i]).padLeft(40).height(245);


	        }

		texture=new Texture(Gdx.files.internal("../assets/image/background/store.png"));// Nhập hình ảnh, đặt hình nền của cửa sổ
		region=new TextureRegion(texture,0,0,748,645);
		windowdrable=new TextureRegionDrawable(region);
		this.setBackground(windowdrable);


	    titleLabel=new Label("商店",skin,"title");//Đặt tiêu đề của cửa sổ
	    titleLabel.setScale(0.5f);
	    this.addActor(titleLabel);
	    titleLabel.setColor(Color.BLACK);

		seedButton=new TextButton("种子",skin);
		seedButton.setTransform(true);
        seedButton.setScale(1);
        seedButton.addListener(new ClickListener(Buttons.LEFT){//Thiết lập lắng nghe, khi nhấn sẽ chuyển sang trang hạt giống
       	 public void clicked(InputEvent event,float x, float y) {

       		propTable.setVisible(false);
       		seedTable2.setVisible(false);
     		seedTable1.setVisible(true);
    	   }
        });
        this.addActor(seedButton);

        propButton=new TextButton("Đạo cụ",skin);
        propButton.setTransform(true);
        propButton.setScale(1);
        propButton.addListener(new ClickListener(Buttons.LEFT){//Thiết lập lắng nghe, khi nhấn sẽ chuyển sang trang đạo cụ
       	 public void clicked(InputEvent event,float x, float y) {

              propTable.setVisible(true);
              seedTable1.setVisible(false);
              seedTable2.setVisible(false);
    	   }
        });
		this.addActor(propButton);

		leftButton=new ImageButton(skin,"down");
		leftButton.addListener(new ClickListener(Buttons.LEFT){//Chuyển sang trang bên trái
		public void clicked(InputEvent event,float x, float y) {
			if(seedTable2.isVisible())
				seedTable1.setVisible(true);
			    seedTable2.setVisible(false);
		 }
	    });
		this.addActor(leftButton);

		rightButton=new ImageButton(skin,"up");
		rightButton.addListener(new ClickListener(Buttons.LEFT){//Chuyển sang trang bên phải
		public void clicked(InputEvent event,float x, float y) {
			if(seedTable1.isVisible())
				seedTable2.setVisible(true);
			    seedTable1.setVisible(false);
		 }
	    });
		this.addActor(rightButton);

		StoreUI thisstore=this;
        closeButton=new ImageButton(skin,"win2");
		closeButton.setTransform(true);
        closeButton.addListener(new ClickListener(Buttons.LEFT){//Đóng cửa sổ
       	 public void clicked(InputEvent event,float x, float y) {

       		 seedTable1.setVisible(true);
       		 seedTable2.setVisible(false);
       		 propTable.setVisible(false);
       		 thisstore.setVisible(false);
    	   }
        });
		this.addActor(closeButton);// Đưa tất cả các đối tượng vào cửa sổ cửa hàng và đặt vị trí

		this.addActor(seedTable1);
		this.addActor(seedTable2);
		this.addActor(propTable);
		seedTable1.setPosition(457, 375);
		seedTable2.setPosition(457, 375);
		propTable.setPosition(457, 497.5f);
        this.setModal(true);
        this.setMovable(true);
        this.setSize(748*1.3f, 645*1.3f);
        x=this.getWidth();
        y=this.getHeight();
        seedButton.setPosition(x/10*2.9f, y/10*7.5f);
        propButton.setPosition(x/10*5.95f, y/10*7.5f);
        closeButton.setPosition(x/10*8.8f, y/10*8.2f);
        leftButton.setPosition(x/10*1, y/10*4.3f);
        rightButton.setPosition(x/10*8.5f, y/10*4.3f);
        titleLabel.setPosition(x/2-titleLabel.getWidth()/2, y/10*8.8f);
        this.setPosition(Gdx.graphics.getWidth()/2-this.getWidth()/2, Gdx.graphics.getHeight()/2-this.getHeight()/2);
        this.setVisible(false);
        seedTable1.setVisible(true);
        seedTable2.setVisible(false);
        propTable.setVisible(false);

	}



	public Table getseedTable1() {
		return seedTable1;
	}
	public Table getseedTable2() {
		return seedTable2;
	}
	public Table getpropTable() {
	    return propTable;
	}
	public SlotUI[] getStoreSeedTable() {
		return storeSeedTable;
	}
    public SlotUI[] getStorePropTable() {
    	return storePropTable;
    }


}
