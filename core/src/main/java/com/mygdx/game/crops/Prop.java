package com.mygdx.game.crops;

public class Prop {
	//Lưu trữ trông tin phần thiết bị

	private int propId;// id
	private String name;// Tên thiết bị
	private String pic;// ảnh thiết bị
	private double propPrice;// Đơn mua
	private int buyLevel;// Level mua

	public int getPropId() {
		return propId;
	}

	public void setPropId(int propId) {
		this.propId = propId;
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

	public double getPropPrice() {
		return propPrice;
	}

	public void setPropPrice(double propPrice) {
		this.propPrice = propPrice;
	}

	public int getBuyLevel() {
		return buyLevel;
	}

	public void setBuyLevel(int buyLevel) {
		this.buyLevel = buyLevel;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getPropNumber() {
		return propNumber;
	}

	public void setPropNumber(int propNumber) {
		this.propNumber = propNumber;
	}

	private String property;// Thuộc tính chống đỡ
    private int value;//giá trị thuộc tính prop
	private int propNumber;//Kho đạo cụ



    public Prop(int propId, String name, String pic, double propPrice, int buyLevel,String property,int value,int propNumber) {
		this.propId = propId;
		this.name = name;
		this.pic = pic;
		this.propPrice = propPrice;
		this.buyLevel = buyLevel;
		this.property=property;
		this.value=value;
		this.propNumber=propNumber;
	}
     public Prop() {
    	 super();
     }




}
