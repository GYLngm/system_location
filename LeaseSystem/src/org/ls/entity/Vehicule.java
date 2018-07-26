package org.ls.entity;

/**
 * 车辆
 */
public class Vehicule {

	protected int id;// 主键

	protected String bianHao;// 编号

	protected int km;// 公里数

	protected float price;// 汽车价格
	
	protected int storeNum;// 库存量
	


	/**
	 * @param id
	 * @param bianHao
	 * @param km
	 * @param price
	 * @param storeNum
	 */
	public Vehicule(int id, String bianHao, int km, float price, int storeNum) {
		super();
		this.id = id;
		this.bianHao = bianHao;
		this.km = km;
		this.price = price;
		this.storeNum = storeNum;
	}                 //cree les attribut des vehicules

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBianHao() {
		return bianHao;
	}

	public void setBianHao(String bianHao) {
		this.bianHao = bianHao;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}
	
	

}
