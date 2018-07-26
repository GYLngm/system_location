package org.ls.entity;

/**
 * 摩托车
 */
public class Moto extends Vehicule {

	/**
	 * @param id
	 * @param bianHao
	 * @param km
	 * @param price
	 * @param storeNum
	 */
	public Moto(int id, String bianHao, int km, float price, int storeNum,
			int horsepower) {
		super(id, bianHao, km, price, storeNum);
		this.horsepower = horsepower;        // creer les attribut des motos
	}

	private int horsepower;// 马力——摩托车专有属性

	public int getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}

	@Override
	public String toString() {
		return bianHao + "\t" + storeNum + "\t" + price;
	}

}
