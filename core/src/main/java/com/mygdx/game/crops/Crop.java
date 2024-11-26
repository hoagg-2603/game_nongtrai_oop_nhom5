package com.mygdx.game.crops;

public class Crop {
	private int cropId;
	private String name;
	private String pic;
	private int stageCount;
	private int unitTime;
	private double seedPrice;
	private int buyLevel;
	private int cropNumber;


	public Crop(int cropId, String name, String pic, int stageCount,
			int unitTime,  double seedPrice, int buyLevel,int cropNumber) {
		this.cropId = cropId;
		this.name = name;
		this.pic = pic;
		this.stageCount = stageCount;
		this.unitTime = unitTime;
		this.seedPrice = seedPrice;
		this.buyLevel = buyLevel;
		this.cropNumber=cropNumber;
	}
     public Crop() {
    	 super();
     }

	public String getCropFruitPic() {
		return "../assets/image/crops/crop" + cropId + "/seed.png";
	}

	public String getCropEndPic() {
		return "../assets/image/crops/crop" + cropId + "/cron_end.png";
	}

	public String getNowStagePic( int stageNow) {
		return "../assets/image/crops/crop" + cropId + "/" + stageNow + ".png";
	}

	public int getCropId() {
		return cropId;
	}

	public void setCropId(int cropId) {
		this.cropId = cropId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getStageCount() {
		return stageCount;
	}

	public void setStageCount(int stageCount) {
		this.stageCount = stageCount;
	}

	public int getUnitTime() {
		return unitTime;
	}

	public void setUnitTime(int unitTime) {
		this.unitTime = unitTime;
	}


	public double getSeedPrice() {
		return seedPrice;
	}

	public void setSeedPrice(double seedPrice) {
		this.seedPrice = seedPrice;
	}

	public int getBuyLevel() {
		return buyLevel;
	}

	public void setBuyLevel(int buyLevel) {
		this.buyLevel = buyLevel;
	}
	public int getCropNumber() {
		return cropNumber;
	}

	public void setCropNumber(int cropNumber) {
		this.cropNumber = cropNumber;
	}
}
