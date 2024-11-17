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

<<<<<<< Updated upstream
    private boolean pickable=false; //Có thể hái được không
    private boolean seedable=true; //Có thể gieo hạt được không


    public Land() {
        super(); // Gọi constructor của lớp cha
    }

    public Land(int landID){
        this.landID=landID; // Gán ID đất
=======
    private boolean pickable=false;
    private boolean seedable=true;


    public Land() {
        super();
    }
    public Land(int landID)
    {
        this.landID=landID;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public int getLandID() { // Hàm lấy ID đất
        return landID; // Trả về ID đất
    }

    public void setLandID(int landID) { // Hàm thiết lập ID đất
        this.landID = landID; // Gán ID đất
    }

    public int getCropID() { // Hàm lấy ID cây trồng
        return cropID; // Trả về ID cây trồng
    }

    public void setCropID(int cropID) { // Hàm thiết lập ID cây trồng
        this.cropID = cropID; // Gán ID cây trồng
    }

    public double getGrowTime() { // Hàm lấy thời gian tăng trưởng
        return growTime; // Trả về thời gian tăng trưởng
    }

    public void setGrowTime(double growTime) { // Hàm thiết lập thời gian tăng trưởng
        this.growTime = growTime; // Gán thời gian tăng trưởng
    }

    public int getNowStage() { // Hàm lấy giai đoạn tăng trưởng hiện tại
        return nowStage; // Trả về giai đoạn tăng trưởng hiện tại
    }

    public void setNowStage(int nowStage) { // Hàm thiết lập giai đoạn tăng trưởng hiện tại
        this.nowStage = nowStage; // Gán giai đoạn tăng trưởng hiện tại
    }


    public boolean isBugged() { // Hàm kiểm tra có lỗi không
        return isBugged; // Trả về có lỗi không
    }

    public void setBugged(boolean bugged) { // Hàm thiết lập có lỗi không
        isBugged = bugged; // Gán có lỗi không
    }

    public boolean isWeeded() { // Hàm kiểm tra có đang phát triển không
        return isWeeded; // Trả về có đang phát triển không
    }

    public void setWeeded(boolean weeded) { // Hàm thiết lập có đang phát triển không
        isWeeded = weeded; // Gán có đang phát triển không
    }

    public boolean isWatered() { // Hàm kiểm tra có được tưới nước không
        return isWatered; // Trả về có được tưới nước không
    }

    public void setWatered(boolean watered) { // Hàm thiết lập có được tưới nước không
        isWatered = watered; // Gán có được tưới nước không
    }

    public int getNoWaterCount() { // Hàm lấy số lần được tưới nước liên tiếp
        return noWaterCount; // Trả về số lần được tưới nước liên tiếp
    }

    public void setNoWaterCount(int noWaterCount) { // Hàm thiết lập số lần được tưới nước liên tiếp
        this.noWaterCount = noWaterCount; // Gán số lần được tưới nước liên tiếp
    } 

    public int getFruitNum() { // Hàm lấy số quả
        return fruitNum; // Trả về số quả
    }

    public void setFruitNum(int fruitNum) { // Hàm thiết lập số quả
        this.fruitNum = fruitNum; // Gán số quả
    }

    public boolean isPerished() { // Hàm kiểm tra trạng thái
        return isPerished; // Trả về trạng thái
    }

    public void setPerished(boolean perished) { // Hàm thiết lập trạng thái
        isPerished = perished; // Gán trạng thái
    }
    public double getStartTime() { // Hàm lấy thời điểm bắt đầu gieo hạt
        return startTime; // Trả về thời điểm bắt đầu gieo hạt
    }

    public void setStartTime(double startTime) { // Hàm thiết lập thời điểm bắt đầu gieo hạt
        this.startTime = startTime; // Gán thời điểm bắt đầu gieo hạt
    }
    public boolean isPickable() { // Hàm kiểm tra có thể hái được không
        return pickable; // Trả về có thể hái được không
    }

    public void setPickable(boolean pickable) { // Hàm thiết lập có thể hái được không
        this.pickable = pickable;   // Gán có thể hái được không
    }

    public boolean isSeedable() {   // Hàm kiểm tra có thể gieo hạt được không
        return seedable;   // Trả về có thể gieo hạt được không
    }

    public void setSeedable(boolean seedable) { // Hàm thiết lập có thể gieo hạt được không
        this.seedable = seedable;  // Gán có thể gieo hạt được không
    }

    public double getWaterTime() { // Hàm lấy thời gian tưới
        return waterTime; // Trả về thời gian tưới
    }

    public void setWaterTime(double waterTime) { // Hàm thiết lập thời gian tưới
        this.waterTime = waterTime; // Gán thời gian tưới
    }

    public boolean isFertilized() { // Hàm kiểm tra việc bón phân
        return isFertilized; // Trả về việc bón phân
    }

    public void setFertilized(boolean fertilized) { // Hàm thiết lập việc bón phân
        isFertilized = fertilized; // Gán việc bón phân
=======
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
>>>>>>> Stashed changes
    }
}
