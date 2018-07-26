package org.ls.entity;

/**
 * 汽车
 */
public class Auto extends Vehicule {

	/**
	 * @param id
	 * @param bianHao
	 * @param km
	 * @param price
	 * @param storeNum
	 */
	public Auto(int id, String bianHao, int km, float price, int storeNum,
			String xingHao) {
		super(id, bianHao, km, price, storeNum);
		this.xingHao = xingHao;
	}               // initialisation de code, kilometre,prix, modele

	protected String xingHao;// 型号——汽车专有属性         modele de la vehicule

	public String getXingHao() {
		return xingHao;
	}

	public void setXingHao(String xingHao) {
		this.xingHao = xingHao;
	}
	@Override
	public String toString() {
		return xingHao + "\t" + storeNum + "\t" + price;
	}
}
