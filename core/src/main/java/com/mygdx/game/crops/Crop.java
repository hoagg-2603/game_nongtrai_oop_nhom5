package com.mygdx.game.crops;

public class Crop {
<<<<<<< Updated upstream
	private int cropId; // ID của cây trồng
	private String name; // Tên cây trồng
	private String pic; // Hình ảnh cây trồng
	private int stageCount; // Số lượng giai đoạn phát triển của cây trồng
	private int unitTime; // Thời gian mỗi giai đoạn phát triển
	private double seedPrice; // Giá tiền mỗi hạt giống
	private int buyLevel; // Level mua cây trồng
	private int cropNumber; // Số lượng cây trồng
=======
	//作物类，存储作物信息

	private int cropId;// 农作物编号
	private String name;// 农作物名称
	private String pic;// 农作物果实图片
	private int stageCount;// 农作物生长阶段数
	private int unitTime;// 农作物生长时各阶段时间基数
	private double seedPrice;// 种子购买单价
	private int buyLevel;// 种子购买等级
	private int cropNumber;//种子库存量
>>>>>>> Stashed changes


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
<<<<<<< Updated upstream
     public Crop() { 
    	 super(); // Gọi constructor của lớp cha
     }

	public String getCropFruitPic() { // Hàm lấy hình ảnh quả cây trồng
		return "../assets/image/crops/crop" + cropId + "/seed.png"; // Trả về đường dẫn hình ảnh quả cây trồng
	}


	public String getCropEndPic() { // Hàm lấy hình ảnh cây trồng đã chín
		return "../assets/image/crops/crop" + cropId + "/cron_end.png"; // Trả về đường dẫn hình ảnh cây trồng đã chín
	}

	public String getNowStagePic( int stageNow) { // Hàm lấy hình ảnh giai đoạn phát triển hiện tại của cây trồng
		return "../assets/image/crops/crop" + cropId + "/" + stageNow + ".png"; // Trả về đường dẫn hình ảnh giai đoạn phát triển hiện tại của cây trồng
	}

	public int getCropId() { // Hàm lấy ID của cây trồng
		return cropId; // Trả về ID của cây trồng
	}

	public void setCropId(int cropId) { // Hàm thiết lập ID của cây trồng
		this.cropId = cropId;	// Gán ID của cây trồng
	}

	public String getName() { // Hàm lấy tên cây trồng
		return name;// Trả về tên cây trồng
	}

	public void setName(String name) { // Hàm thiết lập tên cây trồng
		this.name = name;	// Gán tên cây trồng
	}

	public String getPic() {	// Hàm lấy hình ảnh cây trồng
		return pic; // Trả về hình ảnh cây trồng
	}

	public void setPic(String pic) { // Hàm thiết lập hình ảnh cây trồng
		this.pic = pic;	// Gán hình ảnh cây trồng
	}

	public int getStageCount() { // Hàm lấy số lượng giai đoạn phát triển của cây trồng
		return stageCount; // Trả về số lượng giai đoạn phát triển của cây trồng
	}

	public void setStageCount(int stageCount) { // Hàm thiết lập số lượng giai đoạn phát triển của cây trồng
		this.stageCount = stageCount;	// Gán số lượng giai đoạn phát triển của cây trồng
	}

	public int getUnitTime() { // Hàm lấy thời gian mỗi giai đoạn phát triển
		return unitTime; // Trả về thời gian mỗi giai đoạn phát triển
	}

	public void setUnitTime(int unitTime) { // Hàm thiết lập thời gian mỗi giai đoạn phát triển
		this.unitTime = unitTime; // Gán thời gian mỗi giai đoạn phát triển
	}


	public double getSeedPrice() { // Hàm lấy giá tiền mỗi hạt giống
		return seedPrice; // Trả về giá tiền mỗi hạt giống
	}

	public void setSeedPrice(double seedPrice) { //	Hàm thiết lập giá tiền mỗi hạt giống
		this.seedPrice = seedPrice; // Gán giá tiền mỗi hạt giống
	}

	public int getBuyLevel() { // Hàm lấy level mua cây trồng
		return buyLevel; // Trả về level mua cây trồng
	}

	public void setBuyLevel(int buyLevel) { // Hàm thiết lập level mua cây trồng
		this.buyLevel = buyLevel; // Gán level mua cây trồng
	}
	public int getCropNumber() { // Hàm lấy số lượng cây trồng
		return cropNumber; // Trả về số lượng cây trồng
	}

	public void setCropNumber(int cropNumber) { // Hàm thiết lập số lượng cây trồng
		this.cropNumber = cropNumber; // Gán số lượng cây trồng
=======
     public Crop() {
    	 super();
     }

	// 按农作物id获取果实图片的完整路径
	public String getCropFruitPic() {
		return "../assets/image/crops/crop" + cropId + "/seed.png";
	}



	// 按农作物id获取农作物枯萎图片的完整路径
	public String getCropEndPic() {
		return "../assets/image/crops/crop" + cropId + "/cron_end.png";
	}

	// 按农作物id和现在的生长阶段获取农作物当前阶段图片的完整路径
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
>>>>>>> Stashed changes
	}
}
