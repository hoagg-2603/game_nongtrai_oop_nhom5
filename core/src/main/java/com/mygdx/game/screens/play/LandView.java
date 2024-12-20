package com.mygdx.game.screens.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.crops.Crop;
import com.mygdx.game.crops.Land;
import com.mygdx.game.screens.PlayScreen;



public class LandView extends Actor{
    //Lớp để hiển thị trực quan đất
    private Land land; //Dữ liệu đất
    private PlayScreen playScreen;
    private Crop crop; //Dữ liệu cây trồng trên đất
    private Texture landTexture; // Ảnh nền đất
    private Texture cropTexture; // Ảnh cây trồng, thay đổi tùy theo tình trạng trồng trên đất
    private double noWateredTime=0; // Thời gian không tưới nước

    public LandView(PlayScreen playScreen,Land land,float x,float y) { //Khởi tạo lớp đất nền trồng cây
        super();
        this.land=land;
        this.playScreen=playScreen;
        this.setPosition(x,y);
        landTexture=new Texture(Gdx.files.internal("../assets/image/background/landbackground.png"));
        this.setSize(landTexture.getWidth(), landTexture.getHeight());//Kích thước đất được đặt theo kích thước ảnh nền
        loadLandData();//Tải dữ liệu hiện tại của đất
        this.addListener(new ClickListener(){public void clicked(InputEvent event, float x, float y)
        {
                if(playScreen.getMouseStatus()==-100){
                }
                else if(playScreen.getMouseStatus()==-1)
                    shovel();
                else if(playScreen.getMouseStatus()==-2)
                    water();
                else if(playScreen.getMouseStatus()==-3)
                    pick();
                else if(playScreen.getMouseStatus()==-4)
                    fertilize();
                else
                    seed(playScreen.getMouseStatus());
            }
            });

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(landTexture!=null){//Vẽ ảnh nền
            if(land.isWatered())// Nếu đã tưới nước, thay đổi màu bút vẽ thành xám đậm để hiện hiệu ứng tưới nước
                batch.setColor(Color.GRAY);
            batch.draw(landTexture, getX(), getY());
            batch.setColor(Color.WHITE);
        }

        if(cropTexture!=null)//Vẽ ảnh cây trồng
            batch.draw(cropTexture,getX(),getY());
}

    @Override
    public void act(float delta) { // Hàm thực hiện hành động của đất
        super.act(delta);
        if(land.getCropID()!=-1){
            if(playScreen.getWorldTime().getTotalPassedSecond()-land.getWaterTime()>86400) {
                land.setWatered(false);
            }
        }
        if(land.getCropID()!=-1&&!land.isPickable()&&!land.isPerished())//Có trồng, không héo úa, chưa trưởng thành mới có thể phát triển
            grow();
    }

    public void pick(){//Hàm thu hoạch
        if(land.getCropID()==-1){
            new Dialog("",playScreen.getSkin(),"dialog").text("Có trồng gì đâu mà đòi thu hoạch =))").button("Xác nhận", true).show(playScreen.getStage());
        }
        else if(!playScreen.getUser().isActable(3)){
            new Dialog("",playScreen.getSkin(),"dialog").text("Đang mệt nha không đủ sức thu hoạch đâu.").button("Xác nhận", true).show(playScreen.getStage());

        }
        else if(land.isPickable()) { // Nếu cây có thể thu hoạch

            playScreen.game.gamemusic.setharvestSoundPlaying();
            playScreen.getUser().setExp(playScreen.getUser().getExp()+5);
            playScreen.getUser().setHp(playScreen.getUser().getHp()-3);
            playScreen.getFruitArray().get(land.getCropID()).setFruitNumber(land.getFruitNum()+land.getFruitNum());
            new Dialog("",playScreen.getSkin(),"dialog").text("Thu hoạch thành công, thu được "+land.getFruitNum()+" quả "+playScreen.getFruitArray().get(land.getCropID()).getName()+"!").button("Xác nhận", true).show(playScreen.getStage());
            land.setFruitNum(0);
            cropTexture=new Texture(crop.getCropEndPic());
            land.setPerished(true);
            land.setPickable(false);
            land.setFertilized(false);
            playScreen.getBag().update();

        }
        else if(land.isPerished()) // Nếu cây đã chết
            new Dialog("",playScreen.getSkin(),"dialog").text("Cây chết mất rùi huhu T-T ").button("Xác nhận", true).show(playScreen.getStage());
        else
            new Dialog("",playScreen.getSkin(),"dialog").text("Chưa chín đâu. Gì mà vội z?").button("Xác nhận", true).show(playScreen.getStage());

    }

    public void shovel(){// Hàm xới đất
        if(land.getCropID()==-1)
        {
            new Dialog("",playScreen.getSkin(),"dialog").text("Không cần xới đất đâu!").button("Xác nhận", true).show(playScreen.getStage());
        }
        else if(!playScreen.getUser().isActable(4))
        {
            new Dialog("",playScreen.getSkin(),"dialog").text("Đang mệt nha không đủ sức xới đất lên đâu!").button("Xác nhận", true).show(playScreen.getStage());

        }

        else if(land.isPerished())
        {
            playScreen.game.gamemusic.setremoveSoundPlaying();
            playScreen.getUser().setExp(playScreen.getUser().getExp()+2);
            int random=MathUtils.random(1, 100);
            playScreen.getUser().setHp(playScreen.getUser().getHp()-4);
            if(random>80){
                Crop luckyCrop=playScreen.getCropArray().random();
                int luckyNumber=MathUtils.random(1, 5);
                playScreen.getCropArray().get(luckyCrop.getCropId()).setCropNumber(luckyCrop.getCropNumber()+luckyNumber);
                playScreen.getBag().update();
                new Dialog("",playScreen.getSkin(),"dialog").text("Uầy uầy! Trong khi xới đất may mắn tìm thấy "+luckyCrop.getName()+" x "+luckyNumber).button("Xác nhận", true).show(playScreen.getStage());
            }
            resetLand();
            cropTexture=null;
        }
        else if(land.isPickable()){
            new Dialog("",playScreen.getSkin(),"dialog") {
                protected void result(Object object) {
                    if (object.equals(true)) {
                        playScreen.getUser().setHp(playScreen.getUser().getHp()-4);
                        resetLand();
                        cropTexture=null;
                    }

                }
            }.text("Cây trồng đã chín, xác nhận xới đất không?").button("Xác nhận", true).button("Hủy",false).show(playScreen.getStage());

        }
        else{
            new Dialog("",playScreen.getSkin(),"dialog") {
                protected void result(Object object) {
                    if (object.equals(true)) {
                        playScreen.game.gamemusic.setremoveSoundPlaying();
                        playScreen.getUser().setHp(playScreen.getUser().getHp()-4);
                        resetLand();
                        cropTexture=null;
                    }

                }
            }.text("Cây trồng đang phát triển, xác nhận xới đất không?").button("Xác nhận", true).button("Hủy",false).show(playScreen.getStage());

        }

    }


    public void water(){//Hàm tưới nước
        if(land.getCropID()==-1)
            new Dialog("",playScreen.getSkin(),"dialog").text("Có trồng gì đâu mà tưới, tưới ma à?").button("Xác nhận", true).show(playScreen.getStage());

        else if(land.isWatered())
            new Dialog("",playScreen.getSkin(),"dialog").text("Mảnh đất này đã được tưới nước.").button("Xác nhận", true).show(playScreen.getStage());
        else if(land.isPerished())
            new Dialog("",playScreen.getSkin(),"dialog").text("Lúc sống thì không tưới, cây chết rồi tưới gì?").button("Xác nhận", true).show(playScreen.getStage());
        else if(land.isPickable())
            new Dialog("",playScreen.getSkin(),"dialog").text("Thu hoạch đi chứ ở đấy mà tưới nước.").button("Xác nhận", true).show(playScreen.getStage());
        else if(!playScreen.getUser().isActable(1))
        {
            new Dialog("",playScreen.getSkin(),"dialog").text("Đang mệt lắm không có sức tưới nước đâu.").button("Xác nhận", true).show(playScreen.getStage());

        }
        else {
            playScreen.game.gamemusic.setwaterSoundPlaying();
            playScreen.getUser().setHp(playScreen.getUser().getHp()-1);
            playScreen.getUser().setExp(playScreen.getUser().getExp()+1);
            land.setWatered(true);
            land.setWaterTime(playScreen.getWorldTime().getTotalPassedSecond());
        }
    }

    public void fertilize(){// Hàm bón phân
        if(land.getCropID()==-1)
            new Dialog("",playScreen.getSkin(),"dialog").text("Có trồng cây gì đâu mà bón phân =))").button("Xác nhận", true).show(playScreen.getStage());

        else if(land.isFertilized())
            new Dialog("",playScreen.getSkin(),"dialog").text("Lúc cây sống thì không bón, chết rồi bón gì nữa?").button("Xác nhận", true).show(playScreen.getStage());
        else if(land.isPerished())
            new Dialog("",playScreen.getSkin(),"dialog").text("Cây chín rồi thu hoạch đi chứ ở đó mà bón.").button("Xác nhận", true).show(playScreen.getStage());
        else if(land.isPickable())
            new Dialog("",playScreen.getSkin(),"dialog").text("Cây trồng đã được bón phân!").button("Xác nhận", true).show(playScreen.getStage());
        else if(!playScreen.getUser().isActable(2))
        {
            new Dialog("",playScreen.getSkin(),"dialog").text("Đang mệt lắm không có sức bón phân đâu").button("Xác nhận", true).show(playScreen.getStage());

        }
        else if(playScreen.getPropArray().get(3).getPropNumber()==0)
        {
            new Dialog("",playScreen.getSkin(),"dialog").text("Trong túi có đủ phân bón đâu mà bón =))").button("Xác nhận", true).show(playScreen.getStage());

        }
        else {
            playScreen.game.gamemusic.setwaterSoundPlaying();
            new Dialog("",playScreen.getSkin(),"dialog").text("Bón phân thành công! Sản lượng cây trồng sẽ gấp đôi!").button("Xác nhận", true).show(playScreen.getStage());
            playScreen.getPropArray().get(3).setPropNumber(playScreen.getPropArray().get(3).getPropNumber()-1);
            playScreen.getBag().update();
            playScreen.getUser().setHp(playScreen.getUser().getHp()-2);
            playScreen.getUser().setExp(playScreen.getUser().getExp()+3);
            land.setFertilized(true);

        }
    }

    public void seed(int cropID){//Hàm gieo hạt
        if(!land.isSeedable()){
            new Dialog("",playScreen.getSkin(),"dialog").text("Đất đã được gieo hạt rồi!").button("Xác nhận", true).show(playScreen.getStage());
        }
        else if(!playScreen.getUser().isActable(3)){
            new Dialog("",playScreen.getSkin(),"dialog").text("Đang mệt không gieo hạt được đâu!").button("Xác nhận", true).show(playScreen.getStage());
        }


        else {
            if(playScreen.getCropArray().get(cropID).getCropNumber()>0){
                playScreen.game.gamemusic.setSeedSoundPlaying();
                playScreen.getUser().setExp(playScreen.getUser().getExp()+4);
                playScreen.getUser().setHp(playScreen.getUser().getHp()-3);
                land.setCropID(cropID);
                land.setStartTime(playScreen.getWorldTime().getTotalPassedSecond());
                land.setWaterTime(land.getStartTime());
                crop=playScreen.getCropArray().get(cropID);
                crop.setCropNumber(crop.getCropNumber()-1);
                cropTexture=new Texture(Gdx.files.internal(crop.getNowStagePic(0)));
                land.setSeedable(false);
                playScreen.getBag().update();
            }
            else
                new Dialog("",playScreen.getSkin(),"dialog").text(playScreen.getCropArray().get(cropID).getName()+" không đủ trong kho!").button("Xác nhận", true).show(playScreen.getStage());
        }
    }

    public void loadLandData(){
        //Tải dữ liệu của đất
        if(land.getCropID()!=-1){
            this.crop=playScreen.getCropArray().get(land.getCropID());
            if(land.isPerished()){
                cropTexture=new Texture(Gdx.files.internal(crop.getCropEndPic()));
            }
            else
                cropTexture=new Texture(Gdx.files.internal(crop.getNowStagePic(land.getNowStage())));
        }
        else {
            cropTexture=null;
        }
    }

    public void resetLand(){
        // Khôi phục dữ liệu của đất
        land.setNowStage(0);
        land.setWatered(false);
        land.setStartTime(0);
        land.setFruitNum(0);
        land.setCropID(-1);
        land.setPerished(false);
        land.setNoWaterCount(0);
        land.setPerished(false);
        land.setPickable(false);
        land.setSeedable(true);
        land.setGrowTime(0);
        noWateredTime=0;
        land.setWaterTime(0);
    }

    public void checkWater(){
    }

    public void grow(){
        if(land.isWatered())
            noWateredTime=0;
        else
            noWateredTime=playScreen.getWorldTime().getTotalPassedSecond()-land.getWaterTime()-86400;

        if(noWateredTime>=86400&&!land.isPerished()){
            //Nếu không tưới nước trong 1 ngày, cây sẽ chết
            land.setPerished(true);
            cropTexture=new Texture(crop.getCropEndPic());
        }

        if(land.getNowStage()==crop.getStageCount()){//Khi cây đạt đến giai đoạn cuối, có thể thu hoạch
            land.setPickable(true);
            land.setFruitNum(MathUtils.random(10, 20));
            if(land.isFertilized())// Nếu bón phân, sản lượng sẽ gấp đôi
            {
                land.setFruitNum(land.getFruitNum()*2);
            }
        }

        if(!land.isPickable()&&!land.isPerished()){//Khi cây vẫn chưa chín và chưa chết
            if(land.isWatered())//Nếu được tưới, tăng thời gian sinh trưởng
                land.setGrowTime(land.getGrowTime()+Gdx.graphics.getDeltaTime() * playScreen.getWorldTime().getTimeRatio()); //Thời gian tăng trưởng tăng theo thời gian thực
            if(land.getGrowTime()>(land.getNowStage()+1)*crop.getUnitTime()*1800){ //Khi thời gian tăng trưởng vượt quá thời gian tăng trưởng của giai đoạn hiện tại, chuyển sang giai đoạn tiếp theo
                land.setNowStage(land.getNowStage()+1);// Tăng giai đoạn
                cropTexture=new Texture(crop.getNowStagePic(land.getNowStage())); //Thay đổi ảnh cây trồng
            }
        }
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Texture getCropTexture() {
        return cropTexture;
    }

    public void setCropTexture(Texture cropTexture) {
        this.cropTexture = cropTexture;
    }


}
