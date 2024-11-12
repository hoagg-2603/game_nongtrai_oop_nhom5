package com.mygdx.game.crops;

public class Land {
    private int landID;// id
    private int cropID = -1;// số lượng cây trồng trên đất(-1 nghĩa là không trồng cây gì)
    private double growTime;// thời gian tăng trưởng，sẽ thay đổi  khi tưới nước
    private double startTime;//thời điểm bắt đầu gieo hạt
    private double waterTime;//Thời gian tưới
    private int nowStage=0;// Giai đoạn tăng trưởng hiện tại
    private boolean isPerished;// trạng thái
    private boolean isBugged;// Có lỗi
    private boolean isWeeded;//Có đang phát triển không
    private boolean isWatered;//Có được tưới nước không
    private int noWaterCount;// Số lần được tưới nước liên tiếp
    private int fruitNum;// Số quả
    private boolean isFertilized;//Việc bón phân

    private boolean pickable=false;
    private boolean seedable=true;


    public Land() {
        super();
    }
    public Land(int landID)
    {
        this.landID=landID;
    }

    public Land(int landID, int cropID, double growTime, double startTime,double waterTime, int nowStage, boolean isPerished, boolean isBugged, boolean isWeeded, boolean isWatered, int noWaterCount, int fruitNum) {
        this.landID = landID;
        this.cropID = cropID;
        this.growTime = growTime;
        this.startTime = startTime;
        this.waterTime=waterTime;
        this.nowStage = nowStage;
        this.isPerished = isPerished;
        this.isBugged = isBugged;
        this.isWeeded = isWeeded;
        this.isWatered = isWatered;
        this.noWaterCount = noWaterCount;
        this.fruitNum = fruitNum;
    }

    public int getLandID() {
        return landID;
    }

    public void setLandID(int landID) {
        this.landID = landID;
    }

    public int getCropID() {
        return cropID;
    }

    public void setCropID(int cropID) {
        this.cropID = cropID;
    }

    public double getGrowTime() {
        return growTime;
    }

    public void setGrowTime(double growTime) {
        this.growTime = growTime;
    }

    public int getNowStage() {
        return nowStage;
    }

    public void setNowStage(int nowStage) {
        this.nowStage = nowStage;
    }


    public boolean isBugged() {
        return isBugged;
    }

    public void setBugged(boolean bugged) {
        isBugged = bugged;
    }

    public boolean isWeeded() {
        return isWeeded;
    }

    public void setWeeded(boolean weeded) {
        isWeeded = weeded;
    }

    public boolean isWatered() {
        return isWatered;
    }

    public void setWatered(boolean watered) {
        isWatered = watered;
    }

    public int getNoWaterCount() {
        return noWaterCount;
    }

    public void setNoWaterCount(int noWaterCount) {
        this.noWaterCount = noWaterCount;
    }

    public int getFruitNum() {
        return fruitNum;
    }

    public void setFruitNum(int fruitNum) {
        this.fruitNum = fruitNum;
    }

    public boolean isPerished() {
        return isPerished;
    }

    public void setPerished(boolean perished) {
        isPerished = perished;
    }
    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }
    public boolean isPickable() {
        return pickable;
    }

    public void setPickable(boolean pickable) {
        this.pickable = pickable;
    }

    public boolean isSeedable() {
        return seedable;
    }

    public void setSeedable(boolean seedable) {
        this.seedable = seedable;
    }

    public double getWaterTime() {
        return waterTime;
    }

    public void setWaterTime(double waterTime) {
        this.waterTime = waterTime;
    }

    public boolean isFertilized() {
        return isFertilized;
    }

    public void setFertilized(boolean fertilized) {
        isFertilized = fertilized;
    }
}
