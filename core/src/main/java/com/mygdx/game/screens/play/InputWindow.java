package com.mygdx.game.screens.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.crops.Crop;
import com.mygdx.game.crops.Fruit;
import com.mygdx.game.crops.Prop;
import com.mygdx.game.crops.User;

public class InputWindow extends Window{
	//Tổng cộng có 3 hàm tạo chính

	 private Texture texture;//Kết cấu
     private TextButton confirmButton;//Nút xác nhận
     private TextButton cancelButton;//Nút hủy
     private ImageButton closeButton;//Nút đóng
     private TextField numberInputField;//Hộp đàu vào
     private Label fieldTypeLabel;//Nhập nội dung,chẳng hạn như"Số lượng sử dụng"trong balo hay "Số lượng mua" ở cửa hàng
     private Crop crop;
 	 private Fruit fruit;
 	 private Prop prop;
 	 private Label fruitNumberLabel;//Số lượng còn lại（trái cây）
 	 private Label propNumberLabel;//Kiểm kê đạo cụ
     private float x,y;

     public InputWindow(Crop crop, User user, PackageUI bag, Stage stage, Skin skin){//Trình tạo gồm 3 phần：hạt giống、trái cây、đạo cụ

    	 super("", skin,"window1");

    	 this.crop=crop;
    	 texture=new Texture(Gdx.files.internal(crop.getPic()));//Lấy hình ảnh hạt giống và gán nó
    	 Image seedImage=new Image(texture);//Truyền dữ kiệu vào đối tượng hình ảnh
         seedImage.setSize(80, 80);

 	     Label seedNameLabel=new Label(crop.getName(),skin);
 	     Label seedPriceLabel=new Label("Tên: "+crop.getSeedPrice(),skin);
 	     Label seedBuyLevelLabel=new Label("Mục: "+crop.getBuyLevel(),skin);
    	 numberInputField=new TextField("",skin);
    	 numberInputField.setAlignment(Align.center);//Giá trị nhận được
 		 numberInputField.setSize(120, 50);
 		 fieldTypeLabel=new Label("Số lượng mua"+"(1-99)",skin);

 		 InputWindow thisInputWindow=this;//Nhập
 		 confirmButton=new TextButton("Xác nhận",skin);
         confirmButton.addListener(new ClickListener(Buttons.LEFT){

        	 public void clicked(InputEvent event,float x, float y) {

        	    String regex="^[1-9]\\d*$";
        		if(!numberInputField.getText().matches(regex)) {
           		new Dialog("",skin,"dialog").text("Tôi không hiểu mong muốn của bạn, Vui lòng thực hiện lại!").button("Xác nhận", false).show(stage);
           		numberInputField.setText("");
        		}
           		else if(numberInputField.getText().length()>2) {
           		new Dialog("",skin,"dialog").text("Số lượng vượt quá giới hạn mua!").button("Xác nhận", false).show(stage);
              		numberInputField.setText("");
           		}
           		else{
               		int number = Integer.parseInt(numberInputField.getText());
               		if(number*crop.getSeedPrice()>user.getMoney()) {
               			new Dialog("",skin,"dialog").text("Xin lỗi,không đủ tiền!").button("Xác nhận", false).show(stage);
               			numberInputField.setText("");
               		}
               		else if(user.getLevel()<crop.getBuyLevel()) {
               			new Dialog("",skin,"dialog").text("Xin lỗi,cấp bậc hiện tại chưa đủ！").button("Xác nhận", false).show(stage);
               			numberInputField.setText("");
               		}
               		else {
               			new Dialog("",skin,"dialog"){
        					protected void result(Object object) {
        						if (object.equals(true)) {

        							thisInputWindow.setVisible(false);;//Nhập
        						}
        					}
        				}.text("Đã mua thành công"+crop.getName()+"x"+numberInputField.getText()).button("确认", true).show(stage);
               			numberInputField.setText("");
               			crop.setCropNumber(crop.getCropNumber()+number);
               			user.setMoney(user.getMoney()-number*crop.getSeedPrice());
               		}
           	    }


           		bag.update();//Cập nhật các vật phẩm trong balo

     	     }
         });

 		 cancelButton=new TextButton("Hủy",skin);
         cancelButton.addListener(new ClickListener(Buttons.LEFT){//Hủy thao tác và đóng cửa sổ

        	 public void clicked(InputEvent event,float x, float y) {

        		thisInputWindow.setVisible(false);
        		numberInputField.setText("");
     	     }
         });


         closeButton=new ImageButton(skin,"win1");//cửa sổ
 		 closeButton.setTransform(true);
 		 closeButton.setScale(0.7f);
         closeButton.addListener(new ClickListener(Buttons.LEFT){
        	 public void clicked(InputEvent event,float x, float y) {

        		 thisInputWindow.setVisible(false);
        		 numberInputField.setText("");
     	     }
         });
 		this.addActor(seedNameLabel);
 		this.addActor(seedPriceLabel);
 		this.addActor(seedBuyLevelLabel);
 		this.addActor(seedImage);
 		this.addActor(fieldTypeLabel);
		this.addActor(confirmButton);
		this.addActor(cancelButton);
		this.addActor(closeButton);
		this.addActor(numberInputField);

        this.setModal(true);//Max cấp độ
        this.setMovable(true);//Cửa sổ có thể di chuyên đc
        this.setWidth(Gdx.graphics.getWidth()/3.5f);//Đặt kích thước
        this.setHeight(Gdx.graphics.getHeight()/2.5f);
        x=this.getWidth();
        y=this.getHeight();

        seedNameLabel.setPosition(x/2-seedNameLabel.getWidth()/2, y/10*7);//Đặt vị trí cửa ảnh,nhã trong cửa sổ
        seedImage.setPosition(x/5*1,y/10*4.3f);
        seedPriceLabel.setPosition(x/5*2.7f, y/10*5.5f);
        seedBuyLevelLabel.setPosition(x/5*2.7f, y/10*4.5f);
        fieldTypeLabel.setPosition(x/5*0.55f, y/10*2.7f);
		numberInputField.setPosition(x/5*2.7f, y/10*2.5f);
		closeButton.setPosition(x/10*8.5f, y/10*8.2f);
        confirmButton.setPosition(x/4*1.2f-confirmButton.getWidth()/2, 0);
        cancelButton.setPosition(x/4*2.8f-confirmButton.getWidth()/2, 0);
        this.setPosition(Gdx.graphics.getWidth()/2-this.getWidth()/2, Gdx.graphics.getHeight()/2-this.getHeight()/2);
        this.setVisible(false);

     }


	 public InputWindow(Fruit fruit, User user, PackageUI bag, Stage stage, Skin skin) {

		 super("", skin,"window1");
   	     this.fruit=fruit;

		 texture=new Texture(Gdx.files.internal(fruit.getPic()));
	     Image fruitImage=new Image(texture);
	     fruitImage.setSize(80, 80);
 	     Label fruitNameLabel=new Label(fruit.getName(),skin);
 	     Label fruitPriceLabel=new Label("Giá: "+fruit.getFruitPrice(),skin);
 	     fruitNumberLabel=new Label("Số lượng: "+fruit.getFruitNumber(),skin);
   	     numberInputField=new TextField("",skin);
		 numberInputField.setSize(120, 50);
		 numberInputField.setAlignment(Align.center);
		 fieldTypeLabel=new Label("Số lượng bán"+"(1-999)",skin);

		 InputWindow thisInputWindow=this;
		 confirmButton=new TextButton("Xác nhận",skin);
         confirmButton.addListener(new ClickListener(Buttons.LEFT){
       	     public void clicked(InputEvent event,float x, float y) {

   			     String regex="^[1-9]\\d*$";
   			     if(!numberInputField.getText().matches(regex)) {

      			     new Dialog("",skin,"dialog").text("Tôi không hiểu mong muốn cửa bạn，vui lòng thực hiện lại!").button("Xác nhận", true).show(stage);
      			     numberInputField.setText("");
   			     }
      			 else if(numberInputField.getText().length()>3) {
      			     new Dialog("",skin,"dialog").text("Quá nhiều,vượt quá số lượng còn tồn!").button("Xác nhận", true).show(stage);
         			 numberInputField.setText("");
      			 }
      			 else{

          			 int number = Integer.parseInt(numberInputField.getText());
          			 if(number>fruit.getFruitNumber()) {


          			     new Dialog("",skin,"dialog").text("Xin lỗi, số lượng trái cây của bạn không đủ!").button("Xác nhận", true).show(stage);
          				 numberInputField.setText("");
          			 }
          			 else {

          			     new Dialog("",skin,"dialog"){
              			     protected void result(Object object) {

            					if (object.equals(true)) {

            						thisInputWindow.setVisible(false);;//Nhập
            					}
            				}
            			 }.text("Đã bán thành công!Tiền+"+Integer.parseInt(numberInputField.getText())*fruit.getFruitPrice()).button("Số lượng", true).show(stage);
          				 numberInputField.setText("");
          				 fruit.setFruitNumber(fruit.getFruitNumber()-number);
          				 user.setMoney(user.getMoney()+number*fruit.getFruitPrice());
          			 }
      		      }

      		      bag.update();
    	   }
        });

		 cancelButton=new TextButton("Hủy",skin);
         cancelButton.addListener(new ClickListener(Buttons.LEFT){
       	 public void clicked(InputEvent event,float x, float y) {

       		thisInputWindow.setVisible(false);
       		numberInputField.setText("");
    	   }
        });


         closeButton=new ImageButton(skin,"win1");
		 closeButton.setTransform(true);
		 closeButton.setScale(0.7f);
         closeButton.addListener(new ClickListener(Buttons.LEFT){
       	 public void clicked(InputEvent event,float x, float y) {

       		thisInputWindow.setVisible(false);
       		numberInputField.setText("");
    	   }
        });
		 this.addActor(fruitNameLabel);
		 this.addActor(fruitPriceLabel);
		 this.addActor(fruitNumberLabel);
		 this.addActor(fruitImage);
		 this.addActor(fieldTypeLabel);
		 this.addActor(confirmButton);
		 this.addActor(cancelButton);
		 this.addActor(closeButton);
		 this.addActor(numberInputField);
         this.setModal(true);
         this.setMovable(true);
         this.setWidth(Gdx.graphics.getWidth()/3.5f);
         this.setHeight(Gdx.graphics.getHeight()/2.5f);
         x=this.getWidth();
         y=this.getHeight();
         fruitNameLabel.setPosition(x/2-fruitNameLabel.getWidth()/2, y/10*7);
         fruitImage.setPosition(x/5*1,y/10*4.3f);
         fruitPriceLabel.setPosition(x/5*2.7f, y/10*5.5f);
         fruitNumberLabel.setPosition(x/5*2.7f, y/10*4.5f);
         fieldTypeLabel.setPosition(x/5*0.55f, y/10*2.7f);
		 numberInputField.setPosition(x/5*2.7f, y/10*2.5f);
		 closeButton.setPosition(x/10*8.5f, y/10*8.2f);
         confirmButton.setPosition(x/4*1.2f-confirmButton.getWidth()/2, 0);
         cancelButton.setPosition(x/4*2.8f-confirmButton.getWidth()/2, 0);
         this.setPosition(Gdx.graphics.getWidth()/2-this.getWidth()/2, Gdx.graphics.getHeight()/2-this.getHeight()/2);
         this.setVisible(false);


	}


	 public InputWindow(String fieldType, Prop prop, User user, PackageUI bag, Stage stage, Skin skin) {

		 super("", skin,"window1");
   	     this.prop=prop;

		 texture=new Texture(Gdx.files.internal(prop.getPic()));
	     Image propImage=new Image(texture);
	     propImage.setSize(80, 80);
   	     numberInputField=new TextField("",skin);
		 numberInputField.setSize(120, 50);
		 numberInputField.setAlignment(Align.center);
		 fieldTypeLabel=new Label(fieldType+"(1-99)",skin);
		 confirmButton=new TextButton("Xác nhận",skin);


         InputWindow thisInputWindow=this;
		 cancelButton=new TextButton("Hủy",skin);
         cancelButton.addListener(new ClickListener(Buttons.LEFT){
       	 public void clicked(InputEvent event,float x, float y) {

       		thisInputWindow.setVisible(false);
       		numberInputField.setText("");
    	   }
        });


         closeButton=new ImageButton(skin,"win1");
		 closeButton.setTransform(true);
		 closeButton.setScale(0.7f);
         closeButton.addListener(new ClickListener(Buttons.LEFT){
       	 public void clicked(InputEvent event,float x, float y) {

       		thisInputWindow.setVisible(false);
       		numberInputField.setText("");
    	   }
        });

		 this.addActor(propImage);
		 this.addActor(fieldTypeLabel);
		 this.addActor(confirmButton);
		 this.addActor(cancelButton);
		 this.addActor(closeButton);
		 this.addActor(numberInputField);
         this.setModal(true);
         this.setMovable(true);
         this.setWidth(Gdx.graphics.getWidth()/3.5f);
         this.setHeight(Gdx.graphics.getHeight()/2.5f);
         x=this.getWidth();
         y=this.getHeight();
         propImage.setPosition(x/5*1,y/10*4.3f);
         fieldTypeLabel.setPosition(x/5*0.55f, y/10*2.7f);
		 numberInputField.setPosition(x/5*2.7f, y/10*2.5f);
		 closeButton.setPosition(x/10*8.5f, y/10*8.2f);
         confirmButton.setPosition(x/4*1.2f-confirmButton.getWidth()/2, 0);
         cancelButton.setPosition(x/4*2.8f-confirmButton.getWidth()/2, 0);
         this.setPosition(Gdx.graphics.getWidth()/2-this.getWidth()/2, Gdx.graphics.getHeight()/2-this.getHeight()/2);
         this.setVisible(false);
         if(fieldType=="Số lượng mua") {

            Label propNameLabel=new Label(prop.getName(),skin);
     	    Label propPriceLabel=new Label("Giá trị: "+prop.getPropPrice(),skin);
     	    Label propBuyLevelLabel=new Label("Giá trị: "+prop.getBuyLevel(),skin);

    		 this.addActor(propNameLabel);
    		 this.addActor(propPriceLabel);
    		 this.addActor(propBuyLevelLabel);

    		 propNameLabel.setPosition(x/2-propNameLabel.getWidth()/2, y/10*7);
    		 propPriceLabel.setPosition(x/5*2.7f, y/10*5.5f);
    		 propBuyLevelLabel.setPosition(x/5*2.7f, y/10*4.5f);

    		 confirmButton.addListener(new ClickListener(Buttons.LEFT){
    	       	 public void clicked(InputEvent event,float x, float y) {

    	   			 String regex="^[1-9]\\d*$";
    	   			 if(!numberInputField.getText().matches(regex)) {
    	      			 new Dialog("",skin,"dialog").text("Tôi không hiểu mong muốn cửa bạn,vui lòng thực hiện lại!").button("Xác nhận", true).show(stage);
    	      			 numberInputField.setText("");
    	   			 }
    	      		 else if(numberInputField.getText().length()>3) {
    	      			 new Dialog("",skin,"dialog").text("Quá nhiều,vượt quá giới hạn mua!").button("Xác nhận", true).show(stage);
    	         		 numberInputField.setText("");
    	      		 }
    	      		 else{
    	          		 int number = Integer.parseInt(numberInputField.getText());
    	          		 if(number*prop.getPropPrice()>user.getMoney()) {

    	          			 new Dialog("",skin,"dialog").text("Xin lỗi,bạn không đủ tiền!").button("Xác nhận", true).show(stage);
    	          			 numberInputField.setText("");
    	          		 }
    	          		else if(user.getLevel()<prop.getBuyLevel()) {
                   			new Dialog("",skin,"dialog").text("Xin lỗi,cấp độ của bạn chưa đủ！").button("Xác nhận", false).show(stage);
                   			numberInputField.setText("");
                   		}
    	          		else {
    	          			 new Dialog("",skin,"dialog"){
                  			     protected void result(Object object) {

                 					if (object.equals(true)) {

                 						thisInputWindow.setVisible(false);;//取此窗口
                 					}
                 				}
                 			 }.text("Đã mua thành công"+prop.getName()+"x"+numberInputField.getText()).button("Xác nhận", true).show(stage);
    	          		     numberInputField.setText("");
    	          			 prop.setPropNumber(prop.getPropNumber()+number);
    	          			 user.setMoney(user.getMoney()-number*prop.getPropPrice());
    	          		 }
    	      		 }



    	      	     bag.update();

    	    	  }
    	       });
         }
         else if(fieldType=="Số lượng sử dụng") {

        	 Label propNameLabel=new Label(prop.getName(),skin);
        	 Label propPropertyLabel=new Label(prop.getProperty()+": "+prop.getValue(),skin);
        	 propNumberLabel=new Label("Giá trị: "+prop.getPropNumber(),skin);

        	 this.addActor(propNameLabel);
    		 this.addActor(propPropertyLabel);
    		 this.addActor(propNumberLabel);

    		 propNameLabel.setPosition(x/2-propNameLabel.getWidth()/2, y/10*7);
    		 propPropertyLabel.setPosition(x/5*2.7f, y/10*5.5f);
    		 propNumberLabel.setPosition(x/5*2.7f, y/10*4.5f);
    		 confirmButton.addListener(new ClickListener(Buttons.LEFT){
    	       	 public void clicked(InputEvent event,float x, float y) {
    	   			 String regex="^[1-9]\\d*$";
    	   			 if(!numberInputField.getText().matches(regex)) {
    	      			 new Dialog("",skin,"dialog").text("Tôi không hiểu mong muốn cửa bạn,vui lòng thực hiện lại!").button("Xác nhận", false).show(stage);
    	      			 numberInputField.setText("");
    	   			 }
    	      		 else if(numberInputField.getText().length()>3) {
    	      			 new Dialog("",skin,"dialog").text("Quá nhiều,vượt quá giới hạn sử dụng!").button("Xác nhận", false).show(stage);
    	         	     numberInputField.setText("");
    	      		 }
    	      		 else{
    	          		 int number = Integer.parseInt(numberInputField.getText());
    	          		 if(number>prop.getPropNumber()) {
    	          			 new Dialog("",skin,"dialog").text("Xin lỗi,đò ăn không đủ!").button("Xác nhận", false).show(stage);
    	          			 numberInputField.setText("");
    	          		 }
    	          		 else {
    	          			 new Dialog("",skin,"dialog"){
                  			     protected void result(Object object) {

                 					if (object.equals(true)) {

                 						thisInputWindow.setVisible(false);
                 					}
                 				}
                 			 }.text("Ăn thành công"+prop.getName()+"x"+numberInputField.getText()+"!"+"thể lực phục hồi"+prop.getValue()*Integer.parseInt(numberInputField.getText())).button("确认", true).show(stage);
    	          			 numberInputField.setText("");
    	          			 prop.setPropNumber(prop.getPropNumber()-number);
    	          			 if(user.getHp()+number*prop.getValue()<=100)
    	          				 user.setHp(user.getHp()+number*prop.getValue());
    	          			 else
    	          				 user.setHp(100);
    	          		 }
    	      		 }



    	      		 bag.update();

    	    	 }
    	     });

         }
	}
	 @Override
	    public void act(float delta) {
	    	super.act(delta);
	    	if(fruit!=null)
	    		fruitNumberLabel.setText("Số lượng: "+fruit.getFruitNumber());
	    	else if(prop!=null&&propNumberLabel!=null)
	    		propNumberLabel.setText("Kho hàng: "+prop.getPropNumber());


		}
	 public TextButton getConfirmButton() {
		 return confirmButton;
	 }
	 public TextField getNumberInputField() {
		 return numberInputField;
	 }
}
