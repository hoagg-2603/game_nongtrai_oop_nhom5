package com.mygdx.game.screens.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Cursor;
import com.mygdx.game.crops.Crop;
import com.mygdx.game.crops.Fruit;
import com.mygdx.game.crops.Prop;
import com.mygdx.game.crops.User;
import com.mygdx.game.screens.PlayScreen;

public class SlotUI extends Table{
	// Ô nhỏ trên ba lô, kho lưu trữ

	private Texture texture;//Kết cấu, dùng để lưu trữ hình ảnh
	private float width,height;//Để lưu trữ chiều dài và chiều rộng của bảng
	private InputWindow inputWindow;//Cửa sổ nhập
	private Crop crop=null;
	private Fruit fruit=null;
	private Prop prop=null;
	private Label seedNumberLabel;
	private Label fruitNumberLabel;
	private Label propNumberLabel;
	private Pixmap pm;//Lưu trữ hình ảnh con trỏ
    public SlotUI(Crop crop, User user, PackageUI bag, Stage stage, Skin skin, String type){
    	super();

    	this.crop=crop;
        if(type=="storeSeed"){//Tạo bảng nhỏ cho hạt giống trong cửa hàng
        texture=new Texture(Gdx.files.internal(crop.getPic()));
        Image seedImage=new Image(texture);
        seedImage.setSize(80, 80);

	    Label seedNameLabel=new Label(crop.getName(),skin);
	    Label seedPriceLabel=new Label("价格: "+crop.getSeedPrice(),skin);
	    Label seedBuyLevelLabel=new Label("等级: "+crop.getBuyLevel(),skin);

        inputWindow=new InputWindow(crop,user,bag,stage,skin);

	    this.add(seedNameLabel);
	    this.row();
	    this.add(seedImage).width(80).height(80).padTop(18);
	    this.row();
	    this.add(seedPriceLabel).padTop(15).width(127.5f);
	    this.row();
	    this.add(seedBuyLevelLabel).width(127.5f);
	    this.addListener(new ClickListener(Buttons.LEFT){//Đặt màn hình cho vị trí của hạt giống kho và cửa sổ nhập sẽ bật lên khi nhấp vào.
	       	 public void clicked(InputEvent event,float x, float y) {

	       		inputWindow.setVisible(true);
	       		inputWindow.toFront();
	    	   }
	        });
        }
        else if(type=="bagSeed"){// Tạo bảng nhỏ cho hạt giống trong ba lô
        	texture=new Texture(Gdx.files.internal(crop.getPic()));
            Image seedImage=new Image(texture);

            seedImage.setSize(80, 80);
    	    Label seedNameLabel=new Label(crop.getName(),skin);
    	    Label seedGrowthCycleLabel=new Label("Chu kỳ:"+crop.getStageCount()+"x"+crop.getUnitTime()+"时",skin);
    	    pm= new Pixmap(Gdx.files.internal("../assets/image/mouse/seed/seed"+crop.getCropId()+".png"));
    	    seedNumberLabel=new Label("Tồn kho:"+crop.getCropNumber(),skin);
    	    this.addListener(new ClickListener(Buttons.LEFT){

           	     public void clicked(InputEvent event,float x, float y) {
                     Cursor cursor = Gdx.graphics.newCursor(pm, 0, 0);  // Tạo chuột mới từ Pixmap
                     Gdx.graphics.setCursor(cursor);  // Thiết lập chuột mới
                     PlayScreen.mouseStatus = crop.getCropId();
                     bag.setVisible(false);
           	     }
            });

    	    this.add(seedNameLabel);
    	    this.row();
    	    this.add(seedImage).width(80).height(80).padTop(18);
    	    this.row();
    	    this.add(seedGrowthCycleLabel).padTop(15).width(127.5f);
    	    this.row();
    	    this.add(seedNumberLabel).width(127.5f);
        }
     }


    public SlotUI(Fruit fruit, User user, PackageUI bag, Stage stage, Skin skin) {
    	super();

    	this.fruit=fruit;
        texture=new Texture(Gdx.files.internal(fruit.getPic()));
        Image fruitImage=new Image(texture);
        fruitImage.setSize(80, 80);

	    Label fruitNameLabel=new Label(fruit.getName(),skin);
	    Label fruitPriceLabel=new Label("Giá bán:"+fruit.getFruitPrice(),skin);
	    fruitNumberLabel=new Label("Tồn kho: "+fruit.getFruitNumber(),skin);

        inputWindow=new InputWindow(fruit,user,bag,stage,skin);

	    this.add(fruitNameLabel);
	    this.row();
	    this.add(fruitImage).width(80).height(80).padTop(18);
	    this.row();
	    this.add(fruitPriceLabel).padTop(15).width(127.5f);
	    this.row();
	    this.add(fruitNumberLabel).width(127.5f);
	    this.addListener(new ClickListener(Buttons.LEFT){
	       	 public void clicked(InputEvent event,float x, float y) {

	       		inputWindow.setVisible(true);
	       		inputWindow.toFront();
	    	   }
	        });
        }

    public SlotUI(Prop prop, User user, PackageUI bag, Stage stage, Skin skin, String type, Boolean createWindow){//Boolean createWindow判断道具是否需要创建输入
    	super();

    	this.prop=prop;
        if(type=="storeProp") {// đạo cụ trong cửa hàng cần tạo cửa sổ nhập

        texture=new Texture(Gdx.files.internal(prop.getPic()));
        Image propImage=new Image(texture);

        propImage.setSize(80, 80);
	    Label propNameLabel=new Label(prop.getName(),skin);
	    Label propPriceLabel=new Label("Giá: "+prop.getPropPrice(),skin);
	    Label propBuyLevelLabel=new Label("Cấp độ: "+prop.getBuyLevel(),skin);


        inputWindow=new InputWindow("Số lượng mua",prop,user,bag,stage,skin);

	    this.add(propNameLabel);
	    this.row();
	    this.add(propImage).width(80).height(80).padTop(18);
	    this.row();
	    this.add(propPriceLabel).padTop(15).width(127.5f);
	    this.row();
	    this.add(propBuyLevelLabel).width(127.5f);
	    this.addListener(new ClickListener(Buttons.LEFT){
	       	 public void clicked(InputEvent event,float x, float y) {

	       		inputWindow.setVisible(true);
	       		inputWindow.toFront();
	    	   }
	        });
        }
        else if(type=="bagProp"){
        	texture=new Texture(Gdx.files.internal(prop.getPic()));
            Image propImage=new Image(texture);
            propImage.setSize(80, 80);

     	    Label propNameLabel=new Label(prop.getName(),skin);
     	    Label propPropertyLabel=new Label(prop.getProperty()+":"+prop.getValue(),skin);
     	    propNumberLabel=new Label("Tồn kho:"+prop.getPropNumber(),skin);

     	    if(createWindow){//Tạo cửa sổ nhập nếu cần và thêm listener
                inputWindow=new InputWindow("Số lượng sử dụng",prop,user,bag,stage,skin);
     	        this.addListener(new ClickListener(Buttons.LEFT){

   	       	        public void clicked(InputEvent event,float x, float y) {

   	       		        inputWindow.setVisible(true);
   	     	            inputWindow.toFront();
   	    	            }
   	                });
             }
     	    else{// Nếu không cần cửa sổ, chỉ cần đổi hình dạng con trỏ khi click
     	    	pm= new Pixmap(Gdx.files.internal("../assets/image/mouse/prop/prop"+prop.getPropId()+".png"));
     		    this.addListener(new ClickListener(Buttons.LEFT){
        	       	public void clicked(InputEvent event,float x, float y) {
                        Cursor cursor = Gdx.graphics.newCursor(pm, 0, 0);  // Tạo chuột mới từ Pixmap
                        Gdx.graphics.setCursor(cursor);  // Thiết lập chuột mới
                  		PlayScreen.mouseStatus=-4;
                  		bag.setVisible(false);
        	    	    }
        	        });
 	       	 }

     	    this.add(propNameLabel);
     	    this.row();
     	    this.add(propImage).width(80).height(80).padTop(18);
     	    this.row();
     	    this.add(propPropertyLabel).padTop(15).width(128);
     	    this.row();
     	    this.add(propNumberLabel).width(128);
        }
    }

    public InputWindow getInputWindow(){
    	return inputWindow;
    }

    @Override
    public void act(float delta){
    	super.act(delta);
    	if(crop!=null&&seedNumberLabel!=null)
    		seedNumberLabel.setText("Tồn kho: "+crop.getCropNumber());
    	else if(fruit!=null&&fruitNumberLabel!=null)
    		fruitNumberLabel.setText("Tồn kho: "+fruit.getFruitNumber());
    	else if(prop!=null&&propNumberLabel!=null)
    		propNumberLabel.setText("Tồn kho: "+prop.getPropNumber());
	}

}
