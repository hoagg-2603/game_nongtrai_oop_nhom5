package com.mygdx.game.crops;

public class Fruit {
	//Lớp trái câu lưu trữ thông tin về trái cây
	private int fruitId;//id
	private String name;//tên cây trồng
	private String pic;//ảnh cây trồng
	private double fruitPrice;//Giá bán
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

}
