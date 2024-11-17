package com.mygdx.game.crops;

public class Fruit {
	//Lớp trái câu lưu trữ thông tin về trái cây
	private int fruitId;//id
	private String name;//tên cây trồng
	private String pic;//ảnh cây trồng
	private double fruitPrice;//Giá bán
<<<<<<< Updated upstream
	private int fruitNumber;// Lưu trữ số lượng quả trái hiện có hoặc được quản lý bởi đối tượng `Fruit`. Biến này có thể dùng để theo dõi số lượng trái cây trong trò chơi, ứng dụng quản lý trang trại, hoặc bất kỳ hệ thống nào yêu cầu quản lý số lượng đối tượng.

	public Fruit() {
		super(); // Gọi constructor của lớp cha
	}

	public Fruit(int fruitId, String name,String pic,double fruitPrice,int fruitNumber) {
		this.fruitId = fruitId;
		this.name = name;
		this.pic=pic;
		this.fruitPrice=fruitPrice;
		this.fruitNumber=fruitNumber;
	}



	public int getFruitId() { // Hàm lấy ID của cây trồng
		return fruitId; // Trả về ID của cây trồng
	}

	public void setFruitId(int fruitId) { // Hàm thiết lập ID của cây trồng
		this.fruitId = fruitId; // Gán ID của cây trồng
	}

	public String getName() { // Hàm lấy tên cây trồng
		return name; // Trả về tên cây trồng
	}

	public void setName(String name) { // Hàm thiết lập tên cây trồng
		this.name = name; // Gán tên cây trồng
	}

	public String getPic() { // Hàm lấy hình ảnh cây trồng
		return pic; // Trả về hình ảnh cây trồng
	}

	public void setPic(String pic) { // Hàm thiết lập hình ảnh cây trồng
		this.pic = pic; // Gán hình ảnh cây trồng
	}

	public double getFruitPrice() { // Hàm lấy giá bán
		return fruitPrice; // Trả về giá bán
	}

	public void setFruitPrice(double fruitPrice) { // Hàm thiết lập giá bán
		this.fruitPrice = fruitPrice; // Gán giá bán
	}

	public int getFruitNumber() { // Hàm lấy số lượng
		return fruitNumber; // Trả về số lượng
	}

	public void setFruitNumber(int fruitNumber) { // Hàm thiết lập số lượng
		this.fruitNumber = fruitNumber; // Gán số lượng
	}
		
=======
	private int fruitNumber;// số lượng

	public Fruit() {
			super();
		}

		public Fruit(int fruitId, String name,String pic,double fruitPrice,int fruitNumber) {

			this.fruitId = fruitId;
			this.name = name;
			this.pic=pic;
			this.fruitPrice=fruitPrice;
			this.fruitNumber=fruitNumber;
		}



		public int getFruitId() {
			return fruitId;
		}

		public void setFruitId(int fruitId) {
			this.fruitId = fruitId;
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
		public double getFruitPrice() {
			return fruitPrice;
		}

		public void setFruitPrice(double fruitPrice) {
			this.fruitPrice = fruitPrice;
		}
		public int getFruitNumber() {
			return fruitNumber;
		}

		public void setFruitNumber(int fruitNumber) {
			this.fruitNumber = fruitNumber;
		}
>>>>>>> Stashed changes

}
